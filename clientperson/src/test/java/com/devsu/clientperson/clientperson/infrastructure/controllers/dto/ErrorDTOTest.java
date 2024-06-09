package com.devsu.clientperson.clientperson.infrastructure.controllers.dto;

import org.junit.jupiter.api.Test;

import com.devsu.clientperson.clientperson.domain.models.ErrorMessage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ErrorDTOTest {
    @Test
    public void testAllArgsConstructor() {
        // Arrange
        String code = "404";
        String message = "Not Found";

        // Act
        ErrorMessage errorDTO = new ErrorMessage(code, message);

        // Assert
        assertNotNull(errorDTO);
        assertEquals(code, errorDTO.getCode());
        assertEquals(message, errorDTO.getMessage());
    }

    @Test
    public void testRequiredArgsConstructor() {
        // Act
        ErrorMessage errorDTO = new ErrorMessage();

        // Assert
        assertNotNull(errorDTO);
        errorDTO.setCode("500");
        errorDTO.setMessage("Internal Server Error");
        assertEquals("500", errorDTO.getCode());
        assertEquals("Internal Server Error", errorDTO.getMessage());
    }

    @Test
    public void testBuilder() {
        // Arrange
        String code = "400";
        String message = "Bad Request";

        // Act
        ErrorMessage errorDTO = ErrorMessage.builder()
                .code(code)
                .message(message)
                .build();

        // Assert
        assertNotNull(errorDTO);
        assertEquals(code, errorDTO.getCode());
        assertEquals(message, errorDTO.getMessage());
    }

    @Test
    public void testSettersAndGetters() {
        // Arrange
        ErrorMessage errorDTO = new ErrorMessage();
        String code = "403";
        String message = "Forbidden";

        // Act
        errorDTO.setCode(code);
        errorDTO.setMessage(message);

        // Assert
        assertEquals(code, errorDTO.getCode());
        assertEquals(message, errorDTO.getMessage());
    }
}
