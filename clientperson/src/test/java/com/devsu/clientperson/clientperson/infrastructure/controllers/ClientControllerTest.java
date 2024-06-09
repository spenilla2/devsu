package com.devsu.clientperson.clientperson.infrastructure.controllers;

import com.devsu.clientperson.clientperson.infrastructure.controllers.dto.ClientDTO;
import com.devsu.clientperson.clientperson.domain.services.impl.ClientServiceImpl;
import com.devsu.clientperson.clientperson.domain.models.Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ClientControllerTest {

    @Mock
    private ClientServiceImpl clientServiceImpl;

    @InjectMocks
    private ClientController clientController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(clientController).build();
    }

    @Test
    void getAllClients_ShouldReturnListOfClients() throws Exception {
        Client client = new Client();
        when(clientServiceImpl.getAllClients()).thenReturn(List.of(client));

        mockMvc.perform(get("/api/client/clients"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").exists());

        verify(clientServiceImpl, times(1)).getAllClients();
    }

    @Test
    void getClientByIdentification_ShouldReturnClient() throws Exception {
        Client client = new Client();
        client.setId(1L); 
        when(clientServiceImpl.getClientByIdentification(anyString())).thenReturn(Optional.of(client));

        mockMvc.perform(get("/api/client/{identification}", "12345"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(1L)); 


        verify(clientServiceImpl, times(1)).getClientByIdentification(anyString());
    }
/*
    @Test
    void createClient_ShouldReturnCreatedClient() throws Exception {
        ClientDTO clientDTO = new ClientDTO();
        Client client = new Client();
        when(clientServiceImpl.saveClient(any(Client.class))).thenReturn(Optional.of(client));

        mockMvc.perform(post("/api/client")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"identification\":\"12345\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists());

        verify(clientServiceImpl, times(1)).saveClient(any(Client.class));
    }
    
    @Test
    void updateClient_ShouldReturnUpdatedClient() throws Exception {
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setIdentification("12345");
        Client client = new Client();
        client.setId(1L); // Ensure the client has an id
        client.setIdentification("12345");

        when(clientServiceImpl.updateClient(anyLong(), any(Client.class))).thenReturn(Optional.of(client));
       
        mockMvc.perform(put("/api/client/{id}", 1L)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\"identification\":\"12345\"}")) // Ensure the JSON matches the ClientDTO structure
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(1L)); // Check the value of id

        verify(clientServiceImpl, times(1)).updateClient(anyLong(), any(Client.class));
    }

    @Test
    void deleteClient_ShouldReturnNoContent() throws Exception {
        Client client = new Client();
        when(clientServiceImpl.deleteClientByIdentification(anyString())).thenReturn(Optional.of(client));
        mockMvc.perform(delete("/api/client/{identification}", "12345"))
                .andExpect(status().isNoContent());
        verify(clientServiceImpl, times(1)).deleteClientByIdentification(anyString());
    }*/
}
