package com.devsu.clientperson.clientperson.domain.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class Client extends Person {  

    private String clientId;
    private String password;
    private boolean status;
    }

