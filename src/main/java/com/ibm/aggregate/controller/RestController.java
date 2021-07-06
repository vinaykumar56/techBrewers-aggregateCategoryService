package com.ibm.aggregate.controller;


import com.ibm.aggregate.api.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@org.springframework.web.bind.annotation.RestController
@RequestMapping("/service")
public class RestController {

	@Autowired
	private TransactionService service;

	@RequestMapping(method=RequestMethod.PUT, path="/update")
	public @ResponseBody void updateTransactionCategory(@RequestBody(required=false)  String update) {
		System.out.println("updateTransactionCategory method..... start");
		service.aggregateCategoryTransactions();
		System.out.println("updateTransactionCategory method..... END");

	}

	
}
