package com.deposit.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.deposit.model.Account;

public interface IAccountRepository extends CrudRepository<Account, Long>{
	
//	public boolean existsByReferenceNo(String referenceNo);
	
	public Account findByAccountNumber(String accountNumber);
	
	@Query(value = "SELECT generateAccountNumber()", nativeQuery = true)
	public String  generateAccountNumber();


}
