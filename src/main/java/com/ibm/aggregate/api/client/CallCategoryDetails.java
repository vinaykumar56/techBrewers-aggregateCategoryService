package com.ibm.aggregate.api.client;

import com.ibm.aggregate.api.model.CustomerCategoryDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Component
public class CallCategoryDetails {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${category.url.get}")
    private String getUrl;

    @Value("${category.url.post}")
    private String postUrl;

    @Value("${category.url.put}")
    private String putUrl;

    public List<CustomerCategoryDetails> getCategoryDetails(){
        ParameterizedTypeReference<List<CustomerCategoryDetails>> type = new ParameterizedTypeReference<List<CustomerCategoryDetails>>() {
        };
//        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<CustomerCategoryDetails>> responseEntity = restTemplate.exchange(
                getUrl, HttpMethod.GET,null,type );

        return  responseEntity.getBody();
    }

    public void updateCategoryDetails( List<CustomerCategoryDetails> categoryDetailsList){

        // create headers
        HttpHeaders headers = new HttpHeaders();
        // set `content-type` header
        headers.setContentType(MediaType.APPLICATION_JSON);
        // set `accept` header
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        // build the request
        HttpEntity<List<CustomerCategoryDetails>> entity = new HttpEntity<List<CustomerCategoryDetails>>(categoryDetailsList, headers);

        // send PUT request to update post with `id` 10
        restTemplate.put(putUrl, entity);
        System.out.println("Updated category db ---------------------- : END");
    }

    public void addCategoryDetails(List<CustomerCategoryDetails> categoryDetailsList){

        ParameterizedTypeReference<List<CustomerCategoryDetails>> type = new ParameterizedTypeReference<List<CustomerCategoryDetails>>() {};

        // create headers
        HttpHeaders headers = new HttpHeaders();
        // set `content-type` header
        headers.setContentType(MediaType.APPLICATION_JSON);
        // set `accept` header
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        // build the request
        HttpEntity<List<CustomerCategoryDetails>> entity = new HttpEntity<List<CustomerCategoryDetails>>(categoryDetailsList, headers);

        try {
            ResponseEntity response = restTemplate.exchange(postUrl, HttpMethod.POST, entity, type);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        System.out.println("POST category db ---------------------- : END");

    }
}
