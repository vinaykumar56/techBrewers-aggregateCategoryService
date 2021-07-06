package com.ibm.aggregate.api.client;

import com.ibm.aggregate.api.model.CustomerCategoryDetails;
import com.ibm.aggregate.api.model.TransactionDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
public class CallTransactionDetails {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${transaction.url.get}")
    private String getUrl;

    public List<TransactionDetails> getTransactionDetails(){
        ParameterizedTypeReference<List<TransactionDetails>> type =
                new ParameterizedTypeReference<List<TransactionDetails>>() {
        };

        ResponseEntity<List<TransactionDetails>> responseEntity = restTemplate.exchange(
                getUrl, HttpMethod.GET,null,type );

        return  responseEntity.getBody();
    }
}
