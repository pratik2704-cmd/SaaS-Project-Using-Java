package com.Saas.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.Saas.domain.UserRole;
import com.Saas.mapper.CategoryMapper;
import com.Saas.model.Category;
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
		
		Category category = Category.builder()
				.store(store)
				.name(dto.getName())
				.build();
		
		checkAuthority(user, category.getStore());
		return CategoryMapper.toDto(categoryRepository.save(category));
	}

	@Override
	public List<CategoryDTO> getCategoriesByStore(Long storeId) {
		List<Category> categories = categoryRepository.findByStoreId(storeId);
		return categories.stream()
				.map(CategoryMapper :: toDto)
				.collect(Collectors.toList());
	}

	@Override
	public CategoryDTO updateCategory(Long id, CategoryDTO dto) throws Exception {
		Category category = categoryRepository.findById(id).orElseThrow(
				() -> new Exception("Category not Exixt")
				);
		
		User user = userService.getCurrentUser();
	
		category.setName(dto.getName());
		
		checkAuthority(user, category.getStore());
		return CategoryMapper.toDto(categoryRepository.save(category));
	}

	@Override
	public void deleteCategory(Long id) throws Exception {
		Category category = categoryRepository.findById(id).orElseThrow(
				() -> new Exception("Category not Exixt")
				);
		
		User user = userService.getCurrentUser();
		
		checkAuthority(user, category.getStore());
		
		categoryRepository.delete(category);
		
	}
	
	
	private void checkAuthority(User user, Store store) throws Exception {
		boolean isAdmin = user.getRole().equals(UserRole.ROLE_STORE_ADMIN);
		boolean isManager = user.getRole().equals(UserRole.ROLE_STORE_MANAGER);
		boolean isSameStore = user.equals(store.getStoreAdmin());
		
		
		if((isAdmin && isSameStore) && !isManager) {
			throw new Exception("You don't have permission to manage this category");
		}
	}
	
	
}
