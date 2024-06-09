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

    public ReportDTO() {
    }
    public ReportDTO(int index, LocalDate date, String clientName, String accountNumber, String accountType, Long initialBalance,
            boolean state, Long movementDetail, Long finalBalance) {
        this.index = index; 
        this.date = date;
        this.clientName = clientName;
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.initialBalance = initialBalance;
        this.state = state;
        this.movementDetail = movementDetail;
        this.finalBalance = finalBalance;
    }
    
}
