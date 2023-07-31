package com.bankapp.bankingapp.controller;

import com.bankapp.bankingapp.DTO.request.AccountRequestDTO;
import com.bankapp.bankingapp.DTO.request.CurrentAccountRequestDTO;
import com.bankapp.bankingapp.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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


//    @GetMapping("/{id}")
//    public ResponseEntity retrieveById (@Valid @PathVariable Integer id) {
//        CustomerResponseDTO customerDTO = customerService.retrieveById(id);
//        return new ResponseEntity(customerDTO, HttpStatus.OK);
//    }
//
//    @DeleteMapping("/{id}")
//    private ResponseEntity deleteById(@Valid @PathVariable Integer id){
//        customerService.delete(id);
//        return new ResponseEntity(HttpStatus.OK);
//    }
//    @PatchMapping("/{id}")
//    private ResponseEntity modify(@Valid @PathVariable Integer id, @Valid @RequestBody Map<String, Object> fieldsToModify) {
//        customerService.modify(id, fieldsToModify);
//        return new ResponseEntity(HttpStatus.OK);
//    }
}
