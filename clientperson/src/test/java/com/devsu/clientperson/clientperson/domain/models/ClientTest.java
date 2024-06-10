package com.devsu.clientperson.clientperson.domain.models;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class ClientTest {

    @Test
    public void testClientGettersAndSetters() {
        Client client = new Client();

        // Probando campos heredados de Person
        client.setId(1L);
        client.setName("John Doe");
        client.setGender("Male");
        client.setAge(30);
        client.setIdentification("123456789");
        client.setAddress("123 Street");
        client.setPhone("555-1234");

        // Probando campos específicos de Client
        client.setClientId("clientId123");
        client.setPassword("password");
        client.setStatus(true);

        // Verificando campos heredados de Person
        assertEquals(1L, client.getId());
        assertEquals("John Doe", client.getName());
        assertEquals("Male", client.getGender());
        assertEquals(30, client.getAge());
        assertEquals("123456789", client.getIdentification());
        assertEquals("123 Street", client.getAddress());
        assertEquals("555-1234", client.getPhone());

        // Verificando campos específicos de Client
        assertEquals("clientId123", client.getClientId());
        assertEquals("password", client.getPassword());
        assertTrue(client.isStatus());
    }

    @Test
    public void testClientAllArgsConstructor() {
        Client client = new Client();
        client.setId(1L);
        client.setName("John Doe");
        client.setGender("Male");
        client.setAge(30);
        client.setIdentification("123456789");
        client.setAddress("123 Street");
        client.setPhone("555-1234");
        client.setPassword("password");
        client.setClientId("clientId123");

        // Verificando campos heredados de Person
        assertEquals(1L, client.getId());
        assertEquals("John Doe", client.getName());
        assertEquals("Male", client.getGender());
        assertEquals(30, client.getAge());
        assertEquals("123456789", client.getIdentification());
        assertEquals("123 Street", client.getAddress());
        assertEquals("555-1234", client.getPhone());

        // Verificando campos específicos de Client
        assertEquals("clientId123", client.getClientId());
        assertEquals("password", client.getPassword());

    }

    @Test
    public void testClientRequiredArgsConstructor() {
        Client client = new Client();

        // Verificando que todos los campos están en sus valores por defecto
        assertNull(client.getId());
        assertNull(client.getName());
        assertNull(client.getGender());
        assertEquals(0, client.getAge());
        assertNull(client.getIdentification());
        assertNull(client.getAddress());
        assertNull(client.getPhone());
        assertNull(client.getClientId());
        assertNull(client.getPassword());
        assertFalse(client.isStatus());
    }
}
