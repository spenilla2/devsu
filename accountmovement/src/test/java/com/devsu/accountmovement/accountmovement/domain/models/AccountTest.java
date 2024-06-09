package com.devsu.accountmovement.accountmovement.domain.models;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class AccountTest {
    private Account account;

    @BeforeEach
    public void setUp() {
        account = new Account();
        account.setId(1L);
        account.setAccountNumber("123456789");
        account.setAccountType("Savings");
        account.setInitialBalance(1000L);
        account.setStatus(true);
        Client client = new Client();
        client.setIdClient(1L);
        client.setName("John Doe");
        account.setClient(client);
    }

    @Test
    public void testGetters() {
        assertEquals(1L, account.getId());
        assertEquals("123456789", account.getAccountNumber());
        assertEquals("Savings", account.getAccountType());
        assertEquals(1000L, account.getInitialBalance());
        assertTrue(account.isStatus());
        assertNotNull(account.getClient());
        assertEquals(1L, account.getClient().getIdClient());
        assertEquals("John Doe", account.getClient().getName());
    }

    @Test
    public void testSetters() {
        account.setId(2L);
        assertEquals(2L, account.getId());

        account.setAccountNumber("987654321");
        assertEquals("987654321", account.getAccountNumber());

        account.setAccountType("Checking");
        assertEquals("Checking", account.getAccountType());

        account.setInitialBalance(2000L);
        assertEquals(2000L, account.getInitialBalance());

        account.setStatus(false);
        assertFalse(account.isStatus());

        Client newClient = new Client();
        newClient.setIdClient(2L);
        newClient.setName("Jane Doe");
        account.setClient(newClient);
        assertNotNull(account.getClient());
        assertEquals(2L, account.getClient().getIdClient());
        assertEquals("Jane Doe", account.getClient().getName());
    }
}
