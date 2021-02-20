package com.ordering.system.domains;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

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
public class State implements Serializable{

    private static final long serialVersionUID = 1L;
    
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    private String name;

    @OneToMany(mappedBy = "state")
    private List<City> cities = new ArrayList<>();
}
