package com.devsu.clientperson.clientperson.infrastructure.controllers.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ClientDTOTest {
    @Test
    public void testClientDTOGettersAndSetters() {
        ClientDTO clientDTO = new ClientDTO();

        clientDTO.setId(1L);
        clientDTO.setName("John Doe");
        clientDTO.setGender("Male");
        clientDTO.setAge(30);
        clientDTO.setIdentification("123456789");
        clientDTO.setAddress("123 Street");
        clientDTO.setPhone("555-1234");
        clientDTO.setClientId("clientId123");
        clientDTO.setPassword("password");
        clientDTO.setStatus(true);

        assertEquals(1L, clientDTO.getId());
        assertEquals("John Doe", clientDTO.getName());
        assertEquals("Male", clientDTO.getGender());
        assertEquals(30, clientDTO.getAge());
        assertEquals("123456789", clientDTO.getIdentification());
        assertEquals("123 Street", clientDTO.getAddress());
        assertEquals("555-1234", clientDTO.getPhone());
        assertEquals("clientId123", clientDTO.getClientId());
        assertEquals("password", clientDTO.getPassword());
        assertTrue(clientDTO.isStatus());
    }

    @Test
    public void testClientDTOAllArgsConstructor() {
        ClientDTO clientDTO = new ClientDTO(1L, "John Doe", "Male", 30, "123456789", "123 Street", "555-1234", "clientId123", "password", true);

        assertEquals(1L, clientDTO.getId());
        assertEquals("John Doe", clientDTO.getName());
        assertEquals("Male", clientDTO.getGender());
        assertEquals(30, clientDTO.getAge());
        assertEquals("123456789", clientDTO.getIdentification());
        assertEquals("123 Street", clientDTO.getAddress());
        assertEquals("555-1234", clientDTO.getPhone());
        assertEquals("clientId123", clientDTO.getClientId());
        assertEquals("password", clientDTO.getPassword());
        assertTrue(clientDTO.isStatus());
    }

    @Test
    public void testClientDTORequiredArgsConstructor() {
        ClientDTO clientDTO = new ClientDTO();

        assertNull(clientDTO.getId());
        assertNull(clientDTO.getName());
        assertNull(clientDTO.getGender());
        assertEquals(0, clientDTO.getAge());
        assertNull(clientDTO.getIdentification());
        assertNull(clientDTO.getAddress());
        assertNull(clientDTO.getPhone());
        assertNull(clientDTO.getClientId());
        assertNull(clientDTO.getPassword());
        assertFalse(clientDTO.isStatus());
    }
}
