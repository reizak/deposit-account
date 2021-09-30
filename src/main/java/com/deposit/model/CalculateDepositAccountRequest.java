package com.deposit.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CalculateDepositAccountRequest {	
	
	@JsonProperty("savingTenor")
	private int savingTenor;
	
	@JsonProperty("firstDepositAmount")
	private BigDecimal firstDepositAmount;
	
	@JsonProperty("monthlyDepositAmount")
	private BigDecimal monthlyDepositAmount;
	
	@JsonProperty("purposeOfSaving")
	private String purposeOfSaving;

	public int getSavingTenor() {
		return savingTenor;
	}

	public void setSavingTenor(int savingTenor) {
		this.savingTenor = savingTenor;
	}

	public BigDecimal getFirstDepositAmount() {
		return firstDepositAmount;
	}

	public void setFirstDepositAmount(BigDecimal firstDepositAmount) {
		this.firstDepositAmount = firstDepositAmount;
	}

	public BigDecimal getMonthlyDepositAmount() {
		return monthlyDepositAmount;
	}

	public void setMonthlyDepositAmount(BigDecimal monthlyDepositAmount) {
		this.monthlyDepositAmount = monthlyDepositAmount;
	}

	public String getPurposeOfSaving() {
		return purposeOfSaving;
	}

	public void setPurposeOfSaving(String purposeOfSaving) {
		this.purposeOfSaving = purposeOfSaving;
	}
	

}
