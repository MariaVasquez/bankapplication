package com.devsu.hackerearth.backend.account.model.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class TransactionDto {

	@JsonIgnore
	private Long id;
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date date;
	private String type;
	private double amount;
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private double balance;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private Long accountId;
}
