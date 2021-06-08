package com.edu.product.services.mapper;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.edu.product.model.dto.ProductUpdateDTO;
import com.edu.product.model.entity.Product;
import com.edu.product.service.mapper.IProductMapper;

@SpringBootTest
public class ProductMapperTest {
	
	@Autowired
	private IProductMapper productMapper;
	 
	@Test
	public void toDTONullTest() { 
		assertNull(productMapper.toDTO(null));
	}
	
	@Test
	public void toEntityNullTest() { 
		assertNull(productMapper.toEntity(null));
	}
	
	@Test
	public void mapEntityNullTest() { 
		assertNull(productMapper.mapEntity(null, null));
		assertNotNull(productMapper.mapEntity(new Product(), null));
		assertNotNull(productMapper.mapEntity(null, new ProductUpdateDTO()));
	}
	
}
