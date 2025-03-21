package com.devsu.hackerearth.backend.account.model.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GenericResponseDto<T> implements Serializable {
    private String status;
    private String message;
    private T data;

    public GenericResponseDto(T data){
        this.status = ResponseCode.TRANSACTION_SUCCESS.getCode();
        this.message = ResponseCode.TRANSACTION_SUCCESS.getMessage();
        this.data = data;
    }
}
