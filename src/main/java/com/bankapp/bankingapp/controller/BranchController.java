package com.bankapp.bankingapp.controller;

import com.bankapp.bankingapp.DTO.request.BranchRequestDTO;
import com.bankapp.bankingapp.DTO.response.BranchResponseDTO;
import com.bankapp.bankingapp.service.BranchService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/branches")
public class BranchController {

    private final BranchService branchService;

    public BranchController(BranchService branchService) {
        this.branchService = branchService;
    }

    @PostMapping
    public ResponseEntity create(@Valid @RequestBody BranchRequestDTO branchRequestDTO){
        Integer branchId = branchService.create(branchRequestDTO);
        return new ResponseEntity(branchId, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity retrieveAll(){
        return new ResponseEntity(branchService.retrieveAll(),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity retrieveById (@Valid @PathVariable Integer id) {
        BranchResponseDTO branchDTO = branchService.retrieveById(id);
        return new ResponseEntity(branchDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity deleteById(@Valid @PathVariable Integer id){
        branchService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

}
