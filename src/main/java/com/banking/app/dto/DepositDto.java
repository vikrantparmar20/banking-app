package com.banking.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DepositDto {
	private Long id;
	private Double amount;
	
}
