package org.nofre.api.base.common.controller;


import org.nofre.api.base.common.model.Paginated;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public abstract class CommonController {

    public <T> ResponseEntity<CommonRs<T>> response(T data, HttpStatus status, HttpMethod method) {
        return ResponseEntity.ok(new CommonRs<>(data, status, method));
    }

    public <T> ResponseEntity<CommonRs<Paginated<T>>> response(Paginated<T> data, HttpStatus status, HttpMethod method) {
        return ResponseEntity.ok(new CommonRs<>(data, status, method));
    }


    public <T> ResponseEntity<CommonRs<T>> getResponse(T data) {
        return ResponseEntity.ok(new CommonRs<>(data));
    }


    public <T> ResponseEntity<CommonRs<T>> createdResponse(T data) {
        return response(data, HttpStatus.CREATED, HttpMethod.POST);
    }

    public <T> ResponseEntity<CommonRs<T>> updatedResponse(T data) {
        return response(data, HttpStatus.OK, HttpMethod.PUT);
    }


    public ResponseEntity<CommonRs<Void>> deletedResponse() {
        return ResponseEntity.ok(new CommonRs<>(HttpStatus.NO_CONTENT, HttpMethod.DELETE));
    }


    public <T> ResponseEntity<CommonRs<Paginated<T>>> paginatedResponse(Paginated<T> data) {
        return ResponseEntity.ok(new CommonRs<>(data, HttpMethod.POST));
    }


}
