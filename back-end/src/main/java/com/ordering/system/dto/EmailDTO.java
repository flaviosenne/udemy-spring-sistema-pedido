package com.ordering.system.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmailDTO implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private String email;
}
