package com.Saas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Saas.model.Store;

public interface StoreRepository extends JpaRepository<Store, Long>{
	
	Store findByStoreAdminId(Long adminId);

}
