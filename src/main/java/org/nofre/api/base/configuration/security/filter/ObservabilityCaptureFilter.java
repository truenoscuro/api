package org.nofre.api.base.configuration.security.filter;

import io.micrometer.tracing.Span;
import io.micrometer.tracing.TraceContext;
import io.micrometer.tracing.Tracer;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static org.nofre.api.base.observability.Header.*;

@Component
@RequiredArgsConstructor
public class ObservabilityCaptureFilter extends OncePerRequestFilter {

    private final Tracer tracer;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        String traceparent = request.getHeader(TRACEPARENT.getValue());
        String traceId = request.getHeader(TRACE_ID.getValue());
        String spanId = request.getHeader(SPAN_ID.getValue());
        //Si hay traceparent se configura con el nombre que toca
        if (traceparent != null) {
            traceId = traceparent.split("-")[1];
            spanId = traceparent.split("-")[2];
        }

        if (traceId == null || spanId == null) {
            filterChain.doFilter(request, response);
            return;
        }

        TraceContext context = tracer.traceContextBuilder()
                .traceId(traceId)
                .spanId(spanId)
                .sampled(Boolean.TRUE)
                .build();

        Span span = tracer.spanBuilder()
                .setParent(context)
                .name(request.getRequestURI())
                .start();

        //Creamos el contexto y lo cerramos
        try (Tracer.SpanInScope ws = tracer.withSpan(span)) {
            filterChain.doFilter(request, response);
        } finally {
            span.end();
        }

    }

}
