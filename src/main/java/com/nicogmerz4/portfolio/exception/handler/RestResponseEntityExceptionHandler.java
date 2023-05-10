package com.nicogmerz4.portfolio.exception.handler;

import com.nicogmerz4.portfolio.service.CustomResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class RestResponseEntityExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    final public ResponseEntity handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, Map<String, String>> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorCode = ((FieldError) error).getCode();
            String errorMessage = error.getDefaultMessage();
            if (errors.containsKey(fieldName)) {
                errors.get(fieldName).put(errorCode, errorMessage);
                return;
            }
            Map<String, String> aux = new HashMap<>();
            aux.put(errorCode, errorMessage);
            errors.put(fieldName, aux);
        });
        
        CustomResponse res = new CustomResponse();
        res.getBody().setErrors(errors);
        return new ResponseEntity(res.getBody(), HttpStatus.BAD_REQUEST);
    }
}
