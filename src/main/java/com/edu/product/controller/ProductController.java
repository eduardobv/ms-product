package com.edu.product.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.edu.product.exception.BadRequestProductException;
import com.edu.product.exception.NotFoundProductException;
import com.edu.product.model.dto.ApiErrorResponseDTO;
import com.edu.product.model.dto.ProductDTO;
import com.edu.product.model.dto.ProductUpdateDTO;
import com.edu.product.service.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@Tag(name = "products", description = "Managment of Products")
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@Operation(summary = "list of all products")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Successful Operation", 
					 content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = ProductDTO.class)))}),
		@ApiResponse(responseCode = "401", description = "Not Authorized",
					 content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ApiErrorResponseDTO.class))}),
		@ApiResponse(responseCode = "500", description = "Internal Server Error",
		 			 content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ApiErrorResponseDTO.class))})
	})
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ProductDTO> findAllProducts(){
		log.info("findAllProducts");
		return productService.findAllProducts();
	}
	
	@Operation(summary = "get product by Id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successful Operation", 
						 content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProductDTO.class))}),
			@ApiResponse(responseCode = "400", description = "Bad Request",
						 content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ApiErrorResponseDTO.class))}),
			@ApiResponse(responseCode = "401", description = "Not Authorized",
			 			 content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ApiErrorResponseDTO.class))}),
			@ApiResponse(responseCode = "404", description = "Not Found",
			 			 content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ApiErrorResponseDTO.class))}),
			@ApiResponse(responseCode = "500", description = "Internal Server Error",
			 			 content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ApiErrorResponseDTO.class))})
	})
	@GetMapping(value = "/{product}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ProductDTO findProductById(@Parameter(description = "id of the product", required = true, name = "product", in = ParameterIn.PATH, schema = @Schema(implementation = Long.class))  
									  @PathVariable(value = "product") Long id) {
		log.info("findProductById");
		Optional<ProductDTO> optionalProductDTO = productService.findProductById(id);
		if(!optionalProductDTO.isPresent()) {
			throw new NotFoundProductException(String.valueOf(id)); 
		}  
		return optionalProductDTO.get();
	}
	
	@Operation(summary = "create a new product")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Successful Operation", 
						 content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProductDTO.class))}),
			@ApiResponse(responseCode = "400", description = "Bad Request",
						 content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ApiErrorResponseDTO.class))}),
			@ApiResponse(responseCode = "401", description = "Not Authorized",
			 			 content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ApiErrorResponseDTO.class))}),
			@ApiResponse(responseCode = "500", description = "Internal Server Error",
			 			 content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ApiErrorResponseDTO.class))})
	})
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public ProductDTO createProduct(@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Product object to be created",
									required = true, content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
									schema = @Schema(implementation = ProductDTO.class))})
									@RequestBody(required = true) @Valid ProductDTO productDTO) {
		log.info("createProduct");
		Optional<ProductDTO> optionalProductDTO =  productService.createProduct(productDTO);
		if(!optionalProductDTO.isPresent()) {
			throw new BadRequestProductException(productDTO.getName()); 
		}  
		return optionalProductDTO.get(); 
	}
	
	@Operation(summary = "update product")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successful Operation", 
						 content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProductDTO.class))}),
			@ApiResponse(responseCode = "400", description = "Bad Request",
						 content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ApiErrorResponseDTO.class))}),
			@ApiResponse(responseCode = "401", description = "Not Authorized",
			 			 content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ApiErrorResponseDTO.class))}),
			@ApiResponse(responseCode = "404", description = "Not Found",
			 			 content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ApiErrorResponseDTO.class))}),
			@ApiResponse(responseCode = "500", description = "Internal Server Error",
			 			 content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ApiErrorResponseDTO.class))})
	})
	@PutMapping(value = "/{product}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ProductDTO updateProduct(@Parameter(description = "id of the product to be updated", required = true, name = "product", in = ParameterIn.PATH, schema = @Schema(implementation = Long.class)) 
									@PathVariable(value = "product") Long id,
									@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Product object to be updated",
									required = true, content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
									schema = @Schema(implementation = ProductUpdateDTO.class))})
									@RequestBody(required = true) @Valid ProductUpdateDTO productDTO) {
		log.info("updateProduct");
		Optional<ProductDTO> optionalProductDTO =  productService.updateProduct(id, productDTO);
		if(!optionalProductDTO.isPresent()) {
			throw new NotFoundProductException(String.valueOf(id)); 
		}  
		return optionalProductDTO.get();
	}
	
	@Operation(summary = "delete product")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Successful Operation"),
			@ApiResponse(responseCode = "400", description = "Bad Request",
						 content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ApiErrorResponseDTO.class))}),
			@ApiResponse(responseCode = "401", description = "Not Authorized",
			 			 content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ApiErrorResponseDTO.class))}),
			@ApiResponse(responseCode = "404", description = "Not Found",
			 			 content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ApiErrorResponseDTO.class))}),
			@ApiResponse(responseCode = "500", description = "Internal Server Error",
			 			 content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ApiErrorResponseDTO.class))})
	})
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping(value = "/{product}", produces = MediaType.APPLICATION_JSON_VALUE)
	public void deleteProduct(@Parameter(description = "id of the product to be deleted", required = true, name = "product", in = ParameterIn.PATH, schema = @Schema(implementation = Long.class)) 
							  @PathVariable(value = "product") Long id) {
		log.info("deleteProduct");
		Optional<ProductDTO> optionalProductDTO = productService.findProductById(id);
		if(!optionalProductDTO.isPresent()) {
			throw new NotFoundProductException(String.valueOf(id)); 
		}  
		productService.deleteProduct(id);
	}
	
}
