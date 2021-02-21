package com.ordering.system.resources;

import com.ordering.system.domains.Request;
import com.ordering.system.services.RequestsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/request")
public class ResquestsResource {
    @Autowired
    RequestsService requestService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Request> getRequestById(@PathVariable Integer id){
        return this.requestService.getRequestById(id);
    }
}
