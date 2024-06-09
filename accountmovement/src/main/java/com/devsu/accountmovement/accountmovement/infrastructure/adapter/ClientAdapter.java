package com.devsu.accountmovement.accountmovement.infrastructure.adapter;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.devsu.accountmovement.accountmovement.domain.models.Client;
import com.devsu.accountmovement.accountmovement.domain.services.ClientService;
import com.devsu.accountmovement.accountmovement.infrastructure.adapter.dto.ClientDTO;
import com.devsu.accountmovement.accountmovement.infrastructure.adapter.mapper.ClientDTOMapper;
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
        Client client = ClientDTOMapper.toClient(restTemplate.getForObject(apiUrl, ClientDTO.class));        
        return Optional.of(client);
    }
}
