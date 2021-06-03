package com.edu.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edu.product.model.entity.Product;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long>{

}
