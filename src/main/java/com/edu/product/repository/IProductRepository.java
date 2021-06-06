package com.edu.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edu.product.model.entity.Product;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long>{
	
	List<Product> findByNameAndDescription(String name, String description);
	
	List<Product> findByName(String name);
	
}
