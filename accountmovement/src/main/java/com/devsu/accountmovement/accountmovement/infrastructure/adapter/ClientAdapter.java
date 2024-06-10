package com.devsu.accountmovement.accountmovement.infrastructure.adapter;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.devsu.accountmovement.accountmovement.domain.models.Client;
import com.devsu.accountmovement.accountmovement.domain.services.ClientService;
@Component
public class ClientAdapter implements ClientService{
    @Autowired
    private RestTemplate restTemplate;
    
    @Value("${client.api.url}")
    private String apiEndpoint;
    @Value("${client.api.port}")
    private String apiPort;
    
    public Optional<Client> getClientByIdentification(String identification) {
        String apiUrl = apiEndpoint + ":" + apiPort + "/api/client/" + identification;
        System.out.println("URL_API:"+apiUrl);
        ResponseEntity<Client> responseClient = restTemplate.getForEntity(apiUrl, Client.class);
        if(!responseClient.getStatusCode().is2xxSuccessful()){
            throw new RuntimeException("Error in the request to the client API");
        }        
        return Optional.of(responseClient.getBody());
    }
}
