package com.ordering.system.domains;

import java.io.Serializable;

import javax.persistence.*;

import com.ordering.system.enums.PaymentStatus;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Setter
@Getter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Payment  implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private Integer id;

    private PaymentStatus status;


    @OneToOne
    @JoinColumn(name = "order_id")
    @MapsId
    private Request order;

   
}
