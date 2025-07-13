package org.nofre.api.base.configuration.security.filter;

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
public class ObservabilityInjectFilter extends OncePerRequestFilter {

    private final Tracer tracer;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        TraceContext context = tracer.currentTraceContext().context();
        if (context != null) {
            response.addHeader(TRACEPARENT.getValue(),
                    "00-%s-%s-%s".formatted(
                            context.traceId(),
                            context.spanId(),
                            Boolean.TRUE.equals(context.sampled()) ? "01" : "00")
            );
            response.addHeader(TRACE_ID.getValue(), context.traceId());
            response.addHeader(SPAN_ID.getValue(), context.spanId());
        }

        filterChain.doFilter(request, response);
    }
}
