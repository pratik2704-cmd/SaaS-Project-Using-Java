package com.Saas.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.Saas.mapper.ProductMapper;
import com.Saas.model.Product;
import com.Saas.model.Store;
import com.Saas.model.User;
import com.Saas.payload.dto.ProductDTO;
import com.Saas.repository.ProductRepository;
import com.Saas.repository.StoreRepository;
import com.Saas.service.ProductService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{
	
	private final ProductRepository productRepository;
	private final StoreRepository storeRepository;
	

	@Override
	public ProductDTO createProduct(ProductDTO productDTO, User user) throws Exception {
		Store store = storeRepository.findById(
				productDTO.getStoreId()
				).orElseThrow(
						() -> new Exception("Store not found")
						);
		
		Product product = ProductMapper.toEntity(productDTO, store);
		Product saveProduct = productRepository.save(product);
		return ProductMapper.toDTODto(saveProduct);
	}

	@Override
	public ProductDTO updateProduct(Long id, ProductDTO productDTO, User user) throws Exception {
		Product product = productRepository.findById(id).orElseThrow(
				() -> new Exception("Product not found")
				);
		
		product.setName(productDTO.getName());
		product.setDescription(productDTO.getDescription());
		product.setSku(productDTO.getSku());
		product.setImage(productDTO.getImage());
		product.setMrp(productDTO.getMrp());
		product.setSellingPrice(productDTO.getSellingPrice());
		product.setBrand(productDTO.getBrand());
		product.setUpdatedAt(LocalDateTime.now());
		
		Product saveProduct = productRepository.save(product);
		return ProductMapper.toDTODto(saveProduct);
	}

	@Override
	public void deleteProduct(Long id, User user) throws Exception {
		Product product =  productRepository.findById(id).orElseThrow(
				() -> new Exception("Product not found")
				);
		
		productRepository.delete(product);
	}

	@Override
	public List<ProductDTO> getProductsByStoreId(Long storeId) {
		
		List<Product> products = productRepository.findByStoreId(storeId);
		return products.stream()
				.map(ProductMapper::toDTODto)
				.collect(Collectors.toList());
	}

	@Override
	public List<ProductDTO> searchByKeyword(Long storeId, String keyword) {
		List<Product> products = productRepository.searchByKeyword(storeId, keyword);
		return products.stream()
				.map(ProductMapper::toDTODto)
				.collect(Collectors.toList());
	}

}
