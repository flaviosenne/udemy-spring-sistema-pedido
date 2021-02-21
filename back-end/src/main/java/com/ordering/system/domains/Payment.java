package com.ordering.system.domains;

import java.io.Serializable;

import javax.persistence.*;


import com.ordering.system.enums.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Payment  implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private Integer id;

    private Integer status;


    @OneToOne
    @JoinColumn(name = "order_id")
    @MapsId
    private Request order;

    public Payment(Integer id, PaymentStatus status, Request order) {
        this.id = id;
        this.status = status.getCode();
        this.order = order;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PaymentStatus getStatus() {
        return PaymentStatus.toEnum(this.status);
    }

    public void setStatus(PaymentStatus status) {
        this.status = status.getCode();
    }

    public Request getOrder() {
        return order;
    }

    public void setOrder(Request order) {
        this.order = order;
    }
}
