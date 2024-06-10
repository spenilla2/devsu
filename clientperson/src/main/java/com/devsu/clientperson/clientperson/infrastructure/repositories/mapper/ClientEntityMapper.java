package com.devsu.clientperson.clientperson.infrastructure.repositories.mapper;

import com.devsu.clientperson.clientperson.domain.models.Client;
import com.devsu.clientperson.clientperson.infrastructure.repositories.entities.ClientEntity;

public class ClientEntityMapper {
    public static Client toClient(ClientEntity clientEntity){
        Client client = new Client();
        client.setId(clientEntity.getId());
        client.setName(clientEntity.getName());
        client.setGender(clientEntity.getGender());
        client.setAge(clientEntity.getAge());
        client.setIdentification(clientEntity.getIdentification());
        client.setClientId(clientEntity.getClientId());
        client.setAddress(clientEntity.getAddress());
        client.setPhone(clientEntity.getPhone());
        client.setPassword(clientEntity.getPassword());
        client.setStatus(clientEntity.isStatus());
        return client;
    }
    
    public static ClientEntity toClientEntity(Client client){
        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setName(client.getName());
        clientEntity.setGender(client.getGender());
        clientEntity.setAge(client.getAge());
        clientEntity.setIdentification(client.getIdentification());
        clientEntity.setClientId(client.getClientId());
        clientEntity.setAddress(client.getAddress());
        clientEntity.setPhone(client.getPhone());
        clientEntity.setPassword(client.getPassword());
        clientEntity.setStatus(client.isStatus());        
        return clientEntity;
    }
    
}
