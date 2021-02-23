package com.ordering.system.dto;

import com.ordering.system.domains.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String name;

    public CategoryDTO(Category category){
        this.id = category.getId();
        this.name = category.getName();

    }

}
