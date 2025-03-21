package com.devsu.hackerearth.backend.client.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseCode {

    DATABASE_ERROR("TRAPPCLI_ERR_1",500,"Database error"),
    CLIENT_NOT_FOUND("TRAPPCLI_ERR_2",404,"Client not found"),
    TRANSACTION_SUCCESS("TRAPPCLI_SUCC_1",200, "Ok"),
    UNEXPECTED_ERROR("TRAPPCLI_ERR_3",500,"Unexpected error");

    private final String code;
    private final int httpStatus;
    private final String message;

    public String formatMessage(Object... args){return String.format(this.message, args);}
    
}
