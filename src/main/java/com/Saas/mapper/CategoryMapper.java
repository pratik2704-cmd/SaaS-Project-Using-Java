package com.Saas.mapper;

import com.Saas.model.Category;
import com.Saas.payload.dto.CategoryDTO;

public class CategoryMapper {

	public static CategoryDTO toDto(Category category) {
		return CategoryDTO.builder()
				.id(category.getId())
				.name(category.getName())
				.storeId(category.getStore() != null ? category.getStore().getId() : null)
				.build();
	}

}
