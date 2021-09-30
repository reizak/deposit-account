package com.deposit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deposit.model.Customer;
import com.deposit.repository.ICustomerRepository;

@Service
public class CustomerService implements ICustomerService{
	
	@Autowired
	private ICustomerRepository customerRepository;

	@Override
	public Customer findByCustomerNumber(String customerNumber) {
		return null;
	}

	@Override
	public void save(Customer customer) {
		customerRepository.save(customer);
		
	}

	@Override
	public Customer findByLegalIdNumber(String legalIdNumber) {
		return customerRepository.findByLegalIdNumber(legalIdNumber);
	}

	@Override
	public String generateCustomerNumber() {
		return customerRepository.generateCustomerNumber();
	}

	

}
