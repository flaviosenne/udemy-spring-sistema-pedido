package com.ordering.system.domains;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ordering.system.enums.ClientType;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode
@NoArgsConstructor
@Entity
public class Client implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    private String email;

    @Column(name = "cpf_or_cnpj")
    private String cpfOrCnpj;

    private Integer type;

    @JsonManagedReference
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Adress> adresses = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "phone")
    private Set<String> phones = new HashSet<>();

    @OneToMany(mappedBy = "client")
    private List<Request> requests = new ArrayList<>();


    public Client(Integer id, String name, String email, String cpfOrCnpj, ClientType type){
        super();
        this.id = id;
        this.name = name;
        this.email = email;
        this.cpfOrCnpj = cpfOrCnpj;
        this.type = type.getCode();
    }

    public void setId(Integer id){
        this.id = id;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public void setCpfOrCnpj(String cpfOrCnpj){
        this.cpfOrCnpj = cpfOrCnpj;
    }
    public void setType(ClientType type){
        this.type = type.getCode();
    }
    public void setAdress(List<Adress> adress){
        this.adresses = adress;
    }
    public void setPhone(Set<String> phone){
        this.phones = phone;
    } 
    public void setOrder(List<Request> requests){
        this.requests = requests;
    }   

    public String getName(){
        return this.name;
    }
    public String getEmail(){
        return this.email;
    }
    public String getCpfOrCnpj(){
        return this.cpfOrCnpj;
    }
    public Integer getId(){
        return this.id;
    }
    public ClientType getType(){
        return ClientType.toEnum(this.type);
    }
    public List<Adress> getAdresses(){
        return this.adresses;
    }
    public Set<String> getPhone(){
        return this.phones;
    }
    public List<Request> getRequests(){
        return this.requests;
    }

}
