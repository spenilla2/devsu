package com.devsu.clientperson.clientperson.infrastructure.controllers;

import com.devsu.clientperson.clientperson.infrastructure.controllers.dto.ClientDTO;
import com.devsu.clientperson.clientperson.infrastructure.controllers.mapper.ClientDTOMapper;

import org.springframework.web.bind.annotation.*;

import com.devsu.clientperson.clientperson.domain.services.impl.ClientServiceImpl;
import com.devsu.clientperson.clientperson.domain.models.Client;

import java.util.List;

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
    
    @PutMapping ("/{identification}")
    public ResponseEntity<Client> updateClient(@PathVariable String identification, @Valid @RequestBody ClientDTO clientDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(clientServiceImpl.updateClient(identification, ClientDTOMapper.toClient(clientDTO)).get());
    }   
    
    @DeleteMapping("/{identification}")
    public ResponseEntity<Client> deleteClient(@PathVariable String identification) {        
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(clientServiceImpl.deleteClientByIdentification(identification).get());    
    } 

}
