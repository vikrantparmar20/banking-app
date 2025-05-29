package com.banking.app.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.banking.app.dto.AccountDto;
import com.banking.app.dto.DepositDto;
import com.banking.app.service.AccountService;
import com.banking.app.service.implementation.AccountServiceImpl;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
	private AccountService accountService;
	
	public AccountController(AccountService accountService) {
		// TODO Auto-generated constructor stub
		this.accountService = accountService;
	}
	
	@PostMapping
	public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto){
		return new ResponseEntity<>(accountService.createAccountDto(accountDto),HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<AccountDto> getAccount(@PathVariable Long id){
		return ResponseEntity.ok(accountService.getAccountDto(id));
	}
	
	@PutMapping("/deposit")
	public ResponseEntity<AccountDto> deposit( @RequestBody DepositDto depositDto){
		
		AccountDto accountDto = accountService.depositOrWithdraw(depositDto.getId(),depositDto.getAmount(), true );
		return ResponseEntity.ok(accountDto);
	}
	
	@PutMapping("/withdraw")
	public ResponseEntity<AccountDto> withdraw( @RequestBody DepositDto depositDto){
		
		AccountDto accountDto = accountService.depositOrWithdraw(depositDto.getId(),depositDto.getAmount(), false );
		return ResponseEntity.ok(accountDto);
	}
	
	@GetMapping("/allAccounts")
	public ResponseEntity<List<AccountDto>> getAllAccounts(){
		return ResponseEntity.ok(accountService.getAllAccounts());
	}
}
