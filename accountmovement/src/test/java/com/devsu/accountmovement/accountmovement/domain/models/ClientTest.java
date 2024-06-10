package com.devsu.accountmovement.accountmovement.domain.models;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class ClientTest {

    private Client client;

    @BeforeEach
    public void setUp() {
        client = Client.builder()
                .identification("ID12345")
                .name("John Doe")
                .build();
    }

    @Test
    public void testGetters() {
        assertEquals("ID12345", client.getIdentification());
        assertEquals("John Doe", client.getName());
    }

    @Test
    public void testSetters() {
        
        client.setIdentification("ID67890");
        assertEquals("ID67890", client.getIdentification());

        client.setName("Jane Doe");
        assertEquals("Jane Doe", client.getName());
    }

    @Test
    public void testNoArgsConstructor() {
        Client client = new Client();
        assertNull(client.getIdentification());
        assertNull(client.getName());
    }

    @Test
    public void testAllArgsConstructor() {
        Client client = new Client("ID67890", "Jane Doe");
        assertEquals("ID67890", client.getIdentification());
        assertEquals("Jane Doe", client.getName());
    }

    @Test
    public void testRequiredArgsConstructor() {
        Client client = new Client();
        assertNull(client.getIdentification());
        assertNull(client.getName());
    }
}
