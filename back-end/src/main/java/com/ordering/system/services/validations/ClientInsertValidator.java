package com.ordering.system.services.validations;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.ordering.system.dto.ClientNewDTO;
import com.ordering.system.enums.ClientType;
import com.ordering.system.resources.exceptions.FieldMessage;
import com.ordering.system.services.validations.utils.BR;


public class ClientInsertValidator implements ConstraintValidator<ClientInsert, ClientNewDTO>{

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

        for(FieldMessage e: list){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
            e.getMessage()).addPropertyNode(
            e.getFieldName()).addConstraintViolation();
        }

        return list.isEmpty();
    }
}
