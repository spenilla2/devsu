package com.devsu.accountmovement.accountmovement.infrastructure.controllers.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class MovementDTO {
    @Size(min = 3, max = 10, message = "The accountNumber must be between 2 and 150 characters")
    @NotEmpty(message = "accountNumeber is Required!")
    private String accountNumber;
    private Long movementDetail;    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dateMovement;
    private boolean status;
        
}
