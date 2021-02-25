package com.ordering.system.domains;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@NoArgsConstructor
@Setter
@Getter
@Entity
public class Requests implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @JsonFormat(pattern="dd/MM/yyyy HH:mm")
    @Column(name = "date_start")
    private Date dateStart;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "request")
    private Payment payment;


    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "adress_id", referencedColumnName = "id")
    private Adress deliveryAdress;

    @OneToMany(mappedBy = "id.requests")
    private Set<RequestItem> itens = new HashSet<>();

    public Requests(Integer id, Date dateStart, Client client, Adress deliveryAdress){
        super();
        this.id= id;
        this.dateStart = dateStart;
        this.client = client;
        this.deliveryAdress = deliveryAdress;
    }

    public double getTotalValue(){
        double sum = 0;
        for(RequestItem item: this.itens){
            sum += item.getSubTotal();
        }

        return sum;

    }
}
