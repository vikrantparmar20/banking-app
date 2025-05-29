package com.banking.app.service;



import java.util.List;

import com.banking.app.dto.AccountDto;

public interface AccountService {
	AccountDto createAccountDto(AccountDto accountDto);
	AccountDto getAccountDto(Long id);
	AccountDto depositOrWithdraw(Long id, Double amount, Boolean isDeposit);
	List<AccountDto> getAllAccounts();
	
}
