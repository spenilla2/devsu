package com.devsu.accountmovement.accountmovement.domain.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsu.accountmovement.accountmovement.domain.exceptions.BussinessException;
import com.devsu.accountmovement.accountmovement.domain.exceptions.ResourceNotFoundException;
import com.devsu.accountmovement.accountmovement.domain.models.Account;
import com.devsu.accountmovement.accountmovement.domain.models.Movement;

import com.devsu.accountmovement.accountmovement.domain.services.AccountService;
import com.devsu.accountmovement.accountmovement.domain.services.MovementService;
import com.devsu.accountmovement.accountmovement.infrastructure.repositories.MovementRepository;
import com.devsu.accountmovement.accountmovement.infrastructure.repositories.mapper.MovementMapperEntity;

@Service
public class MovementServiceImpl implements MovementService{
    
    @Autowired
    private MovementRepository movementRepository;
    @Autowired
    private AccountService accountService;

    @Override
    public List<Movement> getAllMovements() {
        List<Movement> movements = movementRepository.findAll().stream().map(movement -> MovementMapperEntity.toMovement(movement)).toList();
        if(movements.isEmpty()){
            throw new ResourceNotFoundException("movement");
        }
        return movements;
    }
    @Override
    public Optional<Movement> getMovementById(Long id) {
        Optional<Movement> movementSearch = movementRepository.findById(id).map(movement->MovementMapperEntity.toMovement(movement));
        if(movementSearch.isEmpty()){
            throw new ResourceNotFoundException("movement", "id", id);
        }
        return movementSearch;
    }

    @Override
    public Optional<Movement> saveMovement(Movement movement) {
        Optional<Account> account = accountService.getAccountByAccountNumber(movement.getAccountNumber());
        if(account.isEmpty()){
            throw new BussinessException("The accountNumber: "+movement.getAccountNumber()+" does not exist in the Database.");
        }
        Long accountBalance = account.get().getAccountBalance();
        Long finalBalance = accountBalance + movement.getMovementDetail();
        if (finalBalance < 0) {
            throw new BussinessException("The accountNumber: "+movement.getAccountNumber()+" dont have enough balance to make the movement.");
        }
        Account accountUpdate = new Account();
        accountUpdate.setAccountNumber(account.get().getAccountNumber());
        accountUpdate.setAccountType(account.get().getAccountType());
        accountUpdate.setAccountBalance(finalBalance);
        accountUpdate.setStatus(account.get().isStatus());
        accountUpdate.setClient(account.get().getClient());        
        accountService.updateAccount(account.get().getAccountNumber(), accountUpdate);

        movement.setInitialBalance(accountBalance);
        movement.setFinalBalance(finalBalance);
        movement.setAccountType(account.get().getAccountType());        
        Optional<Movement> movementSave = Optional.of(movementRepository.save(MovementMapperEntity.toMovementEntity(movement))).map(movementSaved->MovementMapperEntity.toMovement(movementSaved));        
        if(movementSave.isEmpty()){
            throw new BussinessException("Problem to Save Movement in Database");
        }
        return movementSave;
    }

    @Override
    public Optional<Movement> updateMovement(Long id, Movement movement) {
        Optional<Movement> movementSearch = movementRepository.findById(id).map(movementFind->MovementMapperEntity.toMovement(movementFind));
        if(movementSearch.isEmpty()){
            throw new ResourceNotFoundException("movement", "id", id);
        }
        Optional<Account> account = accountService.getAccountByAccountNumber(movement.getAccountNumber());
        if(account.isEmpty()){
            throw new BussinessException("The accountNumber: "+movement.getAccountNumber()+" to Update dont exist in the Database.");
        }
        if(!movement.getAccountNumber().equals(movementSearch.get().getAccountNumber())){
            throw new BussinessException("The accountNumber: "+movement.getAccountNumber()+" is diferent to Movement width id"+id);
        }
        Long initialBalance = account.get().getAccountBalance();
        Long finalBalance = initialBalance + movement.getMovementDetail();
        if (finalBalance < 0) {
            throw new BussinessException("The accountNumber: "+movement.getAccountNumber()+" dont have enough balance to make the movement.");
        }
        Account accountUpdate = new Account();
        accountUpdate.setAccountNumber(account.get().getAccountNumber());
        accountUpdate.setAccountType(account.get().getAccountType());
        accountUpdate.setAccountBalance(finalBalance);
        accountUpdate.setStatus(account.get().isStatus());
        accountUpdate.setClient(account.get().getClient());        
        accountService.updateAccount(account.get().getAccountNumber(), accountUpdate);
        movement.setAccountType(movementSearch.get().getAccountType());
        movement.setInitialBalance(movementSearch.get().getInitialBalance());
        movement.setFinalBalance(movementSearch.get().getFinalBalance());            
        Optional<Movement> movementUpdate = Optional.of(movementRepository.save(MovementMapperEntity.toMovementEntity(movement))).map(movementUpdated->MovementMapperEntity.toMovement(movementUpdated));               
        if(movementUpdate.isEmpty()){
            throw new BussinessException("Problem to Update Movement in Database");
        }
        return movementUpdate;            
    }

    @Override
    public Optional<Movement> deleteMovement(Long id) {
        Optional<Movement> movementSearch = movementRepository.findById(id).map(movementFind->MovementMapperEntity.toMovement(movementFind));
        if(movementSearch.isEmpty()){
            throw new ResourceNotFoundException("movement", "id", id);
        }
        Optional<Account> account = accountService.getAccountByAccountNumber(movementSearch.get().getAccountNumber());
        Long accountBalance = account.get().getAccountBalance();
        Long movement = movementSearch.get().getMovementDetail();
        Long finalBalance = 0L;
        if(movement<0){
            finalBalance = accountBalance + movement;
        }else{
            finalBalance = accountBalance - movement;
        }        
        Account accountUpdate = new Account();
        accountUpdate.setAccountNumber(account.get().getAccountNumber());
        accountUpdate.setAccountType(account.get().getAccountType());
        accountUpdate.setAccountBalance(finalBalance);
        accountUpdate.setStatus(account.get().isStatus());
        accountUpdate.setClient(account.get().getClient());        
        accountService.updateAccount(account.get().getAccountNumber(), accountUpdate);
        
        movementRepository.deleteById(id);
        return movementSearch;
    }
} 