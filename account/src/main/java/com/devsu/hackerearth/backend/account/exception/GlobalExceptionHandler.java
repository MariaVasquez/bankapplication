package com.devsu.hackerearth.backend.account.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.devsu.hackerearth.backend.account.model.dto.ResponseCode;

import org.springframework.http.HttpStatus;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApiException.class)
    @ResponseBody
    public ResponseEntity<ErrorResponseDTO> handleCustomException(ApiException ex) {
        return new ResponseEntity<>(
                new ErrorResponseDTO(ex.getCode(), ex.getMessage(), ex.getHttpStatus()),
                HttpStatus.valueOf(ex.getHttpStatus()));
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorResponseDTO handleException(Exception ex){
        return new ErrorResponseDTO(ResponseCode.UNEXPECTED_ERROR.name(), ResponseCode.UNEXPECTED_ERROR.getMessage(), ResponseCode.UNEXPECTED_ERROR.getHttpStatus());
    }

}
