package com.devsu.accountmovement.accountmovement.domain.services.impl;

import com.devsu.accountmovement.accountmovement.domain.exceptions.BussinessException;
import com.devsu.accountmovement.accountmovement.domain.exceptions.ResourceNotFoundException;
import com.devsu.accountmovement.accountmovement.domain.models.Account;
import com.devsu.accountmovement.accountmovement.domain.models.Client;
import com.devsu.accountmovement.accountmovement.domain.services.ClientService;
import com.devsu.accountmovement.accountmovement.infrastructure.repositories.AccountRepository;
import com.devsu.accountmovement.accountmovement.infrastructure.repositories.mapper.AccountMapperEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AccountServiceImplTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private ClientService clientService;

    @InjectMocks
    private AccountServiceImpl accountServiceImpl;

    private Account account;
    private Client client;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        client = new Client(1L, "123456789", "John Doe");
        account = new Account(1L, "123456", "Saving", 1000L, true, client);
    }

    @Test
    public void testGetAllAccounts() {
        when(accountRepository.findAll()).thenReturn(List.of(AccountMapperEntity.toAccountEntity(account)));

        List<Account> accounts = accountServiceImpl.getAllAccounts();

        assertNotNull(accounts);
        assertEquals(1, accounts.size());
        verify(accountRepository, times(1)).findAll();
    }

    @Test
    public void testGetAccountByAccountNumber() {
        when(accountRepository.findByAccountNumber("123456")).thenReturn(Optional.of(AccountMapperEntity.toAccountEntity(account)));

        Optional<Account> accountFound = accountServiceImpl.getAccountByAccountNumber("123456");

        assertTrue(accountFound.isPresent());
        assertEquals("123456", accountFound.get().getAccountNumber());
        verify(accountRepository, times(1)).findByAccountNumber("123456");
    }

    @Test
    public void testSaveAccount() {
        when(accountRepository.findByAccountNumber(any())).thenReturn(Optional.empty());
        when(clientService.getClientByIdentification(any())).thenReturn(Optional.of(client));
        when(accountRepository.save(any())).thenReturn(AccountMapperEntity.toAccountEntity(account));

        Optional<Account> savedAccount = accountServiceImpl.saveAccount(account);

        assertTrue(savedAccount.isPresent());
        assertEquals("123456", savedAccount.get().getAccountNumber());
        verify(accountRepository, times(1)).findByAccountNumber(any());
        verify(accountRepository, times(1)).save(any());
    }

    @Test
    public void testUpdateAccount() {
        when(accountRepository.findByAccountNumber("123456")).thenReturn(Optional.of(AccountMapperEntity.toAccountEntity(account)));
        when(clientService.getClientByIdentification(any())).thenReturn(Optional.of(client));
        when(accountRepository.save(any())).thenReturn(AccountMapperEntity.toAccountEntity(account));

        Optional<Account> updatedAccount = accountServiceImpl.updateAccount("123456", account);

        assertTrue(updatedAccount.isPresent());
        assertEquals("123456", updatedAccount.get().getAccountNumber());
        verify(accountRepository, times(1)).findByAccountNumber("123456");
        verify(accountRepository, times(1)).save(any());
    }

    @Test
    public void testDeleteAccountByAccountNumber() {
        when(accountRepository.findByAccountNumber("123456")).thenReturn(Optional.of(AccountMapperEntity.toAccountEntity(account)));

        Optional<Account> deletedAccount = accountServiceImpl.deleteAccountByAccountNumber("123456");

        assertTrue(deletedAccount.isPresent());
        assertEquals("123456", deletedAccount.get().getAccountNumber());
        verify(accountRepository, times(1)).findByAccountNumber("123456");
        verify(accountRepository, times(1)).deleteByAccountNumber("123456");
    }

    @Test
    public void testGetAllAccountsThrowsResourceNotFoundException() {
        when(accountRepository.findAll()).thenReturn(List.of());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            accountServiceImpl.getAllAccounts();
        });

        assertEquals("account", exception.getResourceName());
        verify(accountRepository, times(1)).findAll();
    }

    @Test
    public void testGetAccountByAccountNumberThrowsResourceNotFoundException() {
        when(accountRepository.findByAccountNumber("123456")).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            accountServiceImpl.getAccountByAccountNumber("123456");
        });

        assertEquals("account", exception.getResourceName());
        verify(accountRepository, times(1)).findByAccountNumber("123456");
    }

    @Test
    public void testSaveAccountThrowsBussinessExceptionWhenAccountExists() {
        when(accountRepository.findByAccountNumber("123456")).thenReturn(Optional.of(AccountMapperEntity.toAccountEntity(account)));

        BussinessException exception = assertThrows(BussinessException.class, () -> {
            accountServiceImpl.saveAccount(account);
        });

        assertEquals("The account width accountNumber 123456 already exists", exception.getMessage());
        verify(accountRepository, times(1)).findByAccountNumber("123456");
    }

    @Test
    public void testSaveAccountThrowsBussinessExceptionWhenClientNotExists() {
        when(accountRepository.findByAccountNumber("123456")).thenReturn(Optional.empty());
        when(clientService.getClientByIdentification(any())).thenReturn(Optional.empty());

        BussinessException exception = assertThrows(BussinessException.class, () -> {
            accountServiceImpl.saveAccount(account);
        });

        assertEquals("The client with identification 123456789 does not exist", exception.getMessage());
        verify(accountRepository, times(1)).findByAccountNumber("123456");
        verify(clientService, times(1)).getClientByIdentification(any());
    }

    @Test
    public void testUpdateAccountThrowsResourceNotFoundException() {
        when(accountRepository.findByAccountNumber("123456")).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            accountServiceImpl.updateAccount("123456", account);
        });

        assertEquals("account", exception.getResourceName());
        verify(accountRepository, times(1)).findByAccountNumber("123456");
    }

    @Test
    public void testDeleteAccountByAccountNumberThrowsResourceNotFoundException() {
        when(accountRepository.findByAccountNumber("123456")).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            accountServiceImpl.deleteAccountByAccountNumber("123456");
        });

        assertEquals("account", exception.getResourceName());
        verify(accountRepository, times(1)).findByAccountNumber("123456");
    }
}
