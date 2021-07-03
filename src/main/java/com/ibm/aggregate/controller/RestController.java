package com.ibm.aggregate.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cloudant.client.api.Database;
import com.cloudant.client.api.model.Response;
import com.ibm.aggregate.api.model.CustomerCategoryDetails;
import com.ibm.aggregate.api.model.TransactionDetails;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/transactions")
public class RestController {

	@Autowired
	private Database db;
	
	@RequestMapping(method=RequestMethod.POST, path="/add",consumes = "application/json")
	public @ResponseBody String addTransaction(@RequestBody List<TransactionDetails> tds) {
		System.out.println("aggregate category method..... start");
		Response r= null;
		System.out.println("transaction Details="+tds);
		if(tds!=null) {
			for(TransactionDetails td: tds) {
			System.out.println(td.toString());
			r = db.post(td);
			}
		}
		
		System.out.println("aggregate category method..... END");
		return r.getId();
	}

	@RequestMapping(method=RequestMethod.GET, path="/getAll")
	public @ResponseBody List<TransactionDetails> getTransactions(@RequestParam(required=false) Integer customerId) {
		System.out.println("aggregate category method get transactions..... start");
		List<TransactionDetails> allDocs = null;
		
			try {
				if(customerId==null) {
				allDocs = db.getAllDocsRequestBuilder().includeDocs(true)
						.build()
						.getResponse()
						.getDocsAs(TransactionDetails.class);
				}
				else {
////					db.createIndex("querybycustomerIdView","designdoc","json", new IndexField()[] {new IndexField("",)});
//					allDocs = db.find(TransactionDetails.class, customerId);
//					db.
					
					System.out.println("------------------In else --------");
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		System.out.println("aggregate category method get transctions..... END");
		return allDocs;
	}
	
	
	
	@RequestMapping(method=RequestMethod.GET, path="/getcustomerspentpattern")
	public @ResponseBody List<CustomerCategoryDetails> getCustomerCategoryDetails(@RequestParam(required=false) Integer customerId) {
		System.out.println("aggregate category method get transactions..... start");
		List<CustomerCategoryDetails> allDocs = null;
		
			try {
				if(customerId==null) {
					
				allDocs = db.getAllDocsRequestBuilder().includeDocs(true)
						.build()
						.getResponse()
						.getDocsAs(CustomerCategoryDetails.class);
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return allDocs;
	}
	
}
