package com.banking.app.service.implementation;

import org.springframework.stereotype.Service;

import com.banking.app.dto.AccountDto;
import com.banking.app.entity.Account;
import com.banking.app.mapper.AccountMapper;
import com.banking.app.repository.AccountRepo;
import com.banking.app.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {
	
	private AccountRepo accountRepo;
	
	public AccountServiceImpl(AccountRepo accountRepo) {
		// TODO Auto-generated constructor stub
		this.accountRepo = accountRepo;
	}

	@Override
	public AccountDto createAccountDto(AccountDto accountDto) {
		// TODO Auto-generated method stub
		Account account = AccountMapper.mapToAccount(accountDto);
		Account savedAccount = accountRepo.save(account);
		return AccountMapper.mapToAccountDto(savedAccount);
	}
	
	@Override 
	public AccountDto getAccountDto(Long id) {
		Account account = accountRepo.findById(id).orElseThrow(()-> new RuntimeException("Account does not exist"));
		return AccountMapper.mapToAccountDto(account);
	}
}
