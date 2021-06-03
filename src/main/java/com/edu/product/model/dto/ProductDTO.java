package com.edu.product.model.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.edu.product.model.ProductCategory;

import lombok.Data;

@Data
public class ProductDTO  {
	private Long id;
	private String name;
	private String description;
	private ProductCategory category;
	private BigDecimal price;
	private Integer minimumStock;
	private Date created;
	private Date modified;
}
