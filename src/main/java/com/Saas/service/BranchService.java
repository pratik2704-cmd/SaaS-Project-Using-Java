package com.Saas.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.Saas.exceptions.UserException;
import com.Saas.model.User;
import com.Saas.payload.dto.BranchDTO;

public interface BranchService {
	
	BranchDTO createBranch(BranchDTO branchDTO) throws UserException;
	BranchDTO updateBranch(Long id,BranchDTO branchDTO) throws Exception;
	void deleteBranch(Long id) throws Exception;
	List<BranchDTO> getAllBranchesByStoreId(Long storeId);
	BranchDTO getBranchById(Long id) throws Exception;

}
