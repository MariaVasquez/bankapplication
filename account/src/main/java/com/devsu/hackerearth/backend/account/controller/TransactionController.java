package com.devsu.hackerearth.backend.account.controller;

import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.devsu.hackerearth.backend.account.exception.ErrorResponseDTO;
import com.devsu.hackerearth.backend.account.model.dto.BankStatementDto;
import com.devsu.hackerearth.backend.account.model.dto.GenericResponseDto;
import com.devsu.hackerearth.backend.account.model.dto.TransactionDto;
import com.devsu.hackerearth.backend.account.service.TransactionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/transactions")
@Tag(name = "Transaction", description = "Transaction administrator")
public class TransactionController {
    
    private final TransactionService transactionService;

	public TransactionController(TransactionService transactionService) {
		this.transactionService = transactionService;
	}

	@GetMapping
	@Operation(summary = "Find all transactions", tags = { "transactions", "GET" })
	@ResponseStatus(HttpStatus.OK)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Transaction list"),
			@ApiResponse(responseCode = "500", description = "Internal server error", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class))
			})
	})
    public GenericResponseDto<List<TransactionDto>> getAll(){
		return new GenericResponseDto<List<TransactionDto>>(transactionService.getAll());
	}

	@GetMapping("/{id}")
	@Operation(summary = "Find a transaction by id", tags = { "transactions", "GET" })
	@ResponseStatus(HttpStatus.OK)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Transaction get by id"),
			@ApiResponse(responseCode = "404", description = "Transaction not found", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class))
			}),
			@ApiResponse(responseCode = "500", description = "Internal server error", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class))
			})
	})
    public GenericResponseDto<TransactionDto> get(@PathVariable Long id){
		return new GenericResponseDto<TransactionDto>(transactionService.getById(id));
	}

	@PostMapping
	@Operation(summary = "Create a transaction", tags = { "transactions", "POST" })
	@ResponseStatus(HttpStatus.OK)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Transaction create"),
			@ApiResponse(responseCode = "404", description = "Transaction not found", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class))
			}),
			@ApiResponse(responseCode = "500", description = "Internal server error", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class))
			})
	})
	public CompletableFuture<GenericResponseDto<TransactionDto>> create(@RequestBody TransactionDto transactionDto){
		return transactionService.create(transactionDto).thenApply(response -> new GenericResponseDto<>(response));
	}

	@GetMapping("/clients/{clientId}/report")
	@Operation(summary = "Find a detail transaction by clientId", tags = { "transactions", "GET" })
	@ResponseStatus(HttpStatus.OK)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Report detail transactions"),
			@ApiResponse(responseCode = "500", description = "Internal server error", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class))
			})
	})
    public GenericResponseDto<List<BankStatementDto>> report(@PathVariable Long clientId, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateTransactionStart, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateTransactionEnd) {
        return new GenericResponseDto<List<BankStatementDto>>(transactionService.getAllByAccountClientIdAndDateBetween(clientId, dateTransactionStart, dateTransactionEnd));
	}
}
