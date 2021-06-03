package com.edu.product.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.product.model.dto.ProductDTO;
import com.edu.product.repository.IProductRepository;
import com.edu.product.service.mapper.IProductMapper;

@Service
public class ProductService {
	
	@Autowired
	private IProductRepository productRepository;
	
	@Autowired
	private IProductMapper productMapper;
	
	public List<ProductDTO> findAllProducts(){
		
		return productRepository.findAll().stream().map( product -> productMapper.toDTO(product)).collect(Collectors.toList());
	}
}
