package com.bankapp.bankingapp.controller;

import com.bankapp.bankingapp.DTO.request.AddressRequestDTO;
import com.bankapp.bankingapp.service.AddressService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping ("/branches/{branchId}/addresses")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PatchMapping("/{id}")
    private ResponseEntity modify(@Valid @PathVariable Integer id, @Valid @RequestBody Map<String, Object> fieldsToModify) {
        addressService.modify(id, fieldsToModify);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    private ResponseEntity replace(@Valid @PathVariable Integer id, @Valid @RequestBody AddressRequestDTO addressRequestDTO){
        addressService.replace(id, addressRequestDTO);
        return new ResponseEntity(HttpStatus.OK);
    }
}
