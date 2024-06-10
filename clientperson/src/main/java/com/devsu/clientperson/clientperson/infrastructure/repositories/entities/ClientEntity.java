package com.devsu.clientperson.clientperson.infrastructure.repositories.entities;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity(name = "client")
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor

public class ClientEntity extends PersonEntity {  

    private String clientId;
    private String password;
    private boolean status;
        
}

