package com.ordering.system.resources;

import com.ordering.system.domains.Client;
import com.ordering.system.services.ClientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/clients")
public class ClientResource {
    @Autowired
    ClientService clientService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable Integer id){
        return this.clientService.getClientById(id);
    }
    
}
