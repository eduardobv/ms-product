package com.edu.product.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.edu.product.model.dto.ProductDTO;
import com.edu.product.model.dto.ProductUpdateDTO;
import com.edu.product.model.entity.Product;

@Mapper(componentModel = "spring")
public interface IProductMapper {
	
	ProductDTO toDTO(Product product);
	
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "created", ignore = true)
	@Mapping(target = "modified", ignore = true)
	Product toEntity(ProductDTO productDTO); 
	
	@Mapping(source = "product.id", target = "id")
	@Mapping(source = "product.name", target = "name")
	@Mapping(source = "product.description", target = "description")
	@Mapping(source = "productDTO.category", target = "category")
	@Mapping(source = "productDTO.price", target = "price")
	@Mapping(source = "productDTO.minimumStock", target = "minimumStock")
	@Mapping(source = "product.created", target = "created")
	@Mapping(source = "product.modified", target = "modified")
	Product mapEntity(Product product, ProductUpdateDTO productDTO);
	
}
