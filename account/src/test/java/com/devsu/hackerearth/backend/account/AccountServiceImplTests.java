package com.devsu.hackerearth.backend.account;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.devsu.hackerearth.backend.account.mapper.AccountMapper;
import com.devsu.hackerearth.backend.account.model.Account;
import com.devsu.hackerearth.backend.account.model.dto.AccountDto;
import com.devsu.hackerearth.backend.account.repository.AccountRepository;
import com.devsu.hackerearth.backend.account.service.AccountServiceImpl;
import com.devsu.hackerearth.backend.account.utilTest.DataMockAccount;

class AccountServiceImplTests {

	@InjectMocks
	private AccountServiceImpl accountServiceImpl;

	@Mock
	private AccountMapper accountMapper;

	@Mock
	private AccountRepository accountRepository;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	@DisplayName("find all accounts")
	void findAll() {
		when(accountRepository.findAll()).thenReturn(DataMockAccount.accounts());
		when(accountMapper.entityToAccountDto(any(Account.class))).thenReturn(DataMockAccount.accountDto());

		List<AccountDto> response = accountServiceImpl.getAll();
		assertEquals(DataMockAccount.accountDtosList(), response);
	}

}
