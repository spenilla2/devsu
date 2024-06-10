package com.devsu.accountmovement.accountmovement.infrastructure.controllers.mapper;

import com.devsu.accountmovement.accountmovement.domain.models.Movement;
import com.devsu.accountmovement.accountmovement.infrastructure.controllers.dto.MovementDTO;
import com.devsu.accountmovement.accountmovement.infrastructure.controllers.dto.MovementDTOResponse;

public class MovementMapper {
    public static Movement toMovement(MovementDTO movementDTO){
        Movement movement = new Movement();        
        movement.setAccountNumber(movementDTO.getAccountNumber());
        movement.setStatus(movementDTO.isStatus());
        movement.setDateMovement(movementDTO.getDateMovement());
        movement.setMovementDetail(movementDTO.getMovementDetail());        
        return movement;        
    }
    public static MovementDTOResponse toMovementDTOResponse(Movement movement){
        MovementDTOResponse movementDTOResponse = new MovementDTOResponse();
        movementDTOResponse.setId(movement.getId());
        movementDTOResponse.setAccountNumber(movement.getAccountNumber());
        movementDTOResponse.setAccountType(movement.getAccountType());
        movementDTOResponse.setInitialBalance(movement.getInitialBalance());
        movementDTOResponse.setStatus(movement.isStatus());
        movementDTOResponse.setMovementDetail(movement.getMovementDetail());
        movementDTOResponse.setFinalBalance(movement.getFinalBalance());
        movementDTOResponse.setDateMovement(movement.getDateMovement());
        return movementDTOResponse;        
    }
}
