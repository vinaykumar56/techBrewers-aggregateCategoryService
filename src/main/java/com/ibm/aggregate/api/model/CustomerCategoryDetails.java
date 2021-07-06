package com.ibm.aggregate.api.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerCategoryDetails {

	@Id
	private String _id;
	private String _rev;
	private int customerId;
	private List<AggregateCategoryDetails> transactionCategoryDetails;

	public CustomerCategoryDetails(){

	}
	
}
