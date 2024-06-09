package com.devsu.clientperson.clientperson.domain.models;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class ErrorMessage {
    private String code;
    private String message;
}
