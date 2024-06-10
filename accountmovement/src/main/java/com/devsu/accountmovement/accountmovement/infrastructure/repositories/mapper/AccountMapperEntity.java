package com.devsu.accountmovement.accountmovement.infrastructure.repositories.mapper;

import com.devsu.accountmovement.accountmovement.infrastructure.repositories.entities.AccountEntity;
import com.devsu.accountmovement.accountmovement.domain.models.Account;
import com.devsu.accountmovement.accountmovement.domain.models.Client;

public class AccountMapperEntity {

    public static Account toAccount(AccountEntity accountEntity) {
        Account account = new Account();
        account.setId(accountEntity.getId());
        account.setAccountNumber(accountEntity.getAccountNumber());
        account.setAccountType(accountEntity.getAccountType());
        account.setAccountBalance(accountEntity.getAccountBalance());
        account.setStatus(accountEntity.isStatus());
        account.setClient(Client.builder().identification(accountEntity.getIdentification())
                                          .name(accountEntity.getName()).build());
        return account;
    }

    public static AccountEntity toAccountEntity(Account account) {
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setId(account.getId());
        accountEntity.setAccountNumber(account.getAccountNumber());
        accountEntity.setAccountType(account.getAccountType());
        accountEntity.setAccountBalance(account.getAccountBalance());
        accountEntity.setStatus(account.isStatus());
        accountEntity.setIdentification(account.getClient().getIdentification());
        accountEntity.setName(account.getClient().getName());
        return accountEntity;
    }
    
}
