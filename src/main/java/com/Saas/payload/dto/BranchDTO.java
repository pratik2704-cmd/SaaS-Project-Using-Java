package com.Saas.payload.dto;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import com.Saas.model.Store;
import com.Saas.model.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BranchDTO {
	
	
	private Long id;
	
	private String name;
	
	private String address;
	
	private String phone;
	
	private String email;
	
	private List<String> workingDays;
	
	private LocalTime openTime;
	
	private LocalTime closeTime;
	
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	
	private StoreDTO store;
	
	private Long storeId;
	
	private UserDto manager;

}
