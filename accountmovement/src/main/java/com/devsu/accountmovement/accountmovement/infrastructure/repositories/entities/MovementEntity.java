package com.devsu.accountmovement.accountmovement.infrastructure.repositories.entities;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity(name = "movement")
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class MovementEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String accountNumber;
    private String accountType;
    private Long initialBalance;
    private boolean status;
    private Long movementDetail;
    private Long finalBalance;
    private LocalDate dateMovement;
}
