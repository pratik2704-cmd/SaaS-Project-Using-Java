package com.Saas.service;

import java.util.List;

import com.Saas.exceptions.UserException;
import com.Saas.payload.dto.CategoryDTO;

public interface CategoryService {
	
	CategoryDTO createCategory(CategoryDTO dto) throws UserException, Exception;
	List<CategoryDTO> getCategoriesByStore(Long storeId);
	CategoryDTO updateCategory(Long id, CategoryDTO dto);
	void deleteCategory(Long id);

}
