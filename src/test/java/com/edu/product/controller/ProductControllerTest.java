package com.edu.product.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.edu.product.model.ProductCategory;
import com.edu.product.model.dto.ProductDTO;
import com.edu.product.model.dto.ProductUpdateDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {
	
	private static final String URL_PRODUCTS= "/products";
	private static final String URL_PRODUCTS_ID= "/products/{products}";
	
	@Autowired
	private MockMvc mockMvc;
		
	@Test
	public void findAllProductsTest() throws Exception {
		
		MvcResult mvcResult = mockMvc.perform(get(URL_PRODUCTS)).andReturn(); 
		assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value()); 
	}	
	
	@Test
	public void findProductByIdTest() throws Exception {
		
		MvcResult mvcResult = mockMvc.perform(get(URL_PRODUCTS_ID,"1")).andReturn(); 
		assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value()); 
	}
	
	@Test
	public void findProductByIdNumberFormatExceptionTest() throws Exception {
		
		MvcResult mvcResult = mockMvc.perform(get(URL_PRODUCTS_ID,"11111111111111111111111111111111111")).andReturn(); 
		assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value()); 
	}
	
	@Test
	public void findProductByIdNotFoundTest() throws Exception {
		
		MvcResult mvcResult = mockMvc.perform(get(URL_PRODUCTS_ID,"222")).andReturn(); 
		assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value()); 
	}
	
	@Test
	public void createProductTest() throws Exception {
		
		ProductDTO productDTO = ProductDTO.builder() 
				.name("Iphone 4")
				.description("3G Mobile")
				.category(ProductCategory.ELECTRONIC)
				.price(BigDecimal.valueOf(750))
				.minimumStock(Integer.valueOf(1000))
				.build();
		
		ProductDTO.builder().toString();
		
		MvcResult mvcResult = mockMvc.perform(post(URL_PRODUCTS).content(asJsonString(productDTO)).contentType(MediaType.APPLICATION_JSON)).andReturn(); 
		assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.CREATED.value()); 
	}
	
	@Test
	public void createProductBadRequestTest() throws Exception {
		
		ProductDTO productDTO = ProductDTO.builder()
				.id(Long.valueOf(1))
				.name("Samsung Galaxy S21")
				.description("Best mobile")
				.category(ProductCategory.ELECTRONIC)
				.price(BigDecimal.valueOf(750))
				.minimumStock(Integer.valueOf(1000))
				.build();
		
		MvcResult mvcResult = mockMvc.perform(post(URL_PRODUCTS).content(asJsonString(productDTO)).contentType(MediaType.APPLICATION_JSON)).andReturn(); 
		assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value()); 
	}
	
	@Test
	public void createProductBadRequestInvalidFormatExceptionTest() throws Exception {
		
		String ProductJson = "{\"id\":123,\"name\":\"OnePlus 9\",\"description\":\"ONEPLUS 9 5G Smartphone\",\"category\":\"ELECTRONICS\",\"price\":699,\"minimumStock\":1000,\"created\":\"2021-06-07T20:05:21.764Z\",\"modified\":\"2021-06-07T20:05:21.764Z\"}";
		
		MvcResult mvcResult = mockMvc.perform(post(URL_PRODUCTS).content(ProductJson).contentType(MediaType.APPLICATION_JSON)).andReturn(); 
		assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value()); 
	}
	
	@Test
	public void createProductBadRequestJsonParseExceptionTest() throws Exception {
		
		String ProductJson = "{\"id\":123,\"name\":\"OnePlus 9\",\"description\"\"ONEPLUS 9 5G Smartphone\",\"category\":\"ELECTRONIC\",\"price\":699,\"minimumStock\":1000,\"created\":\"2021-06-07T20:05:21.764Z\",\"modified\":\"2021-06-07T20:05:21.764Z\"}";
		
		MvcResult mvcResult = mockMvc.perform(post(URL_PRODUCTS).content(ProductJson).contentType(MediaType.APPLICATION_JSON)).andReturn(); 
		assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value()); 
	} 
	
	@Test
	public void createProductBadRequestMethodArgumentNotValidExceptionTest() throws Exception {
		
		String ProductJson = "{\"id\":123,\"name\":\"\",\"description\":\"ONEPLUS 9 5G Smartphone\",\"category\":\"ELECTRONIC\",\"price\":699,\"minimumStock\":1000,\"created\":\"2021-06-07T20:05:21.764Z\",\"modified\":\"2021-06-07T20:05:21.764Z\"}";
		
		MvcResult mvcResult = mockMvc.perform(post(URL_PRODUCTS).content(ProductJson).contentType(MediaType.APPLICATION_JSON)).andReturn(); 
		assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value()); 
	}
	
	@Test
	public void updateProductTest() throws Exception {
		
		ProductUpdateDTO productUpdateDTO = ProductUpdateDTO.builder() 
				.category(ProductCategory.ELECTRONIC)
				.price(BigDecimal.valueOf(750))
				.minimumStock(Integer.valueOf(1000))
				.build();
		ProductUpdateDTO.builder().toString();
		
		MvcResult mvcResult = mockMvc.perform(put(URL_PRODUCTS_ID,"1").content(asJsonString(productUpdateDTO)).contentType(MediaType.APPLICATION_JSON)).andReturn(); 
		assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value()); 
	}
	
	@Test
	public void updateProductNotFoundTest() throws Exception {
		
		ProductUpdateDTO productUpdateDTO = ProductUpdateDTO.builder() 
				.category(ProductCategory.ELECTRONIC)
				.price(BigDecimal.valueOf(750))
				.minimumStock(Integer.valueOf(1000))
				.build();
		
		MvcResult mvcResult = mockMvc.perform(put(URL_PRODUCTS_ID,"22").content(asJsonString(productUpdateDTO)).contentType(MediaType.APPLICATION_JSON)).andReturn(); 
		assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value()); 
	}
	
	
	@Test
	public void deleteProductTest() throws Exception {
		
		MvcResult mvcResult = mockMvc.perform(delete(URL_PRODUCTS_ID,"5")).andReturn(); 
		assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.NO_CONTENT.value()); 
	}
	
	@Test
	public void deleteProductNotFoundTest() throws Exception {
		
		MvcResult mvcResult = mockMvc.perform(delete(URL_PRODUCTS_ID,"22")).andReturn(); 
		assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value()); 
	} 
	
	public static String asJsonString(final Object obj) throws Exception{
		return new ObjectMapper().writeValueAsString(obj); 
	}
	
	
}
