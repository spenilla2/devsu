package com.devsu.accountmovement.accountmovement.infrastructure.adapter.mapper;


import com.devsu.accountmovement.accountmovement.domain.models.Client;
import com.devsu.accountmovement.accountmovement.infrastructure.adapter.dto.ClientDTO;


public class ClientDTOMapper {
    public static Client toClient(ClientDTO clientDTO) {
        Client client = Client.builder().identification(clientDTO.getIdentification())
                                        .name(clientDTO.getName()).build();
        return client;
    }    
}
