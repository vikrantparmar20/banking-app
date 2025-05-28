package com.banking.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banking.app.entity.Account;

public interface AccountRepo extends JpaRepository<Account, Long>{
	
}
