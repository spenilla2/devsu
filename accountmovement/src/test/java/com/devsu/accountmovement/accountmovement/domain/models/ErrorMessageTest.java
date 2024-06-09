package com.devsu.accountmovement.accountmovement.domain.models;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class ErrorMessageTest {

    private ErrorMessage errorMessage;

    @BeforeEach
    public void setUp() {
        errorMessage = ErrorMessage.builder()
                .code("ERR001")
                .message("An error occurred")
                .build();
    }

    @Test
    public void testGetters() {
        assertEquals("ERR001", errorMessage.getCode());
        assertEquals("An error occurred", errorMessage.getMessage());
    }

    @Test
    public void testSetters() {
        errorMessage.setCode("ERR002");
        assertEquals("ERR002", errorMessage.getCode());

        errorMessage.setMessage("Another error occurred");
        assertEquals("Another error occurred", errorMessage.getMessage());
    }

    @Test
    public void testNoArgsConstructor() {
        ErrorMessage errorMessage = new ErrorMessage();
        assertNull(errorMessage.getCode());
        assertNull(errorMessage.getMessage());
    }

    @Test
    public void testAllArgsConstructor() {
        ErrorMessage errorMessage = new ErrorMessage("ERR002", "Another error occurred");
        assertEquals("ERR002", errorMessage.getCode());
        assertEquals("Another error occurred", errorMessage.getMessage());
    }

    @Test
    public void testRequiredArgsConstructor() {
        ErrorMessage errorMessage = new ErrorMessage();
        assertNull(errorMessage.getCode());
        assertNull(errorMessage.getMessage());
    }
}
