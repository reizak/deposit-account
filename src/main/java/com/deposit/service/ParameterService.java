package com.deposit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deposit.model.Parameter;
import com.deposit.repository.IParameterRepository;

@Service
public class ParameterService implements IParameterService{
	
	@Autowired
	IParameterRepository parameterRepository;

	@Override
	public Parameter findByCode(String code) {		
		return parameterRepository.findByCode(code);
	}
	
	
	

}
