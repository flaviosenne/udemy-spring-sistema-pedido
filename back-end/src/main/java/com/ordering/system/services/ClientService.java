package com.ordering.system.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.ordering.system.domains.Client;
import com.ordering.system.dto.ClientDTO;
import com.ordering.system.exceptions.DataIntegrityException;
import com.ordering.system.repositories.ClientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepository;

    public ResponseEntity<Client> getClientById(Integer id) {
        Optional<Client> client = this.clientRepository.findById(id);

        if (client.isPresent()) {
            return ResponseEntity.status(200).body(client.get());
        }

        return ResponseEntity.status(404).body(null);
    }

    public ResponseEntity<List<ClientDTO>> findAllClient(){
        List<Client> client = this.clientRepository.findAll();

        List<ClientDTO> listDTO = client.stream()
                .map(obj ->  new ClientDTO(obj)).collect(Collectors.toList());

        return ResponseEntity.status(200).body(listDTO);
    }

    public Client updateClient(Client client) {
        Client newClient = this.getClientById(client.getId()).getBody();
        updateDate(newClient, client);
        return this.clientRepository.save(newClient);
    }

    public void deleteClientById(Integer id) {
        this.getClientById(id);
        try {
            this.clientRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {

            throw new DataIntegrityException("Can't remove Client if exist connect requests ");
        }

    }

    public Page<Client> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        if (direction.equals("asc")) {
            PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.ASC, orderBy);
            return this.clientRepository.findAll(pageRequest);
        } else {
            PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.DESC, orderBy);
            return this.clientRepository.findAll(pageRequest);
        }

    }

    public Client toClient(ClientDTO client) {
        return new Client(client.getId(), client.getName(), client.getEmail(), null, null);
    }

    private void updateDate(Client newClient, Client client){
        newClient.setName(client.getName());
        newClient.setEmail(client.getEmail());
    }
}
