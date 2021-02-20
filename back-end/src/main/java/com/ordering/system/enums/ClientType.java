package com.ordering.system.enums;

import lombok.Getter;

@Getter
public enum ClientType {
    
    PHYSICAL(1, "Pysical Person"),
    LEGAL(2, "Legal Person");

    private int code;
    private String description;

    private ClientType(int code, String description){
        this.code = code;
        this.description = description;
    }

    public static ClientType toEnum(Integer cod){
        if(cod == null)
            return null;

        for(ClientType x: ClientType.values()){
            if(cod.equals(x.getCode())){
                return x;
            }
        }

        throw new IllegalArgumentException("Invalid id "+ cod);
    } 
}
