package org.nofre.api.base.common.controller;

import lombok.*;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommonRs<T> {
    public T data;
    public String status = HttpStatus.OK.name();
    public String method = HttpMethod.GET.name();
    public String error = "";


    public CommonRs(T data) {
        this.data = data;
    }

    public CommonRs(T data, HttpMethod method) {
        this.data = data;
        this.method = method.name();
    }

    public CommonRs(T data, HttpStatus status, HttpMethod method) {
        this.data = data;
        this.status = status.name();
        this.method = method.name();
    }

    public CommonRs(HttpStatus status, HttpMethod method) {
        this.status = status.name();
        this.method = method.name();
    }

    public CommonRs(Exception e) {
        this.error = e.getMessage();
        this.status = HttpStatus.INTERNAL_SERVER_ERROR.name();
    }


}
