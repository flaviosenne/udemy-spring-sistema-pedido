package com.ordering.system.domains;

import java.util.Date;

import javax.persistence.Entity;

import com.ordering.system.enums.PaymentStatus;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Setter
@Getter
@Entity
public class PaymentTicket extends Payment {
  
    private static final long serialVersionUID = 1L;
    
    private Date dueDate;
    private Date paymentDate;

    public PaymentTicket(Integer id, PaymentStatus status, Order order, Date dueDate, Date paymentDate ){
        super(id, status, order);
        this.dueDate = dueDate;
        this.paymentDate = paymentDate;
    }
}
