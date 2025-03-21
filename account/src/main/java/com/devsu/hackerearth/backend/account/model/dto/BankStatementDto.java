package com.devsu.hackerearth.backend.account.model.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class BankStatementDto {
    
	private String client;
	private String dni;
	private String accountNumber;
	private double initialAmount;
    private boolean isActive;
	private List<TransactionDto> transactions;
}
