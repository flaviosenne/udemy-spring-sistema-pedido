package com.ordering.system.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.ordering.system.services.validations.ClientInsert;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
@ClientInsert
public class ClientNewDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotEmpty(message = "name can't is null")
    @Size(min = 5, max = 80, message = "size between 5 and 80")
    private String name;
    
    @NotEmpty(message = "email can't is null")
    @Email(message = "invalid email")
    private String email;
    
    @NotEmpty(message = "cpfOrCnpj can't is null")
    private String cpfOrCnpj;
    private Integer type;
    
    @NotEmpty(message = "place can't is null")
    private String place;
    
    @NotEmpty(message = "number can't is null")
    private String number;
    private String complement;
   
    
    private String district;
    
    @NotEmpty(message = "postalCode can't is null")
    private String postalCode;
    
    @NotEmpty(message = "phone1 can't is null")
    private String phone1;
    
    private String phone2;
    private String phone3;

    private Integer cityId;
}
