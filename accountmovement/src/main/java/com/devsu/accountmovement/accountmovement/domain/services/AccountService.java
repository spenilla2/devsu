package com.devsu.accountmovement.accountmovement.domain.services;
import com.devsu.accountmovement.accountmovement.domain.models.Account;

import java.util.List;
import java.util.Optional;

public interface AccountService {
    public List<Account> getAllAccounts();
    public Optional<Account> getAccountByAccountNumber(String accountNumber);
    public Optional<Account> saveAccount(Account account);
    public Optional<Account> updateAccount(String accountNumber, Account account);
    public Optional<Account> deleteAccountByAccountNumber(String accountNumber);    
}
