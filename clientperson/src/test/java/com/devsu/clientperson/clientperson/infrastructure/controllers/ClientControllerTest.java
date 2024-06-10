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
    private Client client;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(clientController).build();
        client = new Client();
        client.setId(1L);
        client.setIdentification("12345");
        client.setName("John Doe");

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

    @Test
    void createClient_ShouldReturnCreatedClient() throws Exception {
        when(clientServiceImpl.saveClient(any(Client.class))).thenReturn(Optional.of(client));

        mockMvc.perform(post("/api/client")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"identification\":\"12345\", \"name\":\"John Doe\" , \"clientId\":\"john.doe\" , \"password\":\"123754\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(client.getId()))
                .andExpect(jsonPath("$.identification").value(client.getIdentification()))
                .andExpect(jsonPath("$.name").value(client.getName()))
                .andExpect(jsonPath("$.clientId").value(client.getClientId()))
                .andExpect(jsonPath("$.password").value(client.getPassword()));
        verify(clientServiceImpl, times(1)).saveClient(any(Client.class));
    }

    @Test
    void updateClient_ShouldReturnUpdatedClient() throws Exception {
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setIdentification("12345");
        Client client = new Client();
        client.setId(1L);
        client.setIdentification("12345");

        when(clientServiceImpl.updateClient(anyString(), any(Client.class))).thenReturn(Optional.of(client));
       
        mockMvc.perform(put("/api/client/{identification}", "12345")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\"identification\":\"12345\", \"name\":\"John Doe\" , \"clientId\":\"john.doe\" , \"password\":\"123754\"}"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.identification").value("12345"));

        verify(clientServiceImpl, times(1)).updateClient(anyString(), any(Client.class));
    }

    @Test
    void deleteClient_ShouldReturnNoContent() throws Exception {
        Client client = new Client();
        client.setIdentification("12345");
        when(clientServiceImpl.deleteClientByIdentification(anyString())).thenReturn(Optional.of(client));

        mockMvc.perform(delete("/api/client/{identification}", "12345"))
                .andExpect(status().isNoContent());

        verify(clientServiceImpl, times(1)).deleteClientByIdentification(anyString());
    }
}
