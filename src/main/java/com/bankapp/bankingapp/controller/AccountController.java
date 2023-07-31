package com.bankapp.bankingapp.controller;

import com.bankapp.bankingapp.DTO.request.AccountRequestDTO;
import com.bankapp.bankingapp.DTO.request.CurrentAccountRequestDTO;
import com.bankapp.bankingapp.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping (path = "/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping ("/current")
    public ResponseEntity createCurrentAccount(@Valid @RequestBody CurrentAccountRequestDTO currentAccountRequestDTO){
        String accountId = accountService.createCurrentAccount(currentAccountRequestDTO);
        return new ResponseEntity(accountId, HttpStatus.CREATED);
    }

    @PostMapping ("/savings")
    public ResponseEntity createSavingsAccount(@Valid @RequestBody AccountRequestDTO accountRequestDTO){
        String accountId = accountService.createSavingsAccount(accountRequestDTO);
        return new ResponseEntity(accountId, HttpStatus.CREATED);
    }
}
