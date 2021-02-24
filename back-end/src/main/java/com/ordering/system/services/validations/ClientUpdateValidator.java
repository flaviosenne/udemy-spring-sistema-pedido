package com.ordering.system.services.validations;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.ordering.system.domains.Client;
import com.ordering.system.dto.ClientDTO;
import com.ordering.system.repositories.ClientRepository;
import com.ordering.system.resources.exceptions.FieldMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;


public class ClientUpdateValidator implements ConstraintValidator<ClientUpdate, ClientDTO>{

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private HttpServletRequest request;

    @Override
    public void initialize(ClientUpdate ann){

    }

    @Override
    public boolean isValid(ClientDTO client, ConstraintValidatorContext context){
        
        @SuppressWarnings("unchecked")
        Map<String, String> map = (Map<String, String>) 
        this.request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        
        Integer uriId = Integer.parseInt(map.get("id"));

        List<FieldMessage> list = new ArrayList<>();

        
        Client existEmail = this.clientRepository.findByEmail(client.getEmail());
        if(existEmail != null && !existEmail.getId().equals(uriId)){
            list.add(new FieldMessage("email", "email already exist"));

        }

        for(FieldMessage e: list){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
            e.getMessage()).addPropertyNode(
            e.getFieldName()).addConstraintViolation();
        }

        return list.isEmpty();
    }
}
