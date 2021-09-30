package com.deposit.model;

import javax.persistence.Column;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateDepositAccountRequest {
	
	
	@JsonProperty("customerNumber")
	private String customerNumber;
	
	@Transient
	private String accountNumber;
	
	@JsonProperty("accountName")
	private String accountName;
	
	@JsonProperty("legalIdType")
	private String legalIdType;
	
	@JsonProperty("legalIdNumber")
	private String legalIdNumber;
	
	@JsonProperty("dateOfBirth")
	private String dateOfBirth;
	
	@JsonProperty("placeOfBirth")
	private String placeOfBirth;
	
	@JsonProperty("savingTenor")
	private String savingTenor;
	
	@JsonProperty("firstDepositAmount")
	private String firstDepositAmount;
	
	@JsonProperty("monthlyDepositAmount")
	private String monthlyDepositAmount;
	
	@JsonProperty("purposeOfSaving")
	private String purposeOfSaving;
	
	@Column(name = "mobilePhone")
	private String mobilePhone;

	public String getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getLegalIdType() {
		return legalIdType;
	}

	public void setLegalIdType(String legalIdType) {
		this.legalIdType = legalIdType;
	}

	public String getLegalIdNumber() {
		return legalIdNumber;
	}

	public void setLegalIdNumber(String legalIdNumber) {
		this.legalIdNumber = legalIdNumber;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getPlaceOfBirth() {
		return placeOfBirth;
	}

	public void setPlaceOfBirth(String placeOfBirth) {
		this.placeOfBirth = placeOfBirth;
	}

	public String getSavingTenor() {
		return savingTenor;
	}

	public void setSavingTenor(String savingTenor) {
		this.savingTenor = savingTenor;
	}

	public String getFirstDepositAmount() {
		return firstDepositAmount;
	}

	public void setFirstDepositAmount(String firstDepositAmount) {
		this.firstDepositAmount = firstDepositAmount;
	}

	public String getMonthlyDepositAmount() {
		return monthlyDepositAmount;
	}

	public void setMonthlyDepositAmount(String monthlyDepositAmount) {
		this.monthlyDepositAmount = monthlyDepositAmount;
	}

	public String getPurposeOfSaving() {
		return purposeOfSaving;
	}

	public void setPurposeOfSaving(String purposeOfSaving) {
		this.purposeOfSaving = purposeOfSaving;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	
}
