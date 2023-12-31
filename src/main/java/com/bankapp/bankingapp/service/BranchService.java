package com.bankapp.bankingapp.service;

import com.bankapp.bankingapp.DTO.request.BranchRequestDTO;
import com.bankapp.bankingapp.DTO.response.AddressResponseDTO;
import com.bankapp.bankingapp.DTO.response.BranchResponseDTO;
import com.bankapp.bankingapp.entity.Address;
import com.bankapp.bankingapp.entity.Bank;
import com.bankapp.bankingapp.entity.Branch;
import com.bankapp.bankingapp.exceptions.ExistingResourceException;
import com.bankapp.bankingapp.exceptions.ResourceNotFoundException;
import com.bankapp.bankingapp.repository.BranchRepository;
import com.bankapp.bankingapp.utils.Util;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BranchService {
    private final BranchRepository branchRepository;
    private final AddressService addressService;
    private final BankService bankService;

    public BranchService(BranchRepository branchRepository, AddressService addressService, BankService bankService) {
        this.branchRepository = branchRepository;
        this.addressService = addressService;
        this.bankService = bankService;
    }
    @Transactional
    public Integer create(BranchRequestDTO branchRequestDTO) {
        checkForExistingBranch(branchRequestDTO.getBranchCode());

        Address address = addressService.create(branchRequestDTO.getAddressRequestDTO());

        Optional<Bank> bankOptional = bankService.findById(branchRequestDTO.getBank());
        if (bankOptional.isEmpty()){
            throw new ResourceNotFoundException("Banco no puede estar vacío.");
        }
        Bank bank = bankOptional.get();

        Branch branch = mapToEntity(branchRequestDTO);
        branch.setAddress(address);
        branch.setBank(bank);
        branch = branchRepository.save(branch);

        return branch.getId();
    }
    public List<BranchResponseDTO> retrieveAll() {
        List<Branch> branchList = branchRepository.findAll();
        return branchList.stream().map(this::mapToDTO).collect(Collectors.toList());
    }
    public BranchResponseDTO retrieveById(Integer id) {
        Optional<Branch> branch = findById(id);
        if (branch.isEmpty()){
            throw new ResourceNotFoundException("Sucursal bancaria no encontrada");
        }
        return mapToDTO(branch.get());
    }

    public void delete(Integer id) {
        branchRepository.deleteById(id);
    }
    @Transactional
    public void modify(Integer id, Map<String, Object> fieldsToModify) {
        Optional<Branch> optionalBranch = findById(id);

        if (optionalBranch.isEmpty()){
            throw new ResourceNotFoundException();
        }

        Branch branchToModify = optionalBranch.get();
        addressService.modify(branchToModify.getAddress().getId(), fieldsToModify);

    }

    public Optional<Branch> findById(Integer id) {
        return branchRepository.findById(id);
    }

    private void checkForExistingBranch(String branchCode) {
        if (branchRepository.existsByBranchCode(branchCode)) {
            throw new ExistingResourceException();
        }
    }
    private Branch mapToEntity (BranchRequestDTO branchRequestDTO) {
        Branch branch = Util.MODEL_MAPPER.map(branchRequestDTO, Branch.class);
        return branch;
    }

    private BranchResponseDTO mapToDTO(Branch branch) {
        BranchResponseDTO branchDTO = Util.MODEL_MAPPER.map(branch, BranchResponseDTO.class);
        AddressResponseDTO addressResponseDTO = addressService.mapToDTO(branch.getAddress());
        branchDTO.setAddressResponseDTO(addressResponseDTO);
        return branchDTO;
    }



}
