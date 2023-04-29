package com.nicogmerz4.portfolio.service;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Setter @Getter 
public class CustomResponse {
    private HttpStatus httpStatus;
    private CustomResponseBody body;

    public CustomResponse() {
        httpStatus = HttpStatus.OK;
        body = new CustomResponseBody();
    }
}
