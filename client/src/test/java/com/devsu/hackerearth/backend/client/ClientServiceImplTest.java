package com.devsu.hackerearth.backend.client;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.devsu.hackerearth.backend.client.mapper.ClientMapper;
import com.devsu.hackerearth.backend.client.model.Client;
import com.devsu.hackerearth.backend.client.model.dto.ClientDto;
import com.devsu.hackerearth.backend.client.repository.ClientRepository;
import com.devsu.hackerearth.backend.client.service.ClientServiceImpl;
import com.devsu.hackerearth.backend.client.utilTest.DataMock;

class ClientServiceImplTest {

	@InjectMocks
	private ClientServiceImpl clientServiceImpl;

	@Mock
	private ClientRepository clientRepository;

	@Mock
	private ClientMapper clientMapper;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	@DisplayName("find all clients")
	void findAll() {
		when(clientRepository.findAll()).thenReturn(DataMock.clients());
		when(clientMapper.entityToClientDto(any(Client.class))).thenReturn(DataMock.clientDto());

		List<ClientDto> response = clientServiceImpl.getAll();
		assertEquals(DataMock.clientsDtoList(), response);
	}

}
