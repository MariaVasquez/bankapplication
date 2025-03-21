package com.devsu.hackerearth.backend.account.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FieldCustomErrors {

    private String field;
    private String error;

}