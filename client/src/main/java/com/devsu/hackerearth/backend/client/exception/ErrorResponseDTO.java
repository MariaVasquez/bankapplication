package com.devsu.hackerearth.backend.client.exception;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponseDTO {
    private String code;
    private String message;
    private int httpStatus;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<FieldCustomErrors> fieldCustomErrors;

    public ErrorResponseDTO(String code, String message, int httpStatus) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public ErrorResponseDTO(String code, String message, int httpStatus, List<FieldCustomErrors> fieldCustomErrors) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
        this.fieldCustomErrors = fieldCustomErrors;
    }

}