package com.Saas.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Saas.exceptions.UserException;
import com.Saas.payload.dto.CategoryDTO;
import com.Saas.payload.response.ApiResponse;
import com.Saas.service.CategoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categories")
public class CategoryController {
	
	private final CategoryService categoryService;
	
	@PostMapping
	public ResponseEntity<CategoryDTO> createCategory(
			@RequestBody CategoryDTO categoryDTO) throws UserException, Exception{
		return ResponseEntity.ok(
				categoryService.createCategory(categoryDTO)
				);
	}
	
	
	@GetMapping("/store/{storeId}")
	public ResponseEntity<List<CategoryDTO>> getCategoryByStoreId(
			@PathVariable Long storeId) throws UserException, Exception{
		return ResponseEntity.ok(
				categoryService.getCategoriesByStore(storeId)
				);
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<CategoryDTO> updateCategory(
			@RequestBody CategoryDTO categoryDTO,
			@PathVariable Long id) throws UserException, Exception{
		return ResponseEntity.ok(
				categoryService.updateCategory(id, categoryDTO)
				);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> deleteCategory(
			@RequestBody CategoryDTO categoryDTO,
			@PathVariable Long id) throws UserException, Exception{
		
		categoryService.updateCategory(id, categoryDTO);
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setMessage("Successfully deleted category");
		
		return ResponseEntity.ok(
				apiResponse
				);
	}

}
