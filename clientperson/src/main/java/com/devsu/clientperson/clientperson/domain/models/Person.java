package com.devsu.clientperson.clientperson.domain.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class Person {
    private Long id;
    private String name;
    private String gender;
    private int age;
    private String identification;
    private String address;
    private String phone;
}
