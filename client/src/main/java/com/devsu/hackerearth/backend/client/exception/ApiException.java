package com.devsu.hackerearth.backend.client.exception;

import java.util.List;

import com.devsu.hackerearth.backend.client.model.dto.ResponseCode;

import lombok.Getter;

@Getter
public class ApiException extends RuntimeException {

    private final String code;
    private final String message;
    private final int httpStatus;
    private final List<FieldCustomErrors> fieldCustomErrors;

    public ApiException(ResponseCode responseCode){
        this.code = responseCode.getCode();
        this.message =  responseCode.getMessage();
        this.httpStatus = responseCode.getHttpStatus();
        this.fieldCustomErrors = null;
    }

    public ApiException(ResponseCode responseCode, List<FieldCustomErrors> fieldCustomErrors){
        this.code = responseCode.getCode();
        this.message =  responseCode.getMessage();
        this.httpStatus = responseCode.getHttpStatus();
        this.fieldCustomErrors = fieldCustomErrors;
    }
}
