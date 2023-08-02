package com.bankapp.bankingapp.controller;

import com.bankapp.bankingapp.DTO.request.AccountRequestDTO;
import com.bankapp.bankingapp.DTO.request.AmountDTO;
import com.bankapp.bankingapp.DTO.request.CurrentAccountRequestDTO;
import com.bankapp.bankingapp.DTO.request.TransferRequestDTO;
import com.bankapp.bankingapp.DTO.response.AccountResponseDTO;
import com.bankapp.bankingapp.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public ResponseEntity retrieveById (@PathVariable String id) {
        AccountResponseDTO customerDTO = accountService.retrieveById(id);
        return new ResponseEntity(customerDTO, HttpStatus.OK);
    }

    @PostMapping ("/{id}/toDeposit")
    public ResponseEntity toDeposit ( @PathVariable String id,@Valid @RequestBody AmountDTO amountDTO) { //AmountDTO solo monto
        accountService.toDeposit(id, amountDTO);
        return new ResponseEntity("Depósito realizado correctamente.", HttpStatus.OK);
    }

    @PostMapping ("/{id}/withdraw")
    public ResponseEntity withdraw (@PathVariable String id, @Valid @RequestBody AmountDTO amountDTO) {
        accountService.withdraw(id, amountDTO);
        return new ResponseEntity("Monto extraído correctamente.", HttpStatus.OK);
    }

    @PostMapping ("/{id}/transfer")
    public ResponseEntity transfer (@PathVariable String id, @Valid   @RequestBody TransferRequestDTO transferRequestDTO) {
        accountService.transfer(id, transferRequestDTO);
        return new ResponseEntity("Transferencia exitosa.",HttpStatus.OK);
    }




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
