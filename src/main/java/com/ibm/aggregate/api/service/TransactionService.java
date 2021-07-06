package com.ibm.aggregate.api.service;

import java.util.ArrayList;
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
//		this.aggregateCategoryTransactions();
	}
	
	public void aggregateCategoryTransactions() {
		boolean customerIdMatched=false;

		List<TransactionDetails> transactionDetailsList = transactionDetails.getTransactionDetails();
		List<CustomerCategoryDetails> categoryDetailsList =	categoryDetails.getCategoryDetails();

		for(TransactionDetails transactionDetails:transactionDetailsList) {

			int tCustomerId = transactionDetails.getCustomerId();

			if (transactionDetails.getWithdrawalAmount() > 0) {
				customerIdMatched = false;
				for (CustomerCategoryDetails categoryDetails : categoryDetailsList) {
					int cCustomerId = categoryDetails.getCustomerId();

					if (tCustomerId == cCustomerId) {
						//update details in the existing transaction category list
						categoryDetails.setTransactionCategoryDetails(
								this.updateTransactionCategoryDetails(transactionDetails,
										categoryDetails.getTransactionCategoryDetails()));
						customerIdMatched = true;
						break;
					}
				}
				if (!customerIdMatched) {
					//add new customer entry
					categoryDetailsList = this.addTransactionCategoryDetails(transactionDetails, categoryDetailsList);
				}
			}
		}

		this.updateDetails(categoryDetailsList);
		System.out.println("Final category details list : "+categoryDetailsList);
	}

	private void updateDetails(List<CustomerCategoryDetails> categoryDetailsList) {

		List<CustomerCategoryDetails> detailsUpdate = new ArrayList<CustomerCategoryDetails>();
		List<CustomerCategoryDetails> detailsAdd = new ArrayList<CustomerCategoryDetails>();

		for(CustomerCategoryDetails details : categoryDetailsList){

			if(details.get_id()==null){
				detailsAdd.add(details);
			}
			else{
				detailsUpdate.add(details);
			}
		}

		if(! detailsUpdate.isEmpty()){
			System.out.println("Update details :"+detailsUpdate);
			this.categoryDetails.updateCategoryDetails(detailsUpdate);
		}

		if(! detailsAdd.isEmpty()){
			System.out.println("Add details :"+detailsAdd);
			this.categoryDetails.addCategoryDetails(detailsAdd);
		}


	}

	private List<CustomerCategoryDetails> addTransactionCategoryDetails(TransactionDetails transactionDetails,
											   List<CustomerCategoryDetails> categoryDetailsList) {
		System.out.println("add category details for customer id="+transactionDetails.getCustomerId());

		AggregateCategoryDetails aggregateCategoryDetails = new AggregateCategoryDetails();
		aggregateCategoryDetails.setCategory(transactionDetails.getTransactionCategory());
		//First transaction entry so count =1
		aggregateCategoryDetails.setTransactionsCount(1);
		aggregateCategoryDetails.setTotalAmountSpent(transactionDetails.getWithdrawalAmount());


		CustomerCategoryDetails customerCategoryDetails = new CustomerCategoryDetails();
		customerCategoryDetails.setCustomerId(transactionDetails.getCustomerId());
		List<AggregateCategoryDetails> aggregateCategoryDetailsList = new ArrayList<AggregateCategoryDetails>();
		aggregateCategoryDetailsList.add(aggregateCategoryDetails);
		customerCategoryDetails.setTransactionCategoryDetails(aggregateCategoryDetailsList);

		categoryDetailsList.add(customerCategoryDetails);

		return categoryDetailsList;
	}



	private List<AggregateCategoryDetails> updateTransactionCategoryDetails(TransactionDetails transactionDetails,
												  List<AggregateCategoryDetails> transactionCategoryDetailsList) {
		System.out.println("Update category details for customer id ="+ transactionDetails.getCustomerId());
		boolean categoryFound = false;
		String category = transactionDetails.getTransactionCategory();

		if(category != null  &&  ! category.trim().equals("")) {
			AggregateCategoryDetails transactionCategoryDetailUpdated = null;
			int count=1;
			double amount=0;

			for (AggregateCategoryDetails transactionCategoryDetail : transactionCategoryDetailsList) {
				if (transactionCategoryDetail.getCategory().equals(category)) {
					categoryFound = true;

					count = transactionCategoryDetail.getTransactionsCount()+1;
					amount = transactionCategoryDetail.getTotalAmountSpent()
							+ transactionDetails.getWithdrawalAmount();

					transactionCategoryDetailUpdated = transactionCategoryDetail;
					break;
				}
			}

			if(categoryFound && transactionCategoryDetailUpdated!=null) {
				transactionCategoryDetailUpdated.setTransactionsCount(count);
				transactionCategoryDetailUpdated.setTotalAmountSpent(amount);
			}
			else{

				AggregateCategoryDetails details = new AggregateCategoryDetails();
				details.setCategory(transactionDetails.getTransactionCategory());
				details.setTransactionsCount(1);
				details.setTotalAmountSpent(transactionDetails.getWithdrawalAmount());
				transactionCategoryDetailsList.add(details);

			}
		}
		return transactionCategoryDetailsList;
	}


}
