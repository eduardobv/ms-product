package com.edu.product.model.dto;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.edu.product.model.ProductCategory;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Builder
@Schema(name = "Product", description = "Product Object")
@Getter
public class ProductDTO  {
	
	@Schema(description = "Unique identifier of the Product", example = "123", format = "int64")
	private Long id;
	
	@Schema(required = true, description = "Name of the Product", example = "OnePlus 9")
	@NotBlank(message = "field 'name' is not present or is empty or null") 
	private String name;
	
	@Schema(required = true, description = "Description of the Product", example = "ONEPLUS 9 5G Smartphone")
	@NotBlank(message = "field 'description' is not present or is empty or null") 
	private String description;
	
	@Schema(required = true, description = "Category of the Product", example = "ELECTRONIC")
	@NotNull(message = "field 'category' is not present or is empty or null") 
	private ProductCategory category;
	
	@Schema(required = true, description = "Price of the Product", example = "699")
	@NotNull(message = "field 'price' is not present or is null") 
	private BigDecimal price;
	
	@Schema(required = true, description = "Minimum stock of the Product", example = "1000")
	@NotNull(message = "field 'minimumStock' is not present or is null") 
	private Integer minimumStock;
	
	@Schema(description = "Date of the product creation")
	private Date created;
	
	@Schema(description = "Date of modification")
	private Date modified;
}
