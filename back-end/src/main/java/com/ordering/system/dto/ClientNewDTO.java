package com.ordering.system.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class ClientNewDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private String name;
    private String email;
    private String cpfOrCnpj;
    private Integer type;

    private String place;
    private String number;
    private String complement;
    private String district;
    private String postalCode;

    private String phone1;
    private String phone2;
    private String phone3;

    private Integer cityId;
}
