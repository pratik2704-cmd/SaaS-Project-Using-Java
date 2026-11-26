package com.Saas.mapper;

import java.time.LocalDateTime;

import com.Saas.model.Store;
import com.Saas.model.User;
import com.Saas.payload.dto.StoreDTO;

public class StoreMapper {

    public static StoreDTO toDto(Store store) {

        if (store == null) {
            throw new IllegalArgumentException("Store cannot be null");
        }

        StoreDTO storeDTO = new StoreDTO();

        storeDTO.setId(store.getId());
        storeDTO.setBrand(store.getBrand());
        storeDTO.setDescription(store.getDescription());

        if (store.getStoreAdmin() != null) {
            storeDTO.setStoreAdmin(
                UserMapper.toDTO(store.getStoreAdmin())
            );
        }

        storeDTO.setStoreType(store.getStoreType());
        storeDTO.setContact(store.getContact());
        storeDTO.setCreatedAt(store.getCreatedAt());
        storeDTO.setUpdatedAt(store.getUpdatedAt());
        storeDTO.setStatus(store.getStatus());

        return storeDTO;
    }

    public static Store toEntity(StoreDTO storeDTO, User storeAdmin) {

        Store store = new Store();

        store.setId(storeDTO.getId());
        store.setBrand(storeDTO.getBrand());
        store.setDescription(storeDTO.getDescription());
        store.setStoreAdmin(storeAdmin);
        store.setContact(storeDTO.getContact());
        store.setCreatedAt(LocalDateTime.now());
        store.setUpdatedAt(LocalDateTime.now());

        return store;
    }
}