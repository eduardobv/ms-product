package com.edu.product.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.product.model.dto.ProductDTO;
import com.edu.product.model.dto.ProductUpdateDTO;
import com.edu.product.model.entity.Product;
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
	
	public Optional<ProductDTO> findProductById(Long id){
		Optional<Product> product  = productRepository.findById(id); 
		if (product.isPresent()){
			return Optional.of(productMapper.toDTO(product.get()));
		}else {
			return Optional.empty();
		} 
	}
	
	public Optional<ProductDTO> createProduct(ProductDTO productDTO) { 
		
		List<Product> listProducts =  productRepository.findByNameAndDescription(productDTO.getName(), productDTO.getDescription());
		
		if(listProducts.isEmpty()) {
			return Optional.of(productMapper.toDTO(productRepository.save(productMapper.toEntity(productDTO))));
		}else {
			return Optional.empty();
		} 
	}
	
	public Optional<ProductDTO> updateProduct(Long id, ProductUpdateDTO productDTO){
		
		Optional<Product> product  = productRepository.findById(id);
		
		if (product.isPresent()){
			return Optional.of(productMapper.toDTO(productRepository.save(productMapper.mapEntity(product.get(), productDTO))));
		}else {
			return Optional.empty();
		}
	}
}
