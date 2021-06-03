package com.edu.product.service.mapper;

import org.mapstruct.Mapper;

import com.edu.product.model.dto.ProductDTO;
import com.edu.product.model.entity.Product;

@Mapper(componentModel = "spring")
public interface IProductMapper {
	
	ProductDTO toDTO(Product product);
	
	Product toEntity(ProductDTO productDTO);
}
