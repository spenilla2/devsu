package com.devsu.clientperson.clientperson.infrastructure.controllers.mapper;

import com.devsu.clientperson.clientperson.domain.models.Client;
import com.devsu.clientperson.clientperson.infrastructure.controllers.dto.ClientDTO;

public class ClientDTOMapper {
    
    public static ClientDTO toClientDTOResponse(Client client){
        return new ClientDTO(client.getId(),
                                    client.getName(), 
                                    client.getGender(), 
                                    client.getAge(), 
                                    client.getIdentification(), 
                                    client.getClientId(),
                                    client.getAddress(), 
                                    client.getPhone(),                              
                                    client.getPassword(),
                                    client.isStatus());                             
    }
    public static Client toClient(ClientDTO clientDTO){
        Client client = new Client();
        client.setId(clientDTO.getId());
        client.setName(clientDTO.getName());
        client.setGender(clientDTO.getGender());
        client.setAge(clientDTO.getAge());
        client.setIdentification(clientDTO.getIdentification());
        client.setClientId(clientDTO.getClientId());
        client.setAddress(clientDTO.getAddress());
        client.setPhone(clientDTO.getPhone());
        client.setPassword(clientDTO.getPassword());
        client.setStatus(clientDTO.isStatus());        
        return client;
    } 
}
