package com.devsu.accountmovement.accountmovement.infrastructure.controllers.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class MovementDTOResponse {
    private Long id;
    private String accountNumber;
    private String accountType;
    private Long initialBalance;
    private boolean status;
    private Long movementDetail;
    private Long finalBalance;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dateMovement;
        
}
