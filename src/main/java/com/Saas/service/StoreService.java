package com.Saas.service;

import java.util.List;

import com.Saas.model.Store;
import com.Saas.model.User;
import com.Saas.payload.dto.StoreDTO;

public interface StoreService {
	
	StoreDTO createStore(StoreDTO storeDto, User user);
	StoreDTO getStoreById(Long id);
	List<StoreDTO> getAllStore();
	Store getStoreByAdmin();
	StoreDTO updateStore(Long id, StoreDTO storeDto);
	StoreDTO deleteStore(Long id);
	StoreDTO getStoreByEmployee();
}
