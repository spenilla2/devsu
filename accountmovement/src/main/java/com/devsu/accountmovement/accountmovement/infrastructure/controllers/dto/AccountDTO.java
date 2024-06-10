package com.devsu.accountmovement.accountmovement.infrastructure.controllers.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class AccountDTO {
    private Long id;
    @Size(min = 3, max = 10, message = "The accountNumber must be between 2 and 150 characters")
    @NotEmpty(message = "accountNumeber is Required!")
    private String accountNumber;
    private String accountType;
    private Long accountBalance;
    private boolean status;
    @NotEmpty(message = "The identification of Client is Required!")
    private String identification;

    public AccountDTO(String number, String saving, long l, boolean b, Object o) {
    }
}
