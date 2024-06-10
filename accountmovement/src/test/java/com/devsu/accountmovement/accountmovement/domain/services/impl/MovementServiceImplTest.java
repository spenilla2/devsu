package com.devsu.accountmovement.accountmovement.domain.services.impl;

import com.devsu.accountmovement.accountmovement.domain.exceptions.BussinessException;
import com.devsu.accountmovement.accountmovement.domain.exceptions.ResourceNotFoundException;
import com.devsu.accountmovement.accountmovement.domain.models.Account;
import com.devsu.accountmovement.accountmovement.domain.models.Movement;
import com.devsu.accountmovement.accountmovement.domain.services.AccountService;
import com.devsu.accountmovement.accountmovement.infrastructure.repositories.MovementRepository;
import com.devsu.accountmovement.accountmovement.infrastructure.repositories.mapper.MovementMapperEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class MovementServiceImplTest {

    @Mock
    private MovementRepository movementRepository;

    @Mock
    private AccountService accountService;

    @InjectMocks
    private MovementServiceImpl movementServiceImpl;

    private Account account;
    private Movement movement;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        account = new Account(1L, "123456", "Saving", 1000L, true, null);
        movement = new Movement(1L, "123456", "Saving", 1000L, true, 500L, 1500L, LocalDate.now());
    }

    @Test
    public void testGetAllMovements() {
        when(movementRepository.findAll()).thenReturn(List.of(MovementMapperEntity.toMovementEntity(movement)));

        List<Movement> movements = movementServiceImpl.getAllMovements();

        assertNotNull(movements);
        assertEquals(1, movements.size());
        verify(movementRepository, times(1)).findAll();
    }

    @Test
    public void testGetMovementById() {
        when(movementRepository.findById(1L)).thenReturn(Optional.of(MovementMapperEntity.toMovementEntity(movement)));

        Optional<Movement> movementFound = movementServiceImpl.getMovementById(1L);

        assertTrue(movementFound.isPresent());
        assertEquals("123456", movementFound.get().getAccountNumber());
        verify(movementRepository, times(1)).findById(1L);
    }

    @Test
    public void testSaveMovement() {
        when(accountService.getAccountByAccountNumber("123456")).thenReturn(Optional.of(account));
        when(movementRepository.save(any())).thenReturn(MovementMapperEntity.toMovementEntity(movement));

        Optional<Movement> savedMovement = movementServiceImpl.saveMovement(movement);

        assertTrue(savedMovement.isPresent());
        assertEquals("123456", savedMovement.get().getAccountNumber());
        verify(accountService, times(1)).getAccountByAccountNumber("123456");
        verify(movementRepository, times(1)).save(any());
    }

    @Test
    public void testUpdateMovement() {
        when(movementRepository.findById(1L)).thenReturn(Optional.of(MovementMapperEntity.toMovementEntity(movement)));
        when(accountService.getAccountByAccountNumber("123456")).thenReturn(Optional.of(account));
        when(movementRepository.save(any())).thenReturn(MovementMapperEntity.toMovementEntity(movement));

        Optional<Movement> updatedMovement = movementServiceImpl.updateMovement(1L, movement);

        assertTrue(updatedMovement.isPresent());
        assertEquals("123456", updatedMovement.get().getAccountNumber());
        verify(movementRepository, times(1)).findById(1L);
        verify(movementRepository, times(1)).save(any());
    }

    @Test
    public void testDeleteMovement() {
        when(movementRepository.findById(1L)).thenReturn(Optional.of(MovementMapperEntity.toMovementEntity(movement)));
        when(accountService.getAccountByAccountNumber("123456")).thenReturn(Optional.of(account));

        Optional<Movement> deletedMovement = movementServiceImpl.deleteMovement(1L);

        assertTrue(deletedMovement.isPresent());
        assertEquals("123456", deletedMovement.get().getAccountNumber());
        verify(movementRepository, times(1)).findById(1L);
        verify(movementRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testGetAllMovementsThrowsResourceNotFoundException() {
        when(movementRepository.findAll()).thenReturn(List.of());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            movementServiceImpl.getAllMovements();
        });

        assertEquals("movement", exception.getResourceName());
        verify(movementRepository, times(1)).findAll();
    }

    @Test
    public void testGetMovementByIdThrowsResourceNotFoundException() {
        when(movementRepository.findById(1L)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            movementServiceImpl.getMovementById(1L);
        });

        assertEquals("movement", exception.getResourceName());
        verify(movementRepository, times(1)).findById(1L);
    }

    @Test
    public void testSaveMovementThrowsBussinessExceptionWhenAccountNotExists() {
        when(accountService.getAccountByAccountNumber("123456")).thenReturn(Optional.empty());

        BussinessException exception = assertThrows(BussinessException.class, () -> {
            movementServiceImpl.saveMovement(movement);
        });

        assertEquals("The accountNumber: 123456 does not exist in the Database.", exception.getMessage());
        verify(accountService, times(1)).getAccountByAccountNumber("123456");
    }

    @Test
    public void testUpdateMovementThrowsResourceNotFoundException() {
        when(movementRepository.findById(1L)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            movementServiceImpl.updateMovement(1L, movement);
        });

        assertEquals("movement", exception.getResourceName());
        verify(movementRepository, times(1)).findById(1L);
    }

    @Test
    public void testDeleteMovementThrowsResourceNotFoundException() {
        when(movementRepository.findById(1L)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            movementServiceImpl.deleteMovement(1L);
        });

        assertEquals("movement", exception.getResourceName());
        verify(movementRepository, times(1)).findById(1L);
    }
}
