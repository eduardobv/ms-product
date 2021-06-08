package com.edu.product.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.edu.product.model.ProductCategory;
import com.edu.product.model.dto.ProductDTO;
import com.edu.product.model.dto.ProductUpdateDTO;

@SpringBootTest
public class ProductServiceTest {

	@Autowired
	private ProductService productService;

	@Test
	public void findAllProductsTest() {

		List<ProductDTO> listProduct = productService.findAllProducts();
		assertNotNull(listProduct);

	}

	@Test
	public void findProductById() {
		
		ProductDTO productDTO = ProductDTO.builder()
								.id(Long.valueOf(1))
								.name("Samsung Galaxy S21")
								.description("Best mobile")
								.category(ProductCategory.ELECTRONIC)
								.price(BigDecimal.valueOf(750))
								.minimumStock(Integer.valueOf(1000))
								.build();
		
		Optional<ProductDTO> optionalProduct = productService.findProductById(Long.valueOf(1));
		assertTrue(optionalProduct.isPresent());
		
		assertTrue(productDTO.getName().equalsIgnoreCase(optionalProduct.get().getName()));
		
		optionalProduct = productService.findProductById(Long.valueOf(222));
		assertFalse(optionalProduct.isPresent());
		 
	}
	
	@Test
	public void createProduct() {
		
		ProductDTO productDTO = ProductDTO.builder()
								.name("Iphone 3")
								.description("Iphone 3 Best mobile")
								.category(ProductCategory.ELECTRONIC)
								.price(BigDecimal.valueOf(750))
								.minimumStock(Integer.valueOf(1000))
								.build();
		
		Optional<ProductDTO> optionalProduct = productService.createProduct(productDTO);
		assertTrue(optionalProduct.isPresent());
		
		optionalProduct = productService.createProduct(productDTO);
		assertFalse(optionalProduct.isPresent()); 
		 
	}
	
	@Test
	public void updateProduct() {
		
		ProductUpdateDTO productUpdateDTO = ProductUpdateDTO.builder()
												.category(ProductCategory.ELECTRONIC)
												.price(BigDecimal.valueOf(750))
												.minimumStock(Integer.valueOf(1000))
												.build();
		
		Optional<ProductDTO> optionalProduct = productService.updateProduct(Long.valueOf(1), productUpdateDTO);
		assertTrue(optionalProduct.isPresent());
		
		optionalProduct = productService.updateProduct(Long.valueOf(78), productUpdateDTO);
		assertFalse(optionalProduct.isPresent()); 
		 
	}
	
	@Test
	public void deleteProduct() {
		  
		
		productService.deleteProduct(Long.valueOf(2)); 
		
		Optional<ProductDTO> optionalProduct = productService.findProductById(Long.valueOf(2));
		assertFalse(optionalProduct.isPresent());
		 
	}
	
}
