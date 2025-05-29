package com.banking.app.service.implementation;

import java.util.ArrayList;
import java.util.List;

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
		// TODO Auto-generated constructor stub
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

	@Override
	public AccountDto depositOrWithdraw(Long id, Double amount, Boolean isDeposit) {
		// TODO Auto-generated method stub 
		Account account = accountRepo.findById(id).orElseThrow(()-> new RuntimeException("Account does not exist"));
		double total = 0;
		if(isDeposit) {
			total = account.getBalance()+amount;
		}
		else {
			if(account.getBalance()>amount) {
				total = account.getBalance()-amount;
			}
			else {
				throw new RuntimeException("Insufficient funds");
			}
		}
		
		account.setBalance(total);
		Account savedAccount = accountRepo.save(account);
		return AccountMapper.mapToAccountDto(savedAccount);
	}

	@Override
	public List<AccountDto> getAllAccounts() {
		// TODO Auto-generated method stub
		List<Account> list = accountRepo.findAll();
		List<AccountDto> listDto = new ArrayList<>();
		list.forEach( (e) -> listDto.add( AccountMapper.mapToAccountDto(e)));
		return listDto;
	}


	

	
}
