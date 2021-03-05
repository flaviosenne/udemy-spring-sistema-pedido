package com.ordering.system.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import com.ordering.system.domains.Requests;
import com.ordering.system.services.RequestsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value = "/request")
public class ResquestsResource {
    @Autowired
    RequestsService requestService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Requests> getRequestById(@PathVariable Integer id){
        return this.requestService.getRequestById(id);
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody Requests requests){
        Requests categorySaved = this.requestService.saveRequests(requests);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(categorySaved.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public ResponseEntity<List<Requests>> findRequestByClient(){
        return ResponseEntity.ok(this.requestService.findRequestByClient());
    }
}
