package com.devsu.clientperson.clientperson.infrastructure.controllers;

import com.devsu.clientperson.clientperson.infrastructure.controllers.dto.ClientDTO;
import com.devsu.clientperson.clientperson.infrastructure.controllers.mapper.ClientDTOMapper;

import org.springframework.web.bind.annotation.*;

import com.devsu.clientperson.clientperson.domain.services.impl.ClientServiceImpl;
import com.devsu.clientperson.clientperson.domain.models.Client;

import java.util.List;
import java.util.Optional;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/client")
public class ClientController {
    @Autowired
    private ClientServiceImpl clientServiceImpl;
    
    @GetMapping("/clients")
    public List<Client> getAllClients() {
        return clientServiceImpl.getAllClients();
    }
    
    @GetMapping("/{identification}")
    public ResponseEntity<Client> getClientByIdentification(@PathVariable String identification) {        
        return ResponseEntity.ok(clientServiceImpl.getClientByIdentification(identification).get());        
    }
    @PostMapping
    public ResponseEntity<Client> createClient(@Valid @RequestBody ClientDTO clientDTO) {        
        return ResponseEntity.status(HttpStatus.CREATED).body(clientServiceImpl.saveClient(ClientDTOMapper.toClient(clientDTO)).get());

    }
    
    @PutMapping ("/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable Long id, @Valid @RequestBody ClientDTO clientDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(clientServiceImpl.updateClient(id, ClientDTOMapper.toClient(clientDTO)).get());
    }   
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Client> deleteClient(@PathVariable Long id) {        
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(clientServiceImpl.deleteClient(id).get());    
    } 

}
