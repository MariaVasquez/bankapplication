package com.devsu.hackerearth.backend.account.utilTest;

import java.util.List;

import com.devsu.hackerearth.backend.account.model.Account;
import com.devsu.hackerearth.backend.account.model.dto.AccountDto;

public class DataMockAccount {

    public static List<AccountDto> accountDtosList(){
        return List.of(AccountDto.builder()
        .clientId(1L)
        .id(1L)
        .initialAmount(6000)
        .number("152203")
        .isActive(true)
        .build());
    }

    public static AccountDto accountDto(){
        return AccountDto.builder()
        .clientId(1L)
        .id(1L)
        .initialAmount(6000)
        .number("152203")
        .isActive(true).build();
    }

    public static List<Account> accounts(){
        return List.of(Account.builder()
        .clientId(1L)
        .id(1L)
        .initialAmount(6000)
        .number("152203")
        .active(true).build());
    }


    
}
