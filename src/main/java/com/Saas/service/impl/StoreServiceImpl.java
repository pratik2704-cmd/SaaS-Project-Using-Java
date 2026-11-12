package com.Saas.service.impl;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.Saas.domain.StoreStatus;
import com.Saas.exceptions.UserException;
import com.Saas.mapper.StoreMapper;
import com.Saas.model.Store;
import com.Saas.model.StoreContact;
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
		Store store = StoreMapper.toEntity(storeDto, user);
		return StoreMapper.toDto(storeRepository.save(store));
	}

	@Override
	public StoreDTO getStoreById(Long id) throws Exception {
		Store store = storeRepository.findById(id).orElseThrow(
				() -> new Exception("Store not found...")
				);
		return StoreMapper.toDto(store);
	}

	@Override
	public List<StoreDTO> getAllStore() {
		List<Store> dtos = storeRepository.findAll();
		
		return dtos.stream().map(StoreMapper::toDto).collect(Collectors.toList());
	}

	@Override
	public Store getStoreByAdmin() throws UserException {
		User admin = userService.getCurrentUser();
		return storeRepository.findByStoreAdminId(admin.getId());
	}

	@Override
	public StoreDTO updateStore(Long id, StoreDTO storeDto) throws UserException {
		User currentUser = userService.getCurrentUser();
		Store existing = storeRepository.findByStoreAdminId(currentUser.getId());
		if(existing == null) {
			throw new UserException("Store not found");
		}
		
		existing.setBrand(storeDto.getBrand());
		existing.setDescription(storeDto.getDescription());
		
		if(storeDto.getStoreType() != null) {
			existing.setStoreType(storeDto.getStoreType());
		}
		
		if(storeDto.getContact() != null) {
			if (storeDto.getContact() != null) {

			    StoreContact contact = StoreContact.builder()
			            .address(storeDto.getContact().getAddress())
			            .phone(storeDto.getContact().getPhone())
			            .email(storeDto.getContact().getEmail())
			            .build();

			    existing.setContact(contact);
			}
		}
		Store updatedStore = storeRepository.save(existing);
		return StoreMapper.toDto(updatedStore);
	}

	@Override
	public void deleteStore(Long id) throws UserException {
		Store store = getStoreByAdmin();
		
		storeRepository.delete(store);
	}

	@Override
	public StoreDTO getStoreByEmployee() throws UserException {
		User currentUser = userService.getCurrentUser();
		
		if(currentUser == null) {
			throw new UserException("you don't have permission to access this store");
		}
		return StoreMapper.toDto(currentUser.getStore());
	}



	@Override
	public StoreDTO moderateStore(Long id, StoreStatus status) throws Exception {
		
		Store store = storeRepository.findById(id).orElseThrow(
				() -> new Exception("Store not found...")
				);
		
		store.setStatus(status);
		Store updatedStore = storeRepository.save(store);
		return StoreMapper.toDto(updatedStore);
	}

}
