package com.ordering.system.domains;

import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.ordering.system.enums.PaymentStatus;


import lombok.NoArgsConstructor;


@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "@type")
public abstract class Payment  implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private Integer id;

    private Integer status;


    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "requests_id", referencedColumnName = "id")
    @MapsId
    private Requests request;

    public Payment(Integer id, PaymentStatus status, Requests request) {
        this.id = id;
        this.status = (status == null)? null: status.getCode();
        this.request = request;
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

    public Requests getRequest() {
        return request;
    }

    public void setRequest(Requests request) {
        this.request = request;
    }
}
