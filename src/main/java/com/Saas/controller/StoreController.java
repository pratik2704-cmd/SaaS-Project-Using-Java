package com.Saas.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Saas.domain.StoreStatus;
import com.Saas.exceptions.UserException;
import com.Saas.mapper.StoreMapper;
import com.Saas.model.Store;
import com.Saas.model.User;
import com.Saas.payload.dto.StoreDTO;
import com.Saas.payload.response.ApiResponse;
import com.Saas.service.StoreService;
import com.Saas.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/store")
public class StoreController {
	
	private StoreService storeService;
	private UserService userService;
	
	public StoreController(StoreService storeService, UserService userService) {
		super();
		this.storeService = storeService;
		this.userService = userService;
	}

	
	@PostMapping
	public ResponseEntity<StoreDTO> createStore(@RequestBody StoreDTO storeDTO, 
			@RequestHeader("Authorization") String jwt) throws UserException{
		
		User user = userService.getUserFromJwtToken(jwt);
		return ResponseEntity.ok(storeService.createStore(storeDTO, user));
		
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<StoreDTO> getStoreById(
			@PathVariable Long id,
			@RequestHeader("Authorization") String jwt) throws Exception{

		return ResponseEntity.ok(storeService.getStoreById(id));
	}
	
	
	@GetMapping()
	public ResponseEntity<List<StoreDTO>> getAllStore(
			@RequestHeader("Authorization") String jwt) throws Exception{

		return ResponseEntity.ok(storeService.getAllStore());
	}

	
	@GetMapping("/admin")
	public ResponseEntity<StoreDTO> getStoreByAdmin(
			@RequestHeader("Authorization") String jwt) throws Exception{

		return ResponseEntity.ok(StoreMapper.toDto(storeService.getStoreByAdmin()));
	}
	
	
	@GetMapping("/employee")
	public ResponseEntity<StoreDTO> getStoreByEmployee(
			@RequestHeader("Authorization") String jwt) throws Exception{

		return ResponseEntity.ok(storeService.getStoreByEmployee());
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<StoreDTO> updateStore(@PathVariable Long id,
			@RequestBody StoreDTO storeDTO
			) throws UserException
	{
		return ResponseEntity.ok(storeService.updateStore(id, storeDTO));
	}
	
	@PutMapping("/{id}/moderate")
	public ResponseEntity<StoreDTO> moderateStore(
			@PathVariable Long id,
			@RequestParam StoreStatus status
			) throws Exception
	{
		return ResponseEntity.ok(storeService.moderateStore(id, status));
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse> deleteStore(@PathVariable Long id) throws UserException{
		
		storeService.deleteStore(id);
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setMessage("store delete Successfully...");
		return ResponseEntity.ok(apiResponse);
	}
	
	


	
}
