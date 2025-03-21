package com.devsu.hackerearth.backend.account;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.devsu.hackerearth.backend.account.controller.AccountController;
import com.devsu.hackerearth.backend.account.feign.ClientService;
import com.devsu.hackerearth.backend.account.model.dto.AccountDto;
import com.devsu.hackerearth.backend.account.model.dto.ClientDto;
import com.devsu.hackerearth.backend.account.model.dto.GenericResponseDto;
import com.devsu.hackerearth.backend.account.service.AccountService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class sampleTest {

	@Autowired
    private MockMvc mockMvc;

	@MockBean
	private AccountService accountService;

	@MockBean
	private AccountController accountController;

	@MockBean
    private ClientService clientService;

	@Test
	void createAccountTest() throws Exception {
		AccountDto newAccount = new AccountDto(1L, "number", "savings", 0.0, true, 1L);
		AccountDto createdAccount = new AccountDto(1L, "number", "savings", 0.0, true, 1L);
		when(accountService.create(newAccount)).thenReturn(createdAccount);
		GenericResponseDto<ClientDto> dataMockClient = new GenericResponseDto<ClientDto>(clientDto());

        when(clientService.getClientById(1L)).thenReturn(dataMockClient);

		String request = "{"
						+ "\"id\":0,"
						+ "\"number\":\"10365895\","
						+ "\"type\":\"TEST\","
						+ "\"initialAmount\":7000,"
						+ "\"isActive\":true,"
						+ "\"clientId\":1"
						+ "}";

		mockMvc.perform(post("/api/accounts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request))
                .andExpect(status().isOk());
	}

	public static ClientDto clientDto(){
        return ClientDto.builder()
        .address("carrera")
        .name("test maria")
        .age(26)
        .dni("1036681035")
        .gender("female")
        .isActive(true)
        .id(1L)
        .build();
    }
}

