package com.Saas.service;

import java.util.List;

import com.Saas.domain.StoreStatus;
import com.Saas.exceptions.UserException;
import com.Saas.model.Store;
import com.Saas.model.User;
import com.Saas.payload.dto.StoreDTO;

public interface StoreService {
	
	StoreDTO createStore(StoreDTO storeDto, User user);
	StoreDTO getStoreById(Long id) throws Exception;
	List<StoreDTO> getAllStore();
	Store getStoreByAdmin() throws UserException;
	StoreDTO updateStore(Long id, StoreDTO storeDto) throws UserException;
	void deleteStore(Long id) throws UserException;
	StoreDTO getStoreByEmployee() throws UserException;
	
	StoreDTO moderateStore(Long id, StoreStatus status) throws Exception;
}
