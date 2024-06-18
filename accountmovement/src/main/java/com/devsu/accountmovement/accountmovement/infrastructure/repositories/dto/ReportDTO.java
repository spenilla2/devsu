package com.devsu.accountmovement.accountmovement.infrastructure.repositories.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ReportDTO {   
    private int index; 
    private LocalDate date;
    private String clientName;
    private String accountNumber;
    private String accountType;
    private Long initialBalance;
    private boolean state;
    private Long movementDetail;
    private Long finalBalance;
    
}
