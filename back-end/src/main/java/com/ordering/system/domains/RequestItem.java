package com.ordering.system.domains;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@EqualsAndHashCode
@NoArgsConstructor
@Getter
@Setter
@Entity
public class RequestItem implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    private RequestItemPK id = new RequestItemPK();

    private Double off;
    private Integer quantity;
    private Double price;

    public RequestItem(Product product, Request request, Double off, Integer quantity, Double price){
        super();
        this.id.setProduct(product);
        this.id.setRequest(request);
        this.off = off;
        this.quantity = quantity;
        this.price = price;
    }

    public Product getProduct(){
        return this.id.getProduct();
    }
    public Request getRequest(){
        return this.id.getRequest();
    }
}
