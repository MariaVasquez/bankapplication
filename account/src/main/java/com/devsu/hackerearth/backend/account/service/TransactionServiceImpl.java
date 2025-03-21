package com.devsu.hackerearth.backend.account.service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.devsu.hackerearth.backend.account.exception.ApiException;
import com.devsu.hackerearth.backend.account.feign.ClientService;
import com.devsu.hackerearth.backend.account.mapper.TransactionMapper;
import com.devsu.hackerearth.backend.account.model.Account;
import com.devsu.hackerearth.backend.account.model.Transaction;
import com.devsu.hackerearth.backend.account.model.dto.BankStatementDto;
import com.devsu.hackerearth.backend.account.model.dto.ClientDto;
import com.devsu.hackerearth.backend.account.model.dto.ResponseCode;
import com.devsu.hackerearth.backend.account.model.dto.TransactionDto;
import com.devsu.hackerearth.backend.account.repository.AccountRepository;
import com.devsu.hackerearth.backend.account.repository.TransactionRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TransactionServiceImpl implements TransactionService {

  private final TransactionRepository transactionRepository;
  private final TransactionMapper transactionMapper;
  private final DepositTransactionService depositTransactionService;
  private final AccountRepository accountRepository;
  private final ClientService clientService;

  public TransactionServiceImpl(TransactionRepository transactionRepository, TransactionMapper transactionMapper,
      DepositTransactionService depositTransactionService, AccountRepository accountRepository,
      ClientService clientService) {
    this.transactionRepository = transactionRepository;
    this.transactionMapper = transactionMapper;
    this.depositTransactionService = depositTransactionService;
    this.accountRepository = accountRepository;
    this.clientService = clientService;
  }

  @Override
  public List<TransactionDto> getAll() {
    return transactionRepository.findAll()
        .stream()
        .map(transactionMapper::entityToTransactionDto)
        .collect(Collectors.toList());
  }

  @Override
  public TransactionDto getById(Long id) {
    return transactionRepository.findById(id)
        .map(transactionMapper::entityToTransactionDto)
        .orElseThrow(() -> new ApiException(ResponseCode.TRANSACTION_NOT_FOUND));
  }

  @Async
  @Transactional
  @Override
  public CompletableFuture<TransactionDto> create(TransactionDto transactionDto) {

    return CompletableFuture.completedFuture(depositTransactionService.execute(transactionDto));
  }

  @Override
  public List<BankStatementDto> getAllByAccountClientIdAndDateBetween(Long clientId, Date dateTransactionStart,
      Date dateTransactionEnd) {
    log.info("Generate report by clientId {}", clientId);
    try {
      List<Account> accounts = accountRepository.findByClientId(clientId);

      if (accounts.isEmpty()) {
        return null;
      }

      List<BankStatementDto> accountsDto = new CopyOnWriteArrayList<>();
      List<TransactionDto> transactionsDto = new CopyOnWriteArrayList<>();

      ClientDto client = clientService.getClientById(clientId).getData();

      accounts.forEach(account -> {

        List<Transaction> transactionDtos = transactionRepository.findTransactionsByAccountIdAndDateRange(
            account.getId(),
            dateTransactionStart, dateTransactionEnd);

        if (!transactionDtos.isEmpty()) {
          transactionDtos
              .parallelStream().forEach(trn -> {
                transactionsDto.add(TransactionDto.builder()
                    .amount(trn.getAmount())
                    .balance(trn.getBalance())
                    .date(trn.getDate())
                    .type(trn.getType()).build());
              });
        }

        accountsDto.add(BankStatementDto.builder()
            .accountNumber(account.getNumber())
            .initialAmount(account.getInitialAmount())
            .isActive(account.isActive())
            .client(client.getName())
            .dni(client.getDni())
            .transactions(transactionsDto).build());
      });

      return accountsDto;

    } catch (Exception e) {
      log.error("Report detail trasaccion error: {}", e.getMessage());
      throw new ApiException(ResponseCode.DATABASE_ERROR);
    }

  }

}
