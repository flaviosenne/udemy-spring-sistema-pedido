package com.ordering.system.domains;

import java.util.Date;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.ordering.system.enums.PaymentStatus;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Setter
@Getter
@Entity

@JsonTypeName("paymentWithTicket")
public class PaymentTicket extends Payment {
  
    private static final long serialVersionUID = 1L;

    @JsonFormat(pattern="dd/MM/yyyy")
    private Date dueDate;
    @JsonFormat(pattern="dd/MM/yyyy")
    private Date paymentDate;

    public PaymentTicket(Integer id, PaymentStatus status, Requests requests, Date dueDate, Date paymentDate ){
        super(id, status, requests);
        this.dueDate = dueDate;
        this.paymentDate = paymentDate;
    }
}
