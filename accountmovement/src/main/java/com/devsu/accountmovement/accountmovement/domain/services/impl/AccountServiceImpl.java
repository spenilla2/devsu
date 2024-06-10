package com.devsu.accountmovement.accountmovement.domain.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsu.accountmovement.accountmovement.domain.exceptions.BussinessException;
import com.devsu.accountmovement.accountmovement.domain.exceptions.ResourceNotFoundException;
import com.devsu.accountmovement.accountmovement.domain.models.Account;
import com.devsu.accountmovement.accountmovement.domain.models.Client;
import com.devsu.accountmovement.accountmovement.domain.services.AccountService;
import com.devsu.accountmovement.accountmovement.domain.services.ClientService;
import com.devsu.accountmovement.accountmovement.infrastructure.repositories.AccountRepository;
import com.devsu.accountmovement.accountmovement.infrastructure.repositories.mapper.AccountMapperEntity;

@Service
public class AccountServiceImpl implements AccountService{
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private ClientService clientService;  
    public List<Account> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll().stream().map(account -> AccountMapperEntity.toAccount(account)).toList();
        if(accounts.isEmpty()){
            throw new ResourceNotFoundException("account");
        }
        return accounts;
    }

    @Override
    public Optional<Account> getAccountByAccountNumber(String accountNumber) {    
        Optional<Account> accountSearch = accountRepository.findByAccountNumber(accountNumber).map(account->AccountMapperEntity.toAccount(account));
        if(accountSearch.isEmpty()){
            throw new ResourceNotFoundException("account", "accountNumber", accountNumber);
        }    
        return accountSearch;
    }

    @Override
    public Optional<Account> saveAccount(Account accountToSave) {
        Optional<Account> accountToSaveSearch = accountRepository.findByAccountNumber(accountToSave.getAccountNumber()).map(account->AccountMapperEntity.toAccount(account));
        if(accountToSaveSearch.isPresent()){
            throw new BussinessException("The account width accountNumber "+accountToSave.getAccountNumber()+" already exists");
        }        
        try{
            Optional<Client> client = clientService.getClientByIdentification(accountToSave.getClient().getIdentification());
            accountToSave.setClient(client.get());        
            Optional<Account> accountSaved = Optional.of(accountRepository.save(AccountMapperEntity.toAccountEntity(accountToSave))).map(accountSave->AccountMapperEntity.toAccount(accountSave));
            if(accountSaved.isEmpty()){
                throw new BussinessException("Problem to Save the Account in Database");    
            }
            return accountSaved;
        }catch(Exception e){
            throw new BussinessException("The client with identification "+accountToSave.getClient().getIdentification()+" does not exist");
        }
    }

    @Override
    public Optional<Account> updateAccount(String accountNumber, Account accountUpdate) {
        Optional<Account> accountSearch = accountRepository.findByAccountNumber(accountNumber).map(accountExist->AccountMapperEntity.toAccount(accountExist));
        if(accountSearch.isEmpty()){
            throw new ResourceNotFoundException("account", "accountNumber", accountNumber);
        }
        Optional<Client> client = clientService.getClientByIdentification(accountUpdate.getClient().getIdentification());
        if(!accountUpdate.getClient().getIdentification().equals(accountSearch.get().getClient().getIdentification())){
              throw new BussinessException("The client with identification to update is not the same client identification registred to the account in Database");
         }
        if(!accountSearch.get().getAccountNumber().equals(accountUpdate.getAccountNumber())){
            throw new BussinessException("The accountNumber to update is diferent to the accountNumber registred to the account in Database");
        }
        accountUpdate.setClient(client.get());
        accountUpdate.setId(accountSearch.get().getId());
        Optional<Account> accountUpdated = Optional.of(accountRepository.save(AccountMapperEntity.toAccountEntity(accountUpdate))).map(accountSave->AccountMapperEntity.toAccount(accountSave));
        if(accountUpdated.isEmpty()){
                throw new BussinessException("Problem to Update the Account in Database");    
            }
            return accountUpdated;
    }

    @Override
    public Optional<Account> deleteAccountByAccountNumber(String accountNumber) {
        Optional<Account> accountSearch = accountRepository.findByAccountNumber(accountNumber).map(account->AccountMapperEntity.toAccount(account));
        if(accountSearch.isEmpty()){
            throw new ResourceNotFoundException("account", "accountNumber", accountNumber);
        }
        accountRepository.deleteByAccountNumber(accountNumber);
        return accountSearch;
        
    }
}
