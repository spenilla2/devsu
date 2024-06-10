package com.devsu.accountmovement.accountmovement.domain.models;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
public class MovementsTest {

    private Movement movement;

    @BeforeEach
    public void setUp() {
        movement = new Movement();
        movement.setId(1L);
        movement.setAccountNumber("123456");
        movement.setAccountType("Saving");
        movement.setInitialBalance(1000L);
        movement.setStatus(true);
        movement.setMovementDetail(500L);
        movement.setFinalBalance(1500L);
        movement.setDateMovement(LocalDate.of(2023, 6, 8));
    }

    @Test
    public void testGetters() {
        assertEquals(1L, movement.getId());
        assertEquals("123456", movement.getAccountNumber());
        assertEquals("Saving", movement.getAccountType());
        assertEquals(1000L, movement.getInitialBalance());
        assertTrue(movement.isStatus());
        assertEquals(500L, movement.getMovementDetail());
        assertEquals(1500L, movement.getFinalBalance());
        assertEquals(LocalDate.of(2023, 6, 8), movement.getDateMovement());
    }

    @Test
    public void testSetters() {
        movement.setId(2L);
        assertEquals(2L, movement.getId());

        movement.setAccountNumber("654321");
        assertEquals("654321", movement.getAccountNumber());

        movement.setAccountType("Checking");
        assertEquals("Checking", movement.getAccountType());

        movement.setInitialBalance(2000L);
        assertEquals(2000L, movement.getInitialBalance());

        movement.setStatus(false);
        assertFalse(movement.isStatus());

        movement.setMovementDetail(1000L);
        assertEquals(1000L, movement.getMovementDetail());

        movement.setFinalBalance(3000L);
        assertEquals(3000L, movement.getFinalBalance());

        movement.setDateMovement(LocalDate.of(2024, 1, 1));
        assertEquals(LocalDate.of(2024, 1, 1), movement.getDateMovement());
    }

    @Test
    public void testNoArgsConstructor() {
        Movement movement = new Movement();
        assertNull(movement.getId());
        assertNull(movement.getAccountNumber());
        assertNull(movement.getAccountType());
        assertNull(movement.getInitialBalance());
        assertFalse(movement.isStatus());
        assertNull(movement.getMovementDetail());
        assertNull(movement.getFinalBalance());
        assertNull(movement.getDateMovement());
    }

    @Test
    public void testAllArgsConstructorAndBuilder() {
        Movement movement = Movement.builder()
                .id(2L)
                .accountNumber("654321")
                .accountType("Checking")
                .initialBalance(2000L)
                .status(false)
                .movementDetail(1000L)
                .finalBalance(3000L)
                .dateMovement(LocalDate.of(2024, 1, 1))
                .build();

        assertEquals(2L, movement.getId());
        assertEquals("654321", movement.getAccountNumber());
        assertEquals("Checking", movement.getAccountType());
        assertEquals(2000L, movement.getInitialBalance());
        assertFalse(movement.isStatus());
        assertEquals(1000L, movement.getMovementDetail());
        assertEquals(3000L, movement.getFinalBalance());
        assertEquals(LocalDate.of(2024, 1, 1), movement.getDateMovement());
    }

}
