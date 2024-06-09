package com.devsu.accountmovement.accountmovement.infrastructure.controllers;



import com.devsu.accountmovement.accountmovement.domain.models.Account;
import com.devsu.accountmovement.accountmovement.domain.services.impl.AccountServiceImpl;
import com.devsu.accountmovement.accountmovement.infrastructure.controllers.dto.AccountDTO;
import com.devsu.accountmovement.accountmovement.infrastructure.controllers.dto.AccountDTOResponse;
import com.devsu.accountmovement.accountmovement.infrastructure.controllers.mapper.AccountMapper;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;



@RestController
@RequestMapping("/api/account")
public class AccountController {
    @Autowired
    private AccountServiceImpl accountServiceImpl;

    @GetMapping("/accounts")
    public List<AccountDTOResponse> getAllAccounts(){
        return accountServiceImpl.getAllAccounts().stream().map(account -> AccountMapper.toAccountDTOResponse(account)).toList();        
    }

    @GetMapping("/{accountNumber}")
    public ResponseEntity<AccountDTOResponse> getAccountByIdentification(@PathVariable String accountNumber){        
        return ResponseEntity.ok(AccountMapper.toAccountDTOResponse(accountServiceImpl.getAccountByAccountNumber(accountNumber).get()));        
    }
    @PostMapping
    public ResponseEntity<AccountDTOResponse> createAccount(@Valid @RequestBody AccountDTO accountDTO){ 
        return ResponseEntity.status(HttpStatus.CREATED).body(AccountMapper.toAccountDTOResponse(accountServiceImpl.saveAccount(AccountMapper.toAccount(accountDTO)).get()));        
    }
    @PutMapping("/{accountNumber}")
    public ResponseEntity<AccountDTOResponse> updateAccount(@PathVariable String accountNumber,@Valid @RequestBody AccountDTO accountDTO){    
        return ResponseEntity.status(HttpStatus.OK).body(AccountMapper.toAccountDTOResponse(accountServiceImpl.updateAccount(accountNumber, AccountMapper.toAccount(accountDTO)).get()));
        
    }
    @DeleteMapping("/{accountNumber}")
    public ResponseEntity<AccountDTOResponse> deleteAccount(@PathVariable String accountNumber){
        Optional<Account> accountDeleted = accountServiceImpl.deleteAccountByAccountNumber(accountNumber);                 
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(AccountMapper.toAccountDTOResponse(accountDeleted.get()));
                        
    }
}
