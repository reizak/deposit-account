package com.deposit.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.deposit.model.Customer;

public interface ICustomerRepository extends CrudRepository<Customer, Long>{
	
	public Customer findByLegalIdNumber(String legalIdNumber);
	
	@Query(value = "SELECT generateCustomerNumber()", nativeQuery = true)
	public String  generateCustomerNumber();

}
