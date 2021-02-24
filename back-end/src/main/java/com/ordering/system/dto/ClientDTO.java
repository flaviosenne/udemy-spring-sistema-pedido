package com.ordering.system.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.ordering.system.domains.Client;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ClientDTO {
    
    private Integer id;
    
    @NotEmpty(message = "name can't is null")
    @Size(min = 5, max = 120, message = "size between 5 and 120")
    private String name;
    @NotEmpty(message = "name can't is null")
    @Email(message = "invalid email")
    private String email; 

    public ClientDTO(Client client){
        this.id = client.getId();
        this.name = client.getName();
        this.email = client.getEmail();
    }
}
