package com.ordering.system.services.validations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.ordering.system.domains.Client;
import com.ordering.system.dto.ClientNewDTO;
import com.ordering.system.enums.ClientType;
import com.ordering.system.repositories.ClientRepository;
import com.ordering.system.resources.exceptions.FieldMessage;
import com.ordering.system.services.validations.utils.BR;

import org.springframework.beans.factory.annotation.Autowired;


public class ClientInsertValidator implements ConstraintValidator<ClientInsert, ClientNewDTO>{

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public void initialize(ClientInsert ann){

    }

    @Override
    public boolean isValid(ClientNewDTO client, ConstraintValidatorContext context){
        List<FieldMessage> list = new ArrayList<>();

        if(client.getType().equals(ClientType.PHYSICAL.getCode()) && !BR.isValidCpf(client.getCpfOrCnpj())){
            list.add(new FieldMessage("cpfOrCnpj", "cpf  isn't valid"));
        }

        if(client.getType().equals(ClientType.LEGAL.getCode()) && !BR.isValidCnpj(client.getCpfOrCnpj())){
            list.add(new FieldMessage("cpfOrCnpj", "cnpj  isn't valid"));
        }
        
        Optional<Client> existEmail = this.clientRepository.findByEmail(client.getEmail());
        if(existEmail.isPresent()){
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
