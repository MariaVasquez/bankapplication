package com.devsu.hackerearth.backend.client.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenericResponseDto<T> {
    private String status;
    private String message;
    private T data;

    public GenericResponseDto(T data){
        this.status = ResponseCode.TRANSACTION_SUCCESS.getCode();
        this.message = ResponseCode.TRANSACTION_SUCCESS.getMessage();
        this.data = data;
    }
}
