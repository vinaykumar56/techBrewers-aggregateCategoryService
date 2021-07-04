package com.ibm.aggregate.api.client;

import com.ibm.aggregate.api.model.CustomerCategoryDetails;
import com.ibm.aggregate.api.model.TransactionDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
public class CallTransactionDetails {

//    @Autowired
//    private RestTemplate restTemplate;

    public List<TransactionDetails> getTransactionDetails(){
        ParameterizedTypeReference<List<TransactionDetails>> type =
                new ParameterizedTypeReference<List<TransactionDetails>>() {
        };
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<TransactionDetails>> responseEntity = restTemplate.exchange(
                "http://localhost:8082/techbrewers/transactions/get",
                HttpMethod.GET,null,type );

        return  responseEntity.getBody();
    }
}
