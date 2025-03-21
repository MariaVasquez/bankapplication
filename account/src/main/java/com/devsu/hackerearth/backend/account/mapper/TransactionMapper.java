package com.devsu.hackerearth.backend.account.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.devsu.hackerearth.backend.account.model.Transaction;
import com.devsu.hackerearth.backend.account.model.dto.TransactionDto;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.ERROR, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface TransactionMapper {

    TransactionDto entityToTransactionDto(Transaction transaction);

    Transaction transactionDtoToEntity(TransactionDto transactionDto);
    
}
