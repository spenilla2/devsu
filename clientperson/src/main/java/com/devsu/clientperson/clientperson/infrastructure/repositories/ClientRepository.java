package com.devsu.clientperson.clientperson.infrastructure.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import jakarta.transaction.Transactional;

import com.devsu.clientperson.clientperson.infrastructure.repositories.entities.ClientEntity;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<ClientEntity, Long>{
    Optional<ClientEntity> findByIdentification(String identification);
    @Transactional
    @Modifying
    @Query("DELETE FROM  client c where c.identification = :identification")
    void deleteClientByIdentification(String identification);
}
