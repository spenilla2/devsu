package com.devsu.accountmovement.accountmovement.infrastructure.repositories.mapper;

import com.devsu.accountmovement.accountmovement.domain.models.Movement;
import com.devsu.accountmovement.accountmovement.infrastructure.repositories.entities.MovementEntity;

public class MovementMapperEntity {
    public static Movement toMovement(MovementEntity movementEntity) {
        Movement movement = new Movement();
        movement.setId(movementEntity.getId());
        movement.setAccountNumber(movementEntity.getAccountNumber());
        movement.setAccountType(movementEntity.getAccountType());
        movement.setInitialBalance(movementEntity.getInitialBalance());
        movement.setStatus(movementEntity.isStatus());
        movement.setMovementDetail(movementEntity.getMovementDetail());
        movement.setFinalBalance(movementEntity.getFinalBalance());
        movement.setDateMovement(movementEntity.getDateMovement());
        return movement;
    }
    public static MovementEntity toMovementEntity(Movement movement) {
        MovementEntity movementEntity = new MovementEntity();
        movementEntity.setId(movement.getId());
        movementEntity.setAccountNumber(movement.getAccountNumber());
        movementEntity.setAccountType(movement.getAccountType());
        movementEntity.setInitialBalance(movement.getInitialBalance());
        movementEntity.setStatus(movement.isStatus());
        movementEntity.setMovementDetail(movement.getMovementDetail());
        movementEntity.setFinalBalance(movement.getFinalBalance());
        movementEntity.setDateMovement(movement.getDateMovement());
        return movementEntity;
    }
}
