package com.bankapp.bankingapp.controller;

import com.bankapp.bankingapp.DTO.request.BankRequestDTO;
import com.bankapp.bankingapp.service.BankService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/banks")
public class BankController {
    private final BankService bankService;

    public BankController(BankService bankService) {
        this.bankService = bankService;
    }

    @PostMapping
    public ResponseEntity create(@RequestBody BankRequestDTO bankRequestDTO){
        BankRequestDTO createdBankDTO = bankService.create(bankRequestDTO);
        return new ResponseEntity(bankRequestDTO.getBankCode(), HttpStatus.CREATED);
    }
}
