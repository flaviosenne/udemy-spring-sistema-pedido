package com.ordering.system.enums;

import lombok.Getter;

@Getter
public enum PaymentStatus {
    
    PENDENT(1, "pendent"),
    FINALIZED(2, "finalized"),
    CANCELED(3, "canceled");

    private int code;
    private String description;

    private PaymentStatus(int code, String description){
        this.code = code;
        this.description = description;
    } 

    public static PaymentStatus toEnum(Integer code){
        if(code == null){
            return null;
        }

        for(PaymentStatus status: PaymentStatus.values()){
            if(code.equals(status.getCode())){
                return status;
            }
        }

        throw new IllegalArgumentException("Invalid id "+ code);
    }
}
