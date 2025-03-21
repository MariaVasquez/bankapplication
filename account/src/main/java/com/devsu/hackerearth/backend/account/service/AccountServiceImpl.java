package com.devsu.hackerearth.backend.account.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.devsu.hackerearth.backend.account.exception.ApiException;
import com.devsu.hackerearth.backend.account.feign.ClientService;
import com.devsu.hackerearth.backend.account.mapper.AccountMapper;
import com.devsu.hackerearth.backend.account.model.dto.AccountDto;
import com.devsu.hackerearth.backend.account.model.dto.PartialAccountDto;
import com.devsu.hackerearth.backend.account.model.dto.ResponseCode;
import com.devsu.hackerearth.backend.account.repository.AccountRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

  private final AccountRepository accountRepository;
  private final AccountMapper accountMapper;
  private final ClientService clientService;

  public AccountServiceImpl(AccountRepository accountRepository, AccountMapper accountMapper,
      ClientService clientService) {
    this.accountRepository = accountRepository;
    this.accountMapper = accountMapper;
    this.clientService = clientService;
  }

  @Override
  public List<AccountDto> getAll() {
    return accountRepository.findAll()
        .stream()
        .map(accountMapper::entityToAccountDto)
        .collect(Collectors.toList());
  }

  @Override
  public AccountDto getById(Long id) {
    return accountRepository.findById(id)
        .map(accountMapper::entityToAccountDto)
        .orElseThrow(() -> new ApiException(ResponseCode.ACCOUNT_NOT_FOUND));
  }

  @Transactional
  @Override
  public AccountDto create(AccountDto accountDto) {
    log.info("Create account for clientId {}", accountDto.getClientId());
    String statusValidateClient = clientService.getClientById(accountDto.getClientId()).getStatus();

    if (statusValidateClient.contains("TRAPPCLI_ERR")) {
      throw new ApiException(ResponseCode.CLIENT_NOT_FOUND);
    }

    try {
      return accountMapper.entityToAccountDto(accountRepository.save(accountMapper.accountDtoToEntity(accountDto)));
    } catch (Exception e) {
      log.error("Create account service error: {}", e.getMessage());
      throw new ApiException(ResponseCode.DATABASE_ERROR);
    }
  }

  @Transactional
  @Override
  public AccountDto update(AccountDto accountDto) {
    try {
      return accountMapper.entityToAccountDto(accountRepository.save(accountMapper.accountDtoToEntity(accountDto)));
    } catch (Exception e) {
      log.error("Edit account service error: {}", e.getMessage());
      throw new ApiException(ResponseCode.DATABASE_ERROR);
    }
  }

  @Transactional
  @Override
  public AccountDto partialUpdate(Long id, PartialAccountDto partialAccountDto) {
    try {
      return accountRepository.findById(id)
          .map(account -> {
            account.setActive(partialAccountDto.isActive());
            return accountRepository.save(account);
          })
          .map(accountMapper::entityToAccountDto)
          .orElseThrow(() -> new ApiException(ResponseCode.ACCOUNT_NOT_FOUND));
    } catch (Exception e) {
      log.error("Edit by id account service error: {}", e.getMessage());
      throw new ApiException(ResponseCode.DATABASE_ERROR);
    }
  }

  @Transactional
  @Override
  public void deleteById(Long id) {
    log.info("Init delete for accountId {}", id);
    try {
      accountRepository.deleteById(id);
    } catch (Exception e) {
      log.error("Delete by id account service error: {}", e.getMessage());
      throw new ApiException(ResponseCode.DATABASE_ERROR);
    }
  }

}
