package com.devsu.accountmovement.accountmovement.domain.services;

import java.util.Optional;

import com.devsu.accountmovement.accountmovement.domain.models.Client;

public interface ClientService {
    public Optional<Client> getClientByIdentification(String iddentification);    
}
