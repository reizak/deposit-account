package com.deposit.service;

import java.util.List;

import com.deposit.model.Account;

public interface IAccountService {
	public Account findByAccountNumber(String accountNumber);
	
	public List<Account> findAll();
	
	public void save(Account account);
	
	public String generateAccountNumber();

}
