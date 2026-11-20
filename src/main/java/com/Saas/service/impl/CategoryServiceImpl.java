package com.Saas.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.Saas.model.Store;
import com.Saas.model.User;
import com.Saas.payload.dto.CategoryDTO;
import com.Saas.repository.CategoryRepository;
import com.Saas.repository.StoreRepository;
import com.Saas.service.CategoryService;
import com.Saas.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{
	private final CategoryRepository categoryRepository;
	private final UserService userService;
	private final StoreRepository storeRepository;
	
	@Override
	public CategoryDTO createCategory(CategoryDTO dto) throws Exception {
		User user = userService.getCurrentUser();
		
		Store store = storeRepository.findById(dto.getStoreId()).orElseThrow(
				() -> new Exception("Store Not Found")
				);
		
		
		return null;
	}

	@Override
	public List<CategoryDTO> getCategoriesByStore(Long storeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CategoryDTO updateCategory(Long id, CategoryDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteCategory(Long id) {
		// TODO Auto-generated method stub
		
	}
	
	

}
