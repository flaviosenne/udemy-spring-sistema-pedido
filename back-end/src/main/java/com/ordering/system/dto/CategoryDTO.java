package com.ordering.system.dto;

import com.ordering.system.domains.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    @NotEmpty(message = "can't is null")
    @Size(min = 5, max = 80, message = "size between 5 and 80")
    private String name;

    public CategoryDTO(Category category){
        this.id = category.getId();
        this.name = category.getName();

    }

}
