package com.edu.product.model.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import com.edu.product.model.ProductCategory;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Schema(name = "ProductUpdate", description = "Product Update Object")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductUpdateDTO {

	@Schema(required = true, description = "Category of the Product", example = "ELECTRONIC")
	@NotNull(message = "field 'category' is not present or is empty or null") 
	private ProductCategory category;
	
	@Schema(required = true, description = "Price of the Product", example = "699")
	@NotNull(message = "field 'price' is not present or is null") 
	private BigDecimal price;
	
	@Schema(required = true, description = "Minimum stock of the Product", example = "1000")
	@NotNull(message = "field 'minimumStock' is not present or is null") 
	private Integer minimumStock;
}
