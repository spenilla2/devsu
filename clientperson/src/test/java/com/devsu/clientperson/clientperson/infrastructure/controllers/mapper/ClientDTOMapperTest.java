package com.devsu.clientperson.clientperson.infrastructure.controllers.mapper;

import com.devsu.clientperson.clientperson.domain.models.Client;
import com.devsu.clientperson.clientperson.infrastructure.controllers.dto.ClientDTO;
import com.devsu.clientperson.clientperson.infrastructure.controllers.mapper.ClientDTOMapper;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class ClientDTOMapperTest {

    @InjectMocks
    private ClientDTOMapper clientDTOMapper;

    @Test
    void testToClientDTOResponse() {
        // Given
        Client client = new Client();
        client.setId(1L);
        client.setName("John Doe");
        // Set other fields as needed

        // When
        ClientDTO clientDTO = clientDTOMapper.toClientDTOResponse(client);

        // Then
        assertEquals(client.getId(), clientDTO.getId());
        assertEquals(client.getName(), clientDTO.getName());
        // Assert other fields as needed
    }

    @Test
    void testToClient() {
        // Given
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setId(1L);
        clientDTO.setName("John Doe");
        // Set other fields as needed

        // When
        Client client = clientDTOMapper.toClient(clientDTO);

        // Then
        assertEquals(clientDTO.getId(), client.getId());
        assertEquals(clientDTO.getName(), client.getName());
        // Assert other fields as needed
    }
}