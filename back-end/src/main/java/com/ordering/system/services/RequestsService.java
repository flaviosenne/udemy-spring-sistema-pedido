package com.ordering.system.services;

import com.ordering.system.domains.Request;
import com.ordering.system.repositories.RequestsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RequestsService {
    @Autowired
    RequestsRepository requestsRepository;

    public ResponseEntity<Request> getRequestById(Integer id){
        Optional<Request> request = this.requestsRepository.findById(id);

        if(request.isPresent()){
            return ResponseEntity.status(200).body(request.get());
        }

        return ResponseEntity.status(404).body(null);
    }
}
