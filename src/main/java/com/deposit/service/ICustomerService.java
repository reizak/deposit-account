package com.deposit.service;

import com.deposit.model.Customer;

public interface ICustomerService {

	public Customer findByCustomerNumber(String customerNumber);
	
	public Customer findByLegalIdNumber(String legalIdNumber);

	public void save(Customer customer);
	
	public String  generateCustomerNumber();

}
