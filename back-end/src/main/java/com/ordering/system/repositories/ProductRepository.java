package com.ordering.system.repositories;

import java.util.List;

import com.ordering.system.domains.Category;
import com.ordering.system.domains.Product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    
    @Transactional(readOnly = true)
    @Query(value = "select obj from Product obj inner join obj.categories cat where obj.name like '% :name %' and cat in :categories " )
    Page<Product> search(
        @Param("name") String name,
        @Param("categories") List<Category> categories,
        Pageable pageRequest);
}
