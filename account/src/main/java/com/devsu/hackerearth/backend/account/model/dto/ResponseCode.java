package com.devsu.hackerearth.backend.account.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseCode {

    DATABASE_ERROR("TRAPP_ERR_1",500,"Database error"),
    ACCOUNT_NOT_FOUND("TRAPP_ERR_2",404,"Account not found"),
    TRANSACTION_NOT_FOUND("TRAPP_ERR_3",404,"Transaction not found"),
    ACCOUNT_WITHOUT_BALANCE("TRAPP_ERR_4",404,"Balance not available"),
    CLIENT_NOT_FOUND("TRAPP_ERR_5",404,"Client not found"),
    TRANSACTION_SUCCESS("TRAPP_SUCC_1",200, "Ok"),
    UNEXPECTED_ERROR("TRAPP_ERR_6",500,"Unexpected error");

    private final String code;
    private final int httpStatus;
    private final String message;

    public String formatMessage(Object... args){return String.format(this.message, args);}
    
}
