package com.deposit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deposit.model.Deposit;
import com.deposit.repository.IDepositRepository;

@Service
public class DepositService implements IDepositService{
	
	@Autowired
	IDepositRepository depositRepository;

	@Override
	public Deposit findByAccountNumber(String accountNumber) {
		return depositRepository.findByAccountNumber(accountNumber);
	}

	@Override
	public void save(Deposit deposit) {
		depositRepository.save(deposit);		
	}
	

}
