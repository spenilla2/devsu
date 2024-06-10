package com.devsu.accountmovement.accountmovement.infrastructure.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.devsu.accountmovement.accountmovement.domain.models.Movement;
import com.devsu.accountmovement.accountmovement.domain.services.impl.MovementServiceImpl;

import com.devsu.accountmovement.accountmovement.infrastructure.controllers.dto.MovementDTO;
import com.devsu.accountmovement.accountmovement.infrastructure.controllers.dto.MovementDTOResponse;
import com.devsu.accountmovement.accountmovement.infrastructure.controllers.mapper.MovementMapper;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/movement")
public class MovementController {
    @Autowired
    private MovementServiceImpl movementServiceImpl;

    @GetMapping("/movements")
    public List<MovementDTOResponse> getAllMovements(){        
        return movementServiceImpl.getAllMovements().stream().map(movement -> MovementMapper.toMovementDTOResponse(movement)).toList();        
    }
    @GetMapping("/{id}")
    public ResponseEntity<MovementDTOResponse> getMovementById(@PathVariable Long id){ 
        return ResponseEntity.ok(MovementMapper.toMovementDTOResponse(movementServiceImpl.getMovementById(id).get()));            
                        
    }
    @PostMapping
    public ResponseEntity<MovementDTOResponse> createMovement(@Valid @RequestBody MovementDTO movementDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(MovementMapper.toMovementDTOResponse(movementServiceImpl.saveMovement(MovementMapper.toMovement(movementDTO)).get()));
    }
    @PutMapping("/{id}")
    public ResponseEntity<MovementDTOResponse> updateMovement(@PathVariable Long id,@Valid @RequestBody MovementDTO movementDTO){      
            return ResponseEntity.status(HttpStatus.OK).body(MovementMapper.toMovementDTOResponse(movementServiceImpl.updateMovement(id, MovementMapper.toMovement(movementDTO)).get()));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Movement> deleteAccount(@PathVariable Long id){ 
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(movementServiceImpl.deleteMovement(id).get());
                        
    }    
}
