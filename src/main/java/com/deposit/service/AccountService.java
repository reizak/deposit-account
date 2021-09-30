package com.deposit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deposit.model.Account;
import com.deposit.repository.IAccountRepository;

@Service
public class AccountService implements IAccountService{
	
	@Autowired
	IAccountRepository accountRepository;

	@Override
	public Account findByAccountNumber(String accountNumber) {
		return accountRepository.findByAccountNumber(accountNumber);
	}
	
	@Override
	public List<Account> findAll() {
		return (List<Account>) accountRepository.findAll();
	}
	
	@Override
	public void save(Account account) {
		accountRepository.save(account);		
	}

	@Override
	public String generateAccountNumber() {
		return accountRepository.generateAccountNumber();
	}

}
