package com.ibm.aggregate.api.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionLikeType;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.ibm.aggregate.api.model.CustomerCategoryDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
public class CallCategoryDetails {

//    @Autowired
//    private RestTemplate restTemplate;


    public List<CustomerCategoryDetails> getCategoryDetails(){
        ParameterizedTypeReference<List<CustomerCategoryDetails>> type = new ParameterizedTypeReference<List<CustomerCategoryDetails>>() {
        };
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<CustomerCategoryDetails>> responseEntity = restTemplate.exchange(
                "http://localhost:8081/techbrewers/transationcategory/get",
                        HttpMethod.GET,null,type );

        return  responseEntity.getBody();
    }

    public static CustomerCategoryDetails updateCategoryDetails(){
        RestTemplate restTemplate = new RestTemplate();
        CustomerCategoryDetails categoryDetails= restTemplate.getForObject(
                "http://localhost:8081/techbrewers/transationcategory/update",
                CustomerCategoryDetails.class);

        return  categoryDetails;
    }
}
