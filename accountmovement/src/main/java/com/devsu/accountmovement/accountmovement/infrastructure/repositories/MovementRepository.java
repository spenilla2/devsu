package com.devsu.accountmovement.accountmovement.infrastructure.repositories;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsu.accountmovement.accountmovement.infrastructure.repositories.entities.MovementEntity;


public interface MovementRepository extends JpaRepository<MovementEntity, Long>{
    @Query(value = "SELECT m.date_movement as date, " +
               "a.name as clientName, " +
               "a.account_number as accountNumber, " +
               "a.account_type as accountType, " +
               "a.initial_balance as initialBalance, " +
               "m.status as state, " +
               "m.movement_detail as movementDetail, " +
               "m.final_balance as finalBalance " +
               "FROM movement m " +
               "JOIN account a ON m.account_number = a.account_number " +
               "WHERE m.date_movement BETWEEN :iniDate AND :endDate " +
               "AND a.identification = :identification", nativeQuery = true)
    public List<Map<String, Object>> getReports(LocalDate iniDate, LocalDate endDate, String identification);
}
