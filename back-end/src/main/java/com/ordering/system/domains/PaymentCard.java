package com.ordering.system.domains;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.ordering.system.enums.PaymentStatus;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
@Entity
@JsonTypeName("paymentWithCard")
public class PaymentCard extends Payment{
    

    private static final long serialVersionUID = 1L;
    
    private Integer numberPlots;

    public PaymentCard(Integer id, PaymentStatus status, Requests requests, Integer numberPlots){
        super(id, status, requests);
        this.numberPlots = numberPlots;
    }
}
