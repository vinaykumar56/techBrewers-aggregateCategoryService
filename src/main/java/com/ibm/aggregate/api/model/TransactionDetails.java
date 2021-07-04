package com.ibm.aggregate.api.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionDetails {

	@Id
	private String _id;
	
	private String _rev;
	
	private int customerId;
	
	@DateTimeFormat(iso=ISO.DATE)
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate valueDate;
	
	@DateTimeFormat(iso=ISO.DATE)
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate transactionDate;
	
	private String transactionRemarks;
	
	private String transactionCategory;
	
	private double withdrawalAmount;
	
	private double depositAmount;
	
	private double balance;

	
	public TransactionDetails(){

	}
	public TransactionDetails(String _id, String _rev, int customerId, LocalDate valueDate, LocalDate transactionDate,
			String transactionRemarks, String transactionCategory, double withdrawalAmount, double depositAmount,
			double balance) {
		super();
		this._id = _id;
		this._rev = _rev;
		this.customerId = customerId;
		this.valueDate = valueDate;
		this.transactionDate = transactionDate;
		this.transactionRemarks = transactionRemarks;
		this.transactionCategory = transactionCategory;
		this.withdrawalAmount = withdrawalAmount;
		this.depositAmount = depositAmount;
		this.balance = balance;
	}



	public String get_id() {
		return _id;
	}



	public void set_id(String _id) {
		this._id = _id;
	}



	public String get_rev() {
		return _rev;
	}



	public void set_rev(String _rev) {
		this._rev = _rev;
	}



	public int getCustomerId() {
		return customerId;
	}



	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}



	public LocalDate getValueDate() {
		return valueDate;
	}



	public void setValueDate(LocalDate valueDate) {
		this.valueDate = valueDate;
	}



	public LocalDate getTransactionDate() {
		return transactionDate;
	}



	public void setTransactionDate(LocalDate transactionDate) {
		this.transactionDate = transactionDate;
	}



	public String getTransactionRemarks() {
		return transactionRemarks;
	}



	public void setTransactionRemarks(String transactionRemarks) {
		this.transactionRemarks = transactionRemarks;
	}



	public String getTransactionCategory() {
		return transactionCategory;
	}



	public void setTransactionCategory(String transactionCategory) {
		this.transactionCategory = transactionCategory;
	}



	public double getWithdrawalAmount() {
		return withdrawalAmount;
	}



	public void setWithdrawalAmount(double withdrawalAmount) {
		this.withdrawalAmount = withdrawalAmount;
	}



	public double getDepositAmount() {
		return depositAmount;
	}



	public void setDepositAmount(double depositAmount) {
		this.depositAmount = depositAmount;
	}



	public double getBalance() {
		return balance;
	}



	public void setBalance(double balance) {
		this.balance = balance;
	}



	@Override
	public String toString() {
		return "TransactionDetails [_id=" + _id + ", _rev=" + _rev + ", customerId=" + customerId + ", valueDate="
				+ valueDate + ", transactionDate=" + transactionDate + ", transactionRemarks=" + transactionRemarks
				+ ", transactionCategory=" + transactionCategory + ", withdrawalAmount=" + withdrawalAmount
				+ ", depositAmount=" + depositAmount + ", balance=" + balance + "]";
	}
	
	
}
