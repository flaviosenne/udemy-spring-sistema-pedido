package com.ordering.system.domains;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Setter
@Entity
public class RequestItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonIgnore
    @EmbeddedId
    private RequestItemPK id = new RequestItemPK();

    private Double off;
    private Integer quantity;
    private Double price;

    public RequestItem(Product product, Requests requests, Double off, Integer quantity, Double price){
        super();
        this.id.setProduct(product);
        this.id.setRequests(requests);
        this.off = off;
        this.quantity = quantity;
        this.price = price;
    }

    public Product getProduct(){
        return this.id.getProduct();
    }

    public void setProduct(Product product){
        this.id.setProduct(product);
    }

    public double getSubTotal(){
        return (this.price - this.off) * this.quantity;
    }
    @JsonIgnore
    public Requests getRequest(){
        return this.id.getRequests();
    }

    public void setRequest(Requests requests){
        this.id.setRequests(requests);
    }

    @Override
    public String toString(){
        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        StringBuilder builder = new StringBuilder();
        builder.append(this.getProduct().getName());
        builder.append(", quantity = ");
        builder.append(this.getQuantity());
        builder.append(", unit price = ");
        builder.append(nf.format(this.getPrice()));
        builder.append(", subTotal: ");
        builder.append(nf.format(getSubTotal()));
        builder.append("\n");
        return builder.toString();
    }
}
