package org.nofre.api.base.common.controller;


import io.micrometer.observation.annotation.Observed;
import org.nofre.api.base.common.controller.model.CommonRs;
import org.nofre.api.base.common.crud.model.Paginated;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Observed
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

    public <T> ResponseEntity<CommonRs<T>> postResponse(T data) {
        return response(data, HttpStatus.OK, HttpMethod.POST);
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
