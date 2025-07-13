package org.nofre.api.base.observability;

import lombok.Getter;

@Getter
public enum Header {
    TRACE_ID("X-TraceId"),
    SPAN_ID("X-SpanId"),
    TRACEPARENT("traceparent");

    private final String value;

    Header(String value) {
        this.value = value;
    }
}
