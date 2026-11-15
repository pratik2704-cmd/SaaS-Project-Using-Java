package com.Saas.payload.dto;

import java.time.LocalDateTime;

import com.Saas.model.Store;

import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductDTO {
	
private Long id;
	
	private String name;
	
	private String sku;
	
	private String description;
	
	private Double mrp;
	
	private Double sellingPrice;
	private String brand;
	private String image;
	
	//private Category category;
	
	private Long categoryId;
	
	private Long storeId;
	
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

}
