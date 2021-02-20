package com.ordering.system;

import java.util.Arrays;

import com.ordering.system.domains.Category;
import com.ordering.system.domains.Product;
import com.ordering.system.repositories.CategoryRepository;
import com.ordering.system.repositories.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SystemApplication implements CommandLineRunner{
	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ProductRepository productRepository;
	public static void main(String[] args) {
		SpringApplication.run(SystemApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	Category cat1 = new Category();
	cat1.setId(null);
	cat1.setName("Informática");
	
	Category cat2 = new Category();
	cat2.setId(null);
	cat2.setName("Escritório");


	Product p1 = new Product();
	p1.setId(null);
	p1.setName("Computador");
	p1.setPrice(2000.00);
	
	Product p2 = new Product();
	p2.setId(null);
	p2.setName("Impressora");
	p2.setPrice(800.00);
	
	Product p3 = new Product();
	p3.setId(null);
	p3.setName("mouse");
	p3.setPrice(80.00);

	
	cat1.getProducts().addAll(Arrays.asList(p1, p2, p3));
	cat2.getProducts().addAll(Arrays.asList(p2));
	
	p1.getCategories().addAll(Arrays.asList(cat1));
	p2.getCategories().addAll(Arrays.asList(cat1, cat2));
	p3.getCategories().addAll(Arrays.asList(cat1));
	
	
	this.categoryRepository.saveAll(Arrays.asList(cat1, cat2));
	this.productRepository.saveAll(Arrays.asList(p1,p2, p3));
	}

}
