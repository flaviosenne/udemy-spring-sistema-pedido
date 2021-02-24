package com.ordering.system.repositories;

import java.util.List;

import com.ordering.system.domains.Category;
import com.ordering.system.domains.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("SELECT DISTINCT obj FROM Product obj INNER JOIN obj.categories cat WHERE obj.name LIKE%:name% AND cat IN:categories")

    Page<Product> search(
        @Param("nome") String name,
        @Param("categorias") List<Category> categories,
        PageRequest pageRequest);
}
