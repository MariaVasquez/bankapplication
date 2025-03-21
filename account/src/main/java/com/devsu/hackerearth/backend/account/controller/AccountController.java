package com.devsu.hackerearth.backend.account.controller;

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

import com.devsu.hackerearth.backend.account.exception.ErrorResponseDTO;
import com.devsu.hackerearth.backend.account.model.dto.AccountDto;
import com.devsu.hackerearth.backend.account.model.dto.GenericResponseDto;
import com.devsu.hackerearth.backend.account.model.dto.PartialAccountDto;
import com.devsu.hackerearth.backend.account.service.AccountService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/accounts")
@Tag(name = "Account", description = "Account administrator")
public class AccountController {

	private final AccountService accountService;

	public AccountController(AccountService accountService) {
		this.accountService = accountService;
	}

	@GetMapping
	@Operation(summary = "Find all accounts", tags = { "accounts", "GET" })
	@ResponseStatus(HttpStatus.OK)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Accounts list"),
			@ApiResponse(responseCode = "500", description = "Internal server error", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class))
			})
	})
	public GenericResponseDto<List<AccountDto>> getAll(){
		return new GenericResponseDto<List<AccountDto>>(accountService.getAll());
	}

	@GetMapping("/{id}")
	@Operation(summary = "Find a accounts by id", tags = { "accounts", "GET" })
	@ResponseStatus(HttpStatus.OK)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "account get by id"),
			@ApiResponse(responseCode = "404", description = "account not found", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class))
			}),
			@ApiResponse(responseCode = "500", description = "Internal server error", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class))
			})
	})
	public GenericResponseDto<AccountDto> get(@PathVariable Long id){
		return new GenericResponseDto<AccountDto>(accountService.getById(id));
	}

	@PostMapping
	@Operation(summary = "Create a account", tags = { "accounts", "POST" })
	@ResponseStatus(HttpStatus.OK)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Accounts create"),
			@ApiResponse(responseCode = "404", description = "Accounts not found", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class))
			}),
			@ApiResponse(responseCode = "500", description = "Internal server error", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class))
			})
	})
	public GenericResponseDto<AccountDto> create(@RequestBody AccountDto accountDto){
		return new GenericResponseDto<AccountDto>(accountService.create(accountDto));
	}

	@PutMapping
	@Operation(summary = "Update a account", tags = { "accounts", "PUT" })
	@ResponseStatus(HttpStatus.OK)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Accounts update"),
			@ApiResponse(responseCode = "404", description = "Accounts not found", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class))
			}),
			@ApiResponse(responseCode = "500", description = "Internal server error", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class))
			})
	})
	public GenericResponseDto<AccountDto> update(@RequestBody AccountDto accountDto){
		return new GenericResponseDto<AccountDto>(accountService.update(accountDto));
	}

	@PutMapping("/{id}")
	@Operation(summary = "Update a account by id", tags = { "account", "PUT" })
	@ResponseStatus(HttpStatus.OK)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Account update"),
			@ApiResponse(responseCode = "404", description = "Account not found", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class))
			}),
			@ApiResponse(responseCode = "500", description = "Internal server error", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class))
			})
	})
	public GenericResponseDto<AccountDto> partialUpdate(@PathVariable Long id, @RequestBody PartialAccountDto partialAccountDto){
		return new GenericResponseDto<AccountDto>(accountService.partialUpdate(id, partialAccountDto));
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Delete a account", tags = { "account", "DELETE" })
	@ResponseStatus(HttpStatus.OK)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Account delete"),
			@ApiResponse(responseCode = "404", description = "Account not found", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class))
			}),
			@ApiResponse(responseCode = "500", description = "Internal server error", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class))
			})
	})
	public void delete(@PathVariable Long id){
		accountService.deleteById(id);
	}
}

