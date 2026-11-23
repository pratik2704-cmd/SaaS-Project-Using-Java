package com.Saas.payload.dto;

import com.Saas.model.Store;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {
	
	private Long id;
	
	private String name;
	
	//private Store store;
	
	private Long storeId;

}
