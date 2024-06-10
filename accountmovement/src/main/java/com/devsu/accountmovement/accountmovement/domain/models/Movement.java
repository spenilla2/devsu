package com.devsu.accountmovement.accountmovement.domain.models;

import java.time.LocalDate;


import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class Movement extends Account {
    private Long id;
    private String accountNumber;
    private String accountType;
    private Long initialBalance;
    private boolean status;
    private Long movementDetail;
    private Long finalBalance;
    private LocalDate dateMovement;
}
