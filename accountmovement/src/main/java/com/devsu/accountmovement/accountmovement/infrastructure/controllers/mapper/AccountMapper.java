package com.devsu.accountmovement.accountmovement.infrastructure.controllers.mapper;

import com.devsu.accountmovement.accountmovement.domain.models.Account;
import com.devsu.accountmovement.accountmovement.domain.models.Client;
import com.devsu.accountmovement.accountmovement.infrastructure.controllers.dto.AccountDTO;
import com.devsu.accountmovement.accountmovement.infrastructure.controllers.dto.AccountDTOResponse;

public class AccountMapper {
    public static Account toAccount(AccountDTO accountDTO){
        Account account = new Account();
        account.setAccountNumber(accountDTO.getAccountNumber());
        account.setAccountType(accountDTO.getAccountType());
        account.setAccountBalance(accountDTO.getAccountBalance());
        account.setStatus(accountDTO.isStatus());
        account.setClient(Client.builder().identification(accountDTO.getIdentification()).build());
        return account;        
    }
    public static AccountDTOResponse toAccountDTOResponse(Account account){
        AccountDTOResponse accountDTOResponse = new AccountDTOResponse();
        accountDTOResponse.setId(account.getId());
        accountDTOResponse.setAccountNumber(account.getAccountNumber());
        accountDTOResponse.setAccountType(account.getAccountType());
        accountDTOResponse.setAccountBalance(account.getAccountBalance());
        accountDTOResponse.setStatus(account.isStatus());
        accountDTOResponse.setClientName(account.getClient().getName());
        return accountDTOResponse;        
    }
    
}
