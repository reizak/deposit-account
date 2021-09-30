package com.deposit.repository;

import org.springframework.data.repository.CrudRepository;

import com.deposit.model.Parameter;

public interface IParameterRepository extends CrudRepository<Parameter, Long>{
	
	public Parameter findByCode(String code);

}
