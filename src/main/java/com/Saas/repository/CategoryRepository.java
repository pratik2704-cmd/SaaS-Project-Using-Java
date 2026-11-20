package com.Saas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Saas.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long >{
	
	List<Category> findByStoreId(long storeId);

}
