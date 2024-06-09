package com.devsu.clientperson.clientperson.domain.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.devsu.clientperson.clientperson.domain.services.ClientService;
import com.devsu.clientperson.clientperson.infrastructure.repositories.ClientRepository;
import com.devsu.clientperson.clientperson.infrastructure.repositories.mapper.ClientEntityMapper;
import com.devsu.clientperson.clientperson.domain.exceptions.ResourceNotFoundException;
import com.devsu.clientperson.clientperson.domain.exceptions.BussinessException;
import com.devsu.clientperson.clientperson.domain.models.Client;
@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    private ClientRepository clientRepository;

    public List<Client> getAllClients() {
        List<Client> clients = clientRepository.findAll().stream().map(client -> ClientEntityMapper.toClient(client)).toList();
        if(clients.isEmpty()){
            throw new ResourceNotFoundException("client");
        }
        return clients;
    }
    public Optional<Client> getClientById(Long id) {
        Optional<Client> clientSearch = clientRepository.findById(id).map(client -> ClientEntityMapper.toClient(client));
        if(clientSearch.isEmpty()){
            throw new ResourceNotFoundException("client" ,"id",id);
        }
        return clientSearch;
    }
    @Override
    public Optional<Client> getClientByIdentification(String identification) {
        Optional<Client> clientSearch = clientRepository.findByIdentification(identification).map(client -> ClientEntityMapper.toClient(client));
        if(clientSearch.isEmpty()){
            throw new ResourceNotFoundException("client ","identification",identification);
        }
        return clientSearch;
    }
    public Optional<Client> saveClient(Client client) {
        if(clientRepository.findByIdentification(client.getIdentification()).isPresent()){
            throw new BussinessException("The Client width identification "+client.getIdentification()+" already exist in Database!!!");
        }
        if(client.getIdentification().isEmpty()){
            throw new BussinessException("The Identification Client cant be empty or null");
        }
        Optional<Client> clientToSave = Optional.of(clientRepository.save(ClientEntityMapper.toClientEntity(client))).map(clientSave -> ClientEntityMapper.toClient(clientSave));
        if(clientToSave.isEmpty()){
            throw new BussinessException("Problem to Save Client in Database");
        }
        return clientToSave;
    }
    public Optional<Client> updateClient(Long id, Client client) {
        Optional<Client> clientToUpdate = clientRepository.findById(id).map(clientExist -> ClientEntityMapper.toClient(clientExist));
        if(clientToUpdate.isEmpty()){
            throw new ResourceNotFoundException("client", "id", id);
        }
        Optional<Client> clientByIdentificationToUpdate = clientRepository.findByIdentification(client.getIdentification()).map(clientExist -> ClientEntityMapper.toClient(clientExist));
        if(clientByIdentificationToUpdate.isPresent() && !id.equals(clientByIdentificationToUpdate.get().getId())){
            throw new BussinessException("The Client with Identification "+client.getIdentification()+" already exists in Database with another ID");
        }
        Optional<Client> clientUpdated = Optional.of(clientRepository.save(ClientEntityMapper.toClientEntity(client))).map(clientSave -> ClientEntityMapper.toClient(clientSave));
        if(clientUpdated.isEmpty()){
            throw new BussinessException("Problem to Update Client in Database");
        }
        return clientUpdated;
    }
    public Optional<Client> deleteClient(Long id) {
        Optional<Client> clientToDelete = clientRepository.findById(id).map(client -> ClientEntityMapper.toClient(client));
        if(clientToDelete.isEmpty()){
            throw new ResourceNotFoundException("client", "id", id);
        }
        try{
            clientRepository.deleteById(id);
            return clientToDelete;
        }catch(DataAccessException e){
            throw new BussinessException("Problem to Delete Client in Database");
        }


    }

}
