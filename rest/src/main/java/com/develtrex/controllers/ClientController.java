package com.develtrex.controllers;

import com.develtrex.entities.Client;
import com.develtrex.services.ClientService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("api/clients")
public class ClientController {
    // Fields
    @Autowired
    private ClientService clientService;

    @GetMapping()
    public List<Client> getClients() {
        log.info("Request to get all clients");
        return clientService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getClient(@PathVariable String id) {
        var response = clientService.getById(id);

        if (response == null) {
            log.warn("Trying to get a non existent client");
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(response);
    }

    @PostMapping()
    public ResponseEntity<Client> createClient(@RequestBody Client client) {
        if (client.getId() != null) {
            log.warn("Trying to create a new client with con id. The id is auto-generated");
            return ResponseEntity.badRequest().build();
        }

        var response = clientService.create(client);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable String id, @RequestBody Client client){
        client.setId(id);
        var response = clientService.update(client);

        if (response == null){
            log.warn("Trying to update a non existent client");
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Client> deleteClient(@PathVariable String id){
        return null;
    }

    @DeleteMapping()
    public ResponseEntity<Client> deleteClients(){
        return null;
    }
}
