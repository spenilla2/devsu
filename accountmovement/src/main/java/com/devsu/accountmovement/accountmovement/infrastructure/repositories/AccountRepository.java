package com.devsu.accountmovement.accountmovement.infrastructure.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.devsu.accountmovement.accountmovement.infrastructure.repositories.entities.AccountEntity;

import jakarta.transaction.Transactional;

public interface AccountRepository extends JpaRepository<AccountEntity, Long>{
   Optional<AccountEntity> findByAccountNumber(String accountNumber);
   @Transactional
   @Modifying
   @Query("DELETE FROM account a WHERE a.accountNumber = :accountNumber")
   void deleteByAccountNumber(String accountNumber);

}
