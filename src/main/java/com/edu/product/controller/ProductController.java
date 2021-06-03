package com.edu.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.product.model.dto.ProductDTO;
import com.edu.product.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping(produces = "application/json")
	public List<ProductDTO> findAllProducts(){
		log.info("findAllProducts");
		return productService.findAllProducts();
	}

}
