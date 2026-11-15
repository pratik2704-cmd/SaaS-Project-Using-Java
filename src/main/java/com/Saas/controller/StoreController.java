
package com.Saas.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
import com.Saas.model.User;
import com.Saas.payload.dto.StoreDTO;
import com.Saas.payload.response.ApiResponse;
import com.Saas.service.StoreService;
import com.Saas.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stores")
public class StoreController {

    // Dependency Injection
    private final StoreService storeService;
    private final UserService userService;

    // CREATE STORE
    @PostMapping
    public ResponseEntity<StoreDTO> createStore(
            @RequestBody StoreDTO storeDTO,
            @RequestHeader("Authorization") String jwt)
            throws UserException {

        User user = userService.getUserFromJwtToken(jwt);

        return ResponseEntity.ok(
                storeService.createStore(storeDTO, user)
        );
    }

    // GET ALL STORES
    @GetMapping
    public ResponseEntity<List<StoreDTO>> getAllStore(
            @RequestHeader("Authorization") String jwt)
            throws Exception {

        return ResponseEntity.ok(storeService.getAllStore());
    }

    // GET STORE BY ADMIN
    @GetMapping("/admin")
    public ResponseEntity<StoreDTO> getStoreByAdmin(
            @RequestHeader("Authorization") String jwt)
            throws Exception {

        return ResponseEntity.ok(
                StoreMapper.toDto(storeService.getStoreByAdmin())
        );
    }

    // GET STORE BY EMPLOYEE
    @GetMapping("/employee")
    public ResponseEntity<StoreDTO> getStoreByEmployee(
            @RequestHeader("Authorization") String jwt)
            throws Exception {

        return ResponseEntity.ok(storeService.getStoreByEmployee());
    }

    // UPDATE STORE
    @PutMapping("/{id}")
    public ResponseEntity<StoreDTO> updateStore(
            @PathVariable Long id,
            @RequestBody StoreDTO storeDTO)
            throws UserException {

        return ResponseEntity.ok(
                storeService.updateStore(id, storeDTO)
        );
    }

    // MODERATE STORE
    @PutMapping("/{id}/moderate")
    public ResponseEntity<StoreDTO> moderateStore(
            @PathVariable Long id,
            @RequestParam StoreStatus status)
            throws Exception {

        return ResponseEntity.ok(
                storeService.moderateStore(id, status)
        );
    }

    // GET STORE BY ID
    @GetMapping("/{id}")
    public ResponseEntity<StoreDTO> getStoreById(
            @PathVariable Long id,
            @RequestHeader("Authorization") String jwt)
            throws Exception {

        return ResponseEntity.ok(
                storeService.getStoreById(id)
        );
    }

    // DELETE STORE
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteStore(
            @PathVariable Long id)
            throws UserException {

        storeService.deleteStore(id);

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Store deleted successfully...");

        return ResponseEntity.ok(apiResponse);
    }
}
