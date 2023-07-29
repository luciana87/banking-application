package com.bankapp.bankingapp.controller;

import com.bankapp.bankingapp.DTO.request.BankRequestDTO;
import com.bankapp.bankingapp.DTO.request.BankRequestReplaceDTO;
import com.bankapp.bankingapp.DTO.response.BankResponseDTO;
import com.bankapp.bankingapp.service.BankService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(path = "/banks")
public class BankController {
    private final BankService bankService;

    public BankController(BankService bankService) {
        this.bankService = bankService;
    }

    @PostMapping
    public ResponseEntity create(@Valid @RequestBody BankRequestDTO bankRequestDTO){
        BankRequestDTO createdBankDTO = bankService.create(bankRequestDTO);
        return new ResponseEntity(createdBankDTO.getBankCode(), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity retrieveAll(){
        return new ResponseEntity(bankService.retrieveAll(),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity retrieveById (@PathVariable Integer id) {
        BankResponseDTO bankDTO = bankService.retrieveById(id);
        return new ResponseEntity(bankDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity deleteById(@PathVariable Integer id){
        bankService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    private ResponseEntity replace(@PathVariable Integer id, @RequestBody BankRequestReplaceDTO bankRequestReplaceDTO){
        bankService.replace(id, bankRequestReplaceDTO);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    private ResponseEntity modify(@PathVariable Integer id, @RequestBody Map<String, Object> fieldsToModify) {
        bankService.modify(id, fieldsToModify);
        return new ResponseEntity(HttpStatus.OK);
    }
}
