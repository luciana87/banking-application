package com.bankapp.bankingapp.controller;

import com.bankapp.bankingapp.DTO.response.AccountResponseDTO;
import com.bankapp.bankingapp.DTO.response.CustomerResponseDTO;
import com.bankapp.bankingapp.service.AccountService;
import com.bankapp.bankingapp.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping (path = "/customers")
public class CustomerController {
    private final CustomerService customerService;
    private final AccountService accountService;

    public CustomerController(CustomerService customerService, AccountService accountService) {
        this.customerService = customerService;
        this.accountService = accountService;
    }

//    @PostMapping
//    public ResponseEntity create(@Valid @RequestBody CustomerRequestDTO customerRequestDTO){
//        Integer customerId = customerService.create(customerRequestDTO);
//        return new ResponseEntity(customerId, HttpStatus.CREATED);
//    }

    @GetMapping
    public ResponseEntity retrieveAll(){
        return new ResponseEntity(customerService.retrieveAll(),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity retrieveById (@Valid @PathVariable Integer id) {
        CustomerResponseDTO customerDTO = customerService.retrieveById(id);
        return new ResponseEntity(customerDTO, HttpStatus.OK);
    }

    @GetMapping ("/{id}/accounts")
    public ResponseEntity retrieveAccountsByCustomer(@Valid @PathVariable Integer id){
        List<AccountResponseDTO> accountRequestDTOList = accountService.retrieveAccountsByCustomer(id);
        return new ResponseEntity(accountRequestDTOList,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity deleteById(@Valid @PathVariable Integer id){
        customerService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    private ResponseEntity modify(@Valid @PathVariable Integer id, @Valid @RequestBody Map<String, Object> fieldsToModify) {
        customerService.modify(id, fieldsToModify);
        return new ResponseEntity(HttpStatus.OK);
    }
}
