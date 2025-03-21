package com.devsu.hackerearth.backend.client.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.devsu.hackerearth.backend.client.exception.ApiException;
import com.devsu.hackerearth.backend.client.mapper.ClientMapper;
import com.devsu.hackerearth.backend.client.model.dto.ClientDto;
import com.devsu.hackerearth.backend.client.model.dto.PartialClientDto;
import com.devsu.hackerearth.backend.client.model.dto.ResponseCode;
import com.devsu.hackerearth.backend.client.repository.ClientRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ClientServiceImpl implements ClientService {

	private final ClientRepository clientRepository;

	private final ClientMapper clientMapper;

	public ClientServiceImpl(ClientRepository clientRepository, ClientMapper clientMapper) {
		this.clientRepository = clientRepository;
		this.clientMapper = clientMapper;
	}

	@Override
	public List<ClientDto> getAll() {
		return clientRepository.findAll()
				.stream()
				.map(clientMapper::entityToClientDto)
				.collect(Collectors.toList());
	}

	@Override
	public ClientDto getById(Long id) {
		return clientRepository.findById(id)
				.map(clientMapper::entityToClientDto)
				.orElseThrow(() -> new ApiException(ResponseCode.CLIENT_NOT_FOUND));
	}

	@Transactional
	@Override
	public ClientDto create(ClientDto clientDto) {
		try {
			return clientMapper.entityToClientDto(clientRepository.save(clientMapper.clientDtoToEntity(clientDto)));
		} catch (Exception e) {
			log.error("Create client service error: {}", e.getMessage());
			throw new ApiException(ResponseCode.DATABASE_ERROR);
		}
	}

	@Transactional
	@Override
	public ClientDto update(ClientDto clientDto) {
		try {
			return clientMapper.entityToClientDto(clientRepository.save(clientMapper.clientDtoToEntity(clientDto)));
		} catch (Exception e) {
			log.error("Edit client service error: {}", e.getMessage());
			throw new ApiException(ResponseCode.DATABASE_ERROR);
		}
	}

	@Transactional
	@Override
	public ClientDto partialUpdate(Long id, PartialClientDto partialClientDto) {
		try {
			return clientRepository.findById(id)
					.map(client -> {
						client.setActive(partialClientDto.isActive());
						return clientRepository.save(client);
					})
					.map(clientMapper::entityToClientDto)
					.orElseThrow(() -> new ApiException(ResponseCode.CLIENT_NOT_FOUND));
		} catch (Exception e) {
			log.error("Edit by id client service error: {}", e.getMessage());
			throw new ApiException(ResponseCode.DATABASE_ERROR);
		}
	}

	@Transactional
	@Override
	public void deleteById(Long id) {
		log.info("Init delete for clintId {}", id);
		try {
			clientRepository.deleteById(id);
		} catch (Exception e) {
			log.error("Delete by id client service error: {}", e.getMessage());
			throw new ApiException(ResponseCode.DATABASE_ERROR);
		}
	}
}
