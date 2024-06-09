package com.devsu.accountmovement.accountmovement.domain.models;

import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class Account {
    private Long id;
    private String accountNumber;
    private String accountType;
    private Long initialBalance;
    private boolean status;
    private Client client;
    
}
