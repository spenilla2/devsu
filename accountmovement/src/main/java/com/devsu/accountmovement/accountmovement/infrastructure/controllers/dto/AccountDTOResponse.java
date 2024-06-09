package com.devsu.accountmovement.accountmovement.infrastructure.controllers.dto;

import lombok.Data;

@Data
public class AccountDTOResponse {
    private Long id;
    private String accountNumber;
    private String accountType;
    private Long initialBalance;
    private boolean status;
    private String clientName;    
}
