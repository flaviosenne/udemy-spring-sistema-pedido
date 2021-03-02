package com.ordering.system.enums;

import lombok.Getter;

@Getter
public enum ClientPerfil {
    
    ADMIN(1, "ROLE_ADMIN"),
    CLIENT(2, "ROLE_CLIENT");

    private int code;
    private String description;

    private ClientPerfil(int code, String description){
        this.code = code;
        this.description = description;
    }

    public static ClientPerfil toEnum(Integer cod){
        if(cod == null)
            return null;

        for(ClientPerfil x: ClientPerfil.values()){
            if(cod.equals(x.getCode())){
                return x;
            }
        }

        throw new IllegalArgumentException("Invalid id "+ cod);
    } 
}
