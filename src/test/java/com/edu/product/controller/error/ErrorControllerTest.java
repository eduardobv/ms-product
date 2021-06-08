package com.edu.product.controller.error;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.edu.product.service.ProductService;

@SpringBootTest
@AutoConfigureMockMvc
public class ErrorControllerTest {
	
	private static final String URL_PRODUCTS_ID= "/products/{products}";
	
	@MockBean
	private ProductService productServ;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void errorControllerNumberFormatExceptionTest() throws Exception {
		  
		given(productServ.findProductById(Long.valueOf(1))).willThrow(MethodArgumentTypeMismatchException.class);
		 
		MvcResult mvcResult = mockMvc.perform(get(URL_PRODUCTS_ID,"1")).andReturn(); 
		assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value()); 
	}
	
	@Test
	public void errorControllerJsonParseExceptionTest() throws Exception {
		  
		given(productServ.findProductById(Long.valueOf(1))).willThrow(HttpMessageNotReadableException.class);
		 
		MvcResult mvcResult = mockMvc.perform(get(URL_PRODUCTS_ID,"1")).andReturn(); 
		assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value()); 
	}
}
