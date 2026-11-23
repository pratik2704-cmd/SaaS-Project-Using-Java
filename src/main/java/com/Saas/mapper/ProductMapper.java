package com.Saas.mapper;

import com.Saas.model.Category;
import com.Saas.model.Product;
import com.Saas.model.Store;
import com.Saas.payload.dto.ProductDTO;

public class ProductMapper {
	
	public static ProductDTO toDTODto(Product product) {
		return ProductDTO.builder()
				.id(product.getId())
				.name(product.getName())
				.sku(product.getSku())
				.description(product.getDescription())
				.mrp(product.getMrp())
				.sellingPrice(product.getSellingPrice())
				.brand(product.getBrand())
				.category(CategoryMapper.toDto(product.getCategory()))
				.storeId(product.getStore() != null?product.getStore().getId(): null)
				.image(product.getImage())
				.createdAt(product.getCreatedAt())
				.updatedAt(product.getUpdatedAt())
				.build();
	//			.categoryId(product.)
	}
	
	public static Product toEntity(ProductDTO productDTO, 
			Store store, 
			Category category) {
		return Product.builder()
				.name(productDTO.getName())
				.store(store)
				.category(category)
				.sku(productDTO.getSku())
				.description(productDTO.getDescription())
				.mrp(productDTO.getMrp())
				.sellingPrice(productDTO.getSellingPrice())
				.brand(productDTO.getBrand())
				.build();
	}

}
