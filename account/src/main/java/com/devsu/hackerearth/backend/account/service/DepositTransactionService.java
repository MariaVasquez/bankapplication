package com.devsu.hackerearth.backend.account.service;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.devsu.hackerearth.backend.account.exception.ApiException;
import com.devsu.hackerearth.backend.account.mapper.TransactionMapper;
import com.devsu.hackerearth.backend.account.model.Account;
import com.devsu.hackerearth.backend.account.model.dto.ResponseCode;
import com.devsu.hackerearth.backend.account.model.dto.TransactionDto;
import com.devsu.hackerearth.backend.account.repository.AccountRepository;
import com.devsu.hackerearth.backend.account.repository.TransactionRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DepositTransactionService {

    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;
    private final AccountRepository accountRepository;

    public DepositTransactionService(TransactionRepository transactionRepository, TransactionMapper transactionMapper,
            AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.transactionMapper = transactionMapper;
        this.accountRepository = accountRepository;
    }

    public TransactionDto execute(TransactionDto transactionDto) {
        try {
            log.info("Init transaction for accountid {}", transactionDto.getAccountId());
            Account account = accountRepository.findById(transactionDto.getAccountId())
                    .orElseThrow(() -> new ApiException(ResponseCode.ACCOUNT_NOT_FOUND));

            double balance = operationBalance(account.getInitialAmount(), transactionDto.getAmount());

            account.setInitialAmount(balance);
            accountRepository.save(account);

            transactionDto.setDate(new Date());
            transactionDto.setBalance(balance);

            return transactionMapper.entityToTransactionDto(
                    transactionRepository.save(transactionMapper.transactionDtoToEntity(transactionDto)));
        } catch (Exception e) {
            log.error("create transaction error: {}", e.getMessage());
            throw new ApiException(ResponseCode.DATABASE_ERROR);
        }
    }

    private double operationBalance(double initialAmount, double amount) {
        if (amount < 0 && initialAmount + amount < 0) {
            throw new ApiException(ResponseCode.ACCOUNT_WITHOUT_BALANCE);
        }
        return initialAmount + amount;
    }

    public TransactionDto fallbackTransaction(TransactionDto transactionDto, Throwable t) {
        log.warn("Transaction fail for accountid {}", transactionDto.getAccountId());
        return null;
    }

}
