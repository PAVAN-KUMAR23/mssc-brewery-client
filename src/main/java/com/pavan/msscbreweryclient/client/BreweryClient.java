package com.pavan.msscbreweryclient.client;

import com.pavan.msscbreweryclient.web.model.BeerDto;
import com.pavan.msscbreweryclient.web.model.CustomerDto;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.UUID;

@Component
@ConfigurationProperties(prefix= "sfg.brewery", ignoreUnknownFields = false)
public class BreweryClient {

    public final String BEER_PATH_V1="/api/v1/beer/";
    public final String CUSTOMER_PATH_V1 ="/api/v1/customer/";

    public void setApihost(String apihost) {
        this.apihost = apihost;
    }

    private String apihost;

    private final RestTemplate restTemplate;


    public BreweryClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public BeerDto getBeerById(UUID beerId){
        return restTemplate.getForObject(apihost+BEER_PATH_V1+beerId.toString(),BeerDto.class);
    }

    public CustomerDto getCustomerById(UUID customerId){
        return  restTemplate.getForObject(apihost+CUSTOMER_PATH_V1+customerId, CustomerDto.class);
    }
    public URI saveNewBeer(BeerDto beerDto){
        return restTemplate.postForLocation(apihost+BEER_PATH_V1,beerDto);
    }

    public URI saveNewCustomer(CustomerDto customerDto) {
        return  restTemplate.postForLocation(apihost + CUSTOMER_PATH_V1, customerDto);
    }
    public void updateBeer(UUID beerId, BeerDto beerDto){
        restTemplate.put(apihost+BEER_PATH_V1+beerId.toString(),beerDto);
    }

    public void UpdateCustomer(UUID customerId, CustomerDto customerDto){
        restTemplate.put(apihost+CUSTOMER_PATH_V1+customerId,customerDto);
    }
    public void deleteBeer(UUID beerId){
        restTemplate.delete(apihost+BEER_PATH_V1+beerId);
    }

    public void deleteCustomer(UUID customerId){
        restTemplate.delete(apihost+CUSTOMER_PATH_V1+customerId);
    }
}
