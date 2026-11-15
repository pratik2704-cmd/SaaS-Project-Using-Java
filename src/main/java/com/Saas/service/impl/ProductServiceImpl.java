package com.Saas.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.Saas.model.User;
import com.Saas.payload.dto.ProductDTO;
import com.Saas.repository.ProductRepository;
import com.Saas.service.ProductService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{
	
	private final ProductRepository productRepository;
	

	@Override
	public ProductDTO createProduct(ProductDTO productDTO, User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProductDTO updateProduct(Long id, ProductDTO productDTO, User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteProduct(Long id, User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ProductDTO> getProductsByStoreId(Long storeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductDTO> searchByKeyword(Long storeId, String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

}
