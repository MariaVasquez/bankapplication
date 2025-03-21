package com.devsu.hackerearth.backend.client.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.devsu.hackerearth.backend.client.exception.ErrorResponseDTO;
import com.devsu.hackerearth.backend.client.model.dto.ClientDto;
import com.devsu.hackerearth.backend.client.model.dto.GenericResponseDto;
import com.devsu.hackerearth.backend.client.model.dto.PartialClientDto;
import com.devsu.hackerearth.backend.client.service.ClientService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/clients")
@Tag(name = "Client", description = "Client administrator")
public class ClientController {

	private final ClientService clientService;

	public ClientController(ClientService clientService) {
		this.clientService = clientService;
	}

	@GetMapping
	@Operation(summary = "Find all clients", tags = { "clients", "GET" })
	@ResponseStatus(HttpStatus.OK)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Client list"),
			@ApiResponse(responseCode = "500", description = "Internal server error", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class))
			})
	})
	public GenericResponseDto<List<ClientDto>> getAll() {
		return new GenericResponseDto<List<ClientDto>>(clientService.getAll());
	}

	@GetMapping("/{id}")
	@Operation(summary = "Find a  client by id", tags = { "clients", "GET" })
	@ResponseStatus(HttpStatus.OK)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Client get by id"),
			@ApiResponse(responseCode = "404", description = "Client not found", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class))
			}),
			@ApiResponse(responseCode = "500", description = "Internal server error", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class))
			})
	})
	public GenericResponseDto<ClientDto> get(@PathVariable Long id) {
		return new GenericResponseDto<ClientDto>(clientService.getById(id));
	}

	@PostMapping
	@Operation(summary = "Create a client", tags = { "clients", "POST" })
	@ResponseStatus(HttpStatus.OK)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Client create"),
			@ApiResponse(responseCode = "404", description = "Client not found", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class))
			}),
			@ApiResponse(responseCode = "500", description = "Internal server error", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class))
			})
	})
	public GenericResponseDto<ClientDto> create(@RequestBody ClientDto clientDto) {
		return new GenericResponseDto<ClientDto>(clientService.create(clientDto));
	}

	@PutMapping
	@Operation(summary = "Update a client", tags = { "clients", "PUT" })
	@ResponseStatus(HttpStatus.OK)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Client update"),
			@ApiResponse(responseCode = "404", description = "Client not found", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class))
			}),
			@ApiResponse(responseCode = "500", description = "Internal server error", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class))
			})
	})
	public GenericResponseDto<ClientDto> update(@RequestBody ClientDto clientDto) {
		return new GenericResponseDto<ClientDto>(clientService.update(clientDto));
	}

	@PutMapping("/{id}")
	@Operation(summary = "Update a client by id", tags = { "clients", "PUT" })
	@ResponseStatus(HttpStatus.OK)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Client update"),
			@ApiResponse(responseCode = "404", description = "Client not found", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class))
			}),
			@ApiResponse(responseCode = "500", description = "Internal server error", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class))
			})
	})
	public GenericResponseDto<ClientDto> partialUpdate(@PathVariable Long id,
			@RequestBody PartialClientDto partialClientDto) {
		return new GenericResponseDto<ClientDto>(clientService.partialUpdate(id, partialClientDto));
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Delete a client", tags = { "clients", "DELETE" })
	@ResponseStatus(HttpStatus.OK)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Client delete"),
			@ApiResponse(responseCode = "404", description = "Client not found", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class))
			}),
			@ApiResponse(responseCode = "500", description = "Internal server error", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class))
			})
	})
	public void delete(@PathVariable Long id) {
		clientService.deleteById(id);
	}
}
