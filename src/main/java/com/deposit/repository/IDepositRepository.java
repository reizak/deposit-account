package com.deposit.repository;

import org.springframework.data.repository.CrudRepository;

import com.deposit.model.Deposit;

public interface IDepositRepository extends CrudRepository<Deposit, Long>{
	
	public Deposit findByAccountNumber(String accountNumber);

}
