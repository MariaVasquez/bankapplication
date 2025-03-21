package com.devsu.hackerearth.backend.account.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.devsu.hackerearth.backend.account.model.Account;
import com.devsu.hackerearth.backend.account.model.dto.AccountDto;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.ERROR, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface AccountMapper {
    
    @Mapping(target = "isActive", source = "active")
    AccountDto entityToAccountDto(Account account);

    Account accountDtoToEntity(AccountDto accountDto);

    
}
