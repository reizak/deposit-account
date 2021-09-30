package com.deposit.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tb_deposit")
public class Deposit extends AuditBase{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@JsonIgnore
	@Column(name = "account_number")
	private String accountNumber;
	
	@Column(name = "tenor")
	private String tenor;
	
	@Column(name = "first_deposit_amount")
	private String firstDepositAmount;
	
	@Column(name = "saving_purpose")
	private String savingPurpose;
	
	@Column(name = "monthly_deposit_amount")
	private String monthlyDepositAmount;
	
	@JsonIgnore
	@Column(updatable=false, name = "created_by")
	private String createdBy;

	@JsonIgnore
	@Column(updatable=false, name = "created_date")
	private Date createdDate;

	@JsonIgnore
	@Column(name = "modified_by")
	private String modifiedBy;

	@JsonIgnore
	@Column(name = "modified_date")
	private Date modifiedDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getTenor() {
		return tenor;
	}

	public void setTenor(String tenor) {
		this.tenor = tenor;
	}

	public String getFirstDepositAmount() {
		return firstDepositAmount;
	}

	public void setFirstDepositAmount(String firstDepositAmount) {
		this.firstDepositAmount = firstDepositAmount;
	}

	public String getSavingPurpose() {
		return savingPurpose;
	}

	public void setSavingPurpose(String savingPurpose) {
		this.savingPurpose = savingPurpose;
	}

	public String getMonthlyDepositAmount() {
		return monthlyDepositAmount;
	}

	public void setMonthlyDepositAmount(String monthlyDepositAmount) {
		this.monthlyDepositAmount = monthlyDepositAmount;
	}
	
	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
}

