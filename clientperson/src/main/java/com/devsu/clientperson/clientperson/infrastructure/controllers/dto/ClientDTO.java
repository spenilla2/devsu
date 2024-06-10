package com.devsu.clientperson.clientperson.infrastructure.controllers.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class ClientDTO {
    private Long id;
    @Size(min = 2, max = 150, message = "Name must be between 2 and 150 characters")
    @NotEmpty(message = "Name Required!")
    private String name;
    private String gender;
    private int age;
    @NotEmpty(message = "Identification is Required!")
    private String identification;
    private String address;
    private String phone;
    private String clientId;
    @Size(min = 2, max = 150, message = "Name must be between 2 and 150 characters")
    private String password;
    private boolean status;

}    