package com.Saas.model;

import java.time.LocalDateTime;

import com.Saas.domain.StoreStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.EqualsAndHashCode;

@Entity
@EqualsAndHashCode
public class Store {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private String brand;
	
	@OneToOne
	private User storeAdmin;
	
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	
	private String description;
	
	private String storeType;
	
	private StoreStatus status;
	
	@Embedded
	private StoreContact contact = new StoreContact();
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public User getStoreAdmin() {
		return storeAdmin;
	}

	public void setStoreAdmin(User storeAdmin) {
		this.storeAdmin = storeAdmin;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStoreType() {
		return storeType;
	}

	public void setStoreType(String storeType) {
		this.storeType = storeType;
	}

	public StoreStatus getStatus() {
		return status;
	}

	public void setStatus(StoreStatus status) {
		this.status = status;
	}

	public StoreContact getContact() {
		return contact;
	}

	public void setContact(StoreContact contact) {
		this.contact = contact;
	}

	public Store(Long id, String brand, User storeAdmin, LocalDateTime createdAt, LocalDateTime updatedAt,
			String description, String storeType, StoreStatus status, StoreContact contact) {
		super();
		this.id = id;
		this.brand = brand;
		this.storeAdmin = storeAdmin;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.description = description;
		this.storeType = storeType;
		this.status = status;
		this.contact = contact;
	}

	public Store() {	}
	
	
	@PrePersist
	protected void onCreate() {
		createdAt = LocalDateTime.now();
		status = StoreStatus.PENDING;
	}
	
	@PreUpdate
	protected void onUpdate() {
		updatedAt = LocalDateTime.now();
	}

}
