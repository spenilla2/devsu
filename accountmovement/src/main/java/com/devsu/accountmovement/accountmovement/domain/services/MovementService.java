package com.devsu.accountmovement.accountmovement.domain.services;

import java.util.List;
import java.util.Optional;

import com.devsu.accountmovement.accountmovement.domain.models.Movement;

public interface MovementService {
    public List<Movement> getAllMovements();
    public Optional<Movement> getMovementById(Long id);
    public Optional<Movement> saveMovement(Movement movement);
    public Optional<Movement> updateMovement(Long id, Movement movement);
    public Optional<Movement> deleteMovement(Long id);
}
