package com.devsu.clientperson.clientperson.infrastructure.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devsu.clientperson.clientperson.infrastructure.repositories.entities.ClientEntity;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<ClientEntity, Long>{
    Optional<ClientEntity> findByIdentification(String identification);
}
