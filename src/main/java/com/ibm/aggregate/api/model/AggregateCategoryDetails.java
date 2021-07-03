package com.ibm.aggregate.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AggregateCategoryDetails {

private String category;
private double totalAmountSpent;
private int transactionsCount;


}
