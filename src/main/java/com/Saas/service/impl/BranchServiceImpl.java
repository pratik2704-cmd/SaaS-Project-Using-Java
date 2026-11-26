package com.Saas.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.Saas.exceptions.UserException;
import com.Saas.mapper.BranchMapper;
import com.Saas.model.Branch;
import com.Saas.model.Store;
import com.Saas.model.User;
import com.Saas.payload.dto.BranchDTO;
import com.Saas.repository.BranchRepository;
import com.Saas.repository.StoreRepository;
import com.Saas.repository.UserRepository;
import com.Saas.service.BranchService;
import com.Saas.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BranchServiceImpl implements BranchService{
	
	private final BranchRepository branchRepository;
	private final StoreRepository storeRepository;
	private final UserService userService;
	

	@Override
	public BranchDTO createBranch(BranchDTO branchDTO) throws UserException {

	    User currentUser = userService.getCurrentUser();

	    if (currentUser == null) {
	        throw new UserException("User not authenticated");
	    }

	    Store store = storeRepository.findByStoreAdminId(currentUser.getId());

	    if (store == null) {
	        throw new UserException("No Store found for logged-in user. Create a Store first.");
	    }

	    Branch branch = BranchMapper.toEntity(branchDTO, store);

	    Branch savedBranch = branchRepository.save(branch);

	    return BranchMapper.toDTO(savedBranch);
	}

	@Override
	public BranchDTO updateBranch(Long id, BranchDTO branchDTO) throws Exception {

	    Branch existing = branchRepository.findById(id)
	            .orElseThrow(() -> new Exception("Branch does not exist"));

	    existing.setName(branchDTO.getName());
	    existing.setWorkingDays(branchDTO.getWorkingDays());
	    existing.setEmail(branchDTO.getEmail());
	    existing.setPhone(branchDTO.getPhone());
	    existing.setAddress(branchDTO.getAddress());
	    existing.setOpenTime(branchDTO.getOpenTime());
	    existing.setCloseTime(branchDTO.getCloseTime());

	    existing.setUpdatedAt(LocalDateTime.now());

	    Branch updatedBranch = branchRepository.save(existing);

	    return BranchMapper.toDTO(updatedBranch);
	}

	@Override
	public void deleteBranch(Long id) throws Exception {

	    Branch existing = branchRepository.findById(id)
	            .orElseThrow(() -> new Exception("Branch does not exist"));

	    branchRepository.delete(existing);
	}

	@Override
	public List<BranchDTO> getAllBranchesByStoreId(Long storeId) {
		List<Branch> branches = branchRepository.findByStoreId(storeId);
		return branches.stream().map(BranchMapper::toDTO)
		.collect(Collectors.toList());
	}

	@Override
	public BranchDTO getBranchById(Long id) throws Exception {
		 Branch existing = branchRepository.findById(id)
		            .orElseThrow(() -> new Exception("Branch does not exist"));
		return BranchMapper.toDTO(existing);
	}

}
