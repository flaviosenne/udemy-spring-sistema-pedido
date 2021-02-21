package com.ordering.system.domains;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonManagedReference;



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
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;  

    @Column(name = "date_start")
    private Date dateStart;  

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "order")
    @JsonManagedReference
    private Payment payment;

    @ManyToOne
    @JoinColumn(name = "adress_id")
    private Adress adress;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;
}
