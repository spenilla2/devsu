package com.devsu.accountmovement.accountmovement.domain.models;

import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class Client {
    
    private Long idClient;
    private String identification;
    private String name;


}
