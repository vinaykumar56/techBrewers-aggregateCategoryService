package com.ibm.aggregate.api.service;

import java.util.List;

import com.ibm.aggregate.api.client.CallCategoryDetails;
import com.ibm.aggregate.api.client.CallTransactionDetails;
import com.ibm.aggregate.api.model.AggregateCategoryDetails;
import com.ibm.aggregate.api.model.CustomerCategoryDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.ibm.aggregate.api.model.TransactionDetails;

@Component
@Service
public class TransactionService implements CommandLineRunner {

	@Autowired
	private CallCategoryDetails categoryDetails;
	@Autowired
	private CallTransactionDetails transactionDetails;

	@Override
	public void run(String... args) throws Exception {
		this.aggregateCategoryTransactions();
	}
	
	public void aggregateCategoryTransactions() {
		boolean customerIdMatched=false;
		List<TransactionDetails> transactionDetailsList = transactionDetails.getTransactionDetails();
		List<CustomerCategoryDetails> categoryDetailsList =	categoryDetails.getCategoryDetails();
		System.out.println("transaction size="+transactionDetailsList.size());
		System.out.println("transaction details ="+transactionDetailsList);
		System.out.println("category details size ="+categoryDetailsList.size());
		System.out.println("category details ="+categoryDetailsList);
//		List<CustomerCategoryDetails> categoryDetailsList;
		for(TransactionDetails transactionDetails:transactionDetailsList) {

			int tCustomerId= transactionDetails.getCustomerId();

			customerIdMatched=false;
			for (CustomerCategoryDetails categoryDetails:categoryDetailsList){
				int cCustomerId= categoryDetails.getCustomerId();

				if(tCustomerId == cCustomerId){
					this.updateTransactionCategoryDetails(transactionDetails,
							categoryDetails.getTransactionCategoryDetails());
					customerIdMatched=true;
				}
			}
			if(! customerIdMatched){
				this.addTransactionCategoryDetails(transactionDetails,categoryDetailsList);
			}
		}
	}

	private void addTransactionCategoryDetails(TransactionDetails transactionDetails,
											   List<CustomerCategoryDetails> categoryDetailsList) {


	}



	private void updateTransactionCategoryDetails(TransactionDetails transactionDetails,
												  List<AggregateCategoryDetails> transactionCategoryDetails) {


	}


}
