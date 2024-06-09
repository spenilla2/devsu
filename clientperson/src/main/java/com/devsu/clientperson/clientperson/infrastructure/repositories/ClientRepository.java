package com.devsu.clientperson.clientperson.infrastructure.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.devsu.clientperson.clientperson.infrastructure.repositories.entities.ClientEntity;

import jakarta.transaction.Transactional;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<ClientEntity, Long>{
    Optional<ClientEntity> findByIdentification(String identification);
    @Transactional
    @Modifying
    @Query("DELETE FROM client c WHERE c.identification = :identification")
    void deleteByIdentification(String identification);

}
