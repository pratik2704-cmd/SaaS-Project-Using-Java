package com.Saas.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.Saas.model.Store;
import com.Saas.model.User;
import com.Saas.payload.dto.StoreDTO;
import com.Saas.repository.StoreRepository;
import com.Saas.service.StoreService;
import com.Saas.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService{
	
	private StoreRepository storeRepository;	
	private UserService userService;
	
	
	public StoreServiceImpl(StoreRepository storeRepository, UserService userService) {
		super();
		this.storeRepository = storeRepository;
		this.userService = userService;
	}



	@Override
	public StoreDTO createStore(StoreDTO storeDto, User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StoreDTO getStoreById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StoreDTO> getAllStore() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Store getStoreByAdmin() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StoreDTO updateStore(Long id, StoreDTO storeDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StoreDTO deleteStore(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StoreDTO getStoreByEmployee() {
		// TODO Auto-generated method stub
		return null;
	}

}
