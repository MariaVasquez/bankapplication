package com.devsu.hackerearth.backend.client.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FieldCustomErrors {

    private String field;
    private String error;

}