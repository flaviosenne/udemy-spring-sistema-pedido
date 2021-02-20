package com.ordering.system.services;

import java.util.Optional;

import com.ordering.system.domains.Client;
import com.ordering.system.repositories.ClientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
    
    @Autowired
    ClientRepository clientRepository;

    public ResponseEntity<Client> getClientById(Integer id){
        Optional<Client> client = this.clientRepository.findById(id);

        if(client.isPresent()){
            return ResponseEntity.status(200).body(client.get());
        }

        return ResponseEntity.status(404).body(null);
    }
}
