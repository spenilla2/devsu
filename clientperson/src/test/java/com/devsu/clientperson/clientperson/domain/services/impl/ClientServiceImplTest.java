package com.devsu.clientperson.clientperson.domain.services.impl;

import com.devsu.clientperson.clientperson.domain.exceptions.BussinessException;
import com.devsu.clientperson.clientperson.domain.exceptions.ResourceNotFoundException;
import com.devsu.clientperson.clientperson.domain.models.Client;
import com.devsu.clientperson.clientperson.infrastructure.repositories.ClientRepository;
import com.devsu.clientperson.clientperson.infrastructure.repositories.mapper.ClientEntityMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class ClientServiceImplTest {

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientServiceImpl clientServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllClients_ShouldReturnClients_WhenClientsExist() {
        Client client = new Client();
        when(clientRepository.findAll()).thenReturn(List.of(ClientEntityMapper.toClientEntity(client)));

        List<Client> clients = clientServiceImpl.getAllClients();

        assertNotNull(clients);
        assertFalse(clients.isEmpty());
        verify(clientRepository, times(1)).findAll();
    }

    @Test
    void getAllClients_ShouldThrowException_WhenNoClientsExist() {
        when(clientRepository.findAll()).thenReturn(Collections.emptyList());

        assertThrows(ResourceNotFoundException.class, () -> clientServiceImpl.getAllClients());
    }

    @Test
    void getClientById_ShouldReturnClient_WhenClientExists() {
        Client client = new Client();
        when(clientRepository.findById(anyLong())).thenReturn(Optional.of(ClientEntityMapper.toClientEntity(client)));

        Optional<Client> result = clientServiceImpl.getClientById(1L);

        assertTrue(result.isPresent());
        verify(clientRepository, times(1)).findById(anyLong());
    }

    @Test
    void getClientById_ShouldThrowException_WhenClientDoesNotExist() {
        when(clientRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> clientServiceImpl.getClientById(1L));
    }

    @Test
    void getClientByIdentification_ShouldReturnClient_WhenClientExists() {
        Client client = new Client();
        when(clientRepository.findByIdentification(anyString())).thenReturn(Optional.of(ClientEntityMapper.toClientEntity(client)));

        Optional<Client> result = clientServiceImpl.getClientByIdentification("12345");

        assertTrue(result.isPresent());
        verify(clientRepository, times(1)).findByIdentification(anyString());
    }

    @Test
    void getClientByIdentification_ShouldThrowException_WhenClientDoesNotExist() {
        when(clientRepository.findByIdentification(anyString())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> clientServiceImpl.getClientByIdentification("12345"));
    }

    @Test
    void saveClient_ShouldReturnSavedClient_WhenClientIsValid() {
        Client client = new Client();
        client.setIdentification("12345");
        when(clientRepository.save(any())).thenReturn(ClientEntityMapper.toClientEntity(client));

        Optional<Client> result = clientServiceImpl.saveClient(client);

        assertTrue(result.isPresent());
        verify(clientRepository, times(1)).save(any());
    }

    @Test
    void saveClient_ShouldThrowException_WhenClientAlreadyExists() {
        Client client = new Client();
        client.setIdentification("12345");
        when(clientRepository.findByIdentification(anyString())).thenReturn(Optional.of(ClientEntityMapper.toClientEntity(client)));

        assertThrows(BussinessException.class, () -> clientServiceImpl.saveClient(client));
    }
/* 
    @Test
    void updateClient_ShouldReturnUpdatedClient_WhenClientIsValid() {
        Client client = new Client();
        client.setId(1L);
        client.setIdentification("12345");
        when(clientRepository.findById(anyLong())).thenReturn(Optional.of(ClientEntityMapper.toClientEntity(client)));
        when(clientRepository.save(any())).thenReturn(ClientEntityMapper.toClientEntity(client));

        Optional<Client> result = clientServiceImpl.updateClient("12345", client);

        assertTrue(result.isPresent());
        verify(clientRepository, times(1)).save(any());
    }
*/
    @Test
    void updateClient_ShouldThrowException_WhenClientDoesNotExist() {
        Client client = new Client();
        when(clientRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> clientServiceImpl.updateClient("12345", client));
    }
/* 
    @Test
    void deleteClient_ShouldReturnDeletedClient_WhenClientExists() {
        Client client = new Client();
        when(clientRepository.findById(anyLong())).thenReturn(Optional.of(ClientEntityMapper.toClientEntity(client)));
        doNothing().when(clientRepository).deleteByIdentification(anyString());

        Optional<Client> result = clientServiceImpl.deleteClientByIdentification("12345");

        assertTrue(result.isPresent());
        verify(clientRepository, times(1)).deleteByIdentification(anyString());
    }
*/
    @Test
    void deleteClient_ShouldThrowException_WhenClientDoesNotExist() {
        when(clientRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> clientServiceImpl.deleteClientByIdentification("12345"));
    }
}
