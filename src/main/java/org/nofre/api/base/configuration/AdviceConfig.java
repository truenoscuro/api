package org.nofre.api.base.configuration;

import org.nofre.api.base.common.controller.CommonRs;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AdviceConfig {


    @ExceptionHandler(Exception.class)
    public ResponseEntity<CommonRs<Void>> handleException(Exception e) {
        return ResponseEntity.internalServerError().body(new CommonRs<>(e));
    }

}
