package com.ibm.aggregate.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.aggregate.api.model.CustomerCategoryDetails;
import com.ibm.aggregate.api.model.TransactionDetails;
import com.ibm.aggregate.controller.RestController;

@Service
public class Transactions {

	@Autowired
	RestController restController;
	
	
	public void aggregateCategoryTransactions() {
		
		List<TransactionDetails> transactionDetailsList=restController.getTransactions(null);
		List<CustomerCategoryDetails> categoryDetailsList;
		for(TransactionDetails transactionDetails:transactionDetailsList) {
			
			transactionDetails.getCustomerId();
		}
	}
	

}
