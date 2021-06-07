package com.edu.product.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.edu.product.model.ProductCategory;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Product")
public class Product implements Serializable{
 
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	private String description; 
	@Enumerated(EnumType.STRING)
	private ProductCategory category;
	
	private BigDecimal price;
	@Column(name = "minimumstock")
	private Integer minimumStock;
	
	@JsonFormat(pattern = "yyyy-MM-dd")  
	private Date created;
	
	@JsonFormat(pattern = "yyyy-MM-dd")  
	private Date modified;
}
