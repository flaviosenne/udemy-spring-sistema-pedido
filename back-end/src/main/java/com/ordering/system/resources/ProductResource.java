package com.ordering.system.resources;


import java.util.List;

import com.ordering.system.domains.Product;
import com.ordering.system.dto.ProductDTO;
import com.ordering.system.resources.utils.URL;
import com.ordering.system.services.ProductService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/products")
public class ProductResource {
    @Autowired
    ProductService productService;

    @ApiOperation(value = "Buscar produto por id")
    @GetMapping(value = "/{id}")
    public ResponseEntity<Product> getRequestById(@PathVariable Integer id){
        return ResponseEntity.ok(this.productService.findProducttById(id));
    }

    @ApiOperation(value = "Buscar lista de produstos paginado")
    @GetMapping
    public ResponseEntity<Page<ProductDTO>> findPage(
            @RequestParam(value = "name", defaultValue ="") String name,
            @RequestParam(value = "categories", defaultValue ="") String categories,
            @RequestParam(value = "page", defaultValue ="0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue ="24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue ="name") String orderBy){
        
        String nameDecode = URL.decodeParam(name);
        List<Integer> ids = URL.decodeIntList(categories);
        System.out.println(nameDecode);
        System.out.println(ids);
        Page<Product> list = this.productService.search(nameDecode, ids, page, linesPerPage, orderBy);
        System.out.println("aqui" +list);
        Page<ProductDTO> listDTO = list.map(obj -> new ProductDTO(obj));
        return ResponseEntity.ok(listDTO);
    }
    
}
