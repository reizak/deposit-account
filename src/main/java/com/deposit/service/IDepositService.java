package com.deposit.service;

import com.deposit.model.Deposit;

public interface IDepositService {
	public Deposit findByAccountNumber(String accountNumber);
	
	public void save(Deposit deposit);

}
