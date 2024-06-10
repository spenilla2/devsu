package com.devsu.clientperson.clientperson.domain.services;
import java.util.List;
import java.util.Optional;

import com.devsu.clientperson.clientperson.domain.models.Client;

public interface ClientService {
    public List<Client> getAllClients();
    public Optional<Client> getClientById(Long id);
    public Optional<Client> getClientByIdentification(String identification);
    public Optional<Client> saveClient(Client client);
    public Optional<Client> updateClient(String identification, Client client);
    public Optional<Client> deleteClientByIdentification(String identification);
} 