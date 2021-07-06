package com.ibm.aggregate;


import com.ibm.aggregate.api.client.CallCategoryDetails;
import com.ibm.aggregate.api.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan({"com.ibm.aggregate.controller","com.ibm.aggregate.api.config","com.ibm.aggregate.api.client","com.ibm.aggregate.api.service"})
public class AggregateCategoryApplication {


	public static void main(String[] args) {
		SpringApplication.run(AggregateCategoryApplication.class, args);
	}

}
