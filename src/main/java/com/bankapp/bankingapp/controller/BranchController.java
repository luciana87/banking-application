package com.bankapp.bankingapp.controller;

import com.bankapp.bankingapp.DTO.request.BranchRequestDTO;
import com.bankapp.bankingapp.DTO.response.BranchResponseDTO;
import com.bankapp.bankingapp.DTO.response.EmployeeResponseDTO;
import com.bankapp.bankingapp.service.BranchService;
import com.bankapp.bankingapp.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(path = "/branches")
public class BranchController {

    private final BranchService branchService;
    private final EmployeeService employeeService;

    public BranchController(BranchService branchService, EmployeeService employeeService) {
        this.branchService = branchService;
        this.employeeService = employeeService;
    }

    @PostMapping
    public ResponseEntity create(@Valid @RequestBody BranchRequestDTO branchRequestDTO){
        Integer branchId = branchService.create(branchRequestDTO);
        return new ResponseEntity(branchId, HttpStatus.CREATED);
    }

    //Devuelve todas las sucursales
    @GetMapping
    public ResponseEntity retrieveAll(){
        return new ResponseEntity(branchService.retrieveAll(),HttpStatus.OK);
    }

    //Devuelve una sucursal específica
    @GetMapping("/{id}")
    public ResponseEntity retrieveById (@Valid @PathVariable Integer id) {
        BranchResponseDTO branchDTO = branchService.retrieveById(id);
        return new ResponseEntity(branchDTO, HttpStatus.OK);
    }

    //Devuelve una lista de empleados que pertenecen a una sucursal específica
    @GetMapping("/{id}/employees")
    public ResponseEntity retrieveEmployeesByBranch (@Valid @PathVariable Integer id) {
        List<EmployeeResponseDTO> employeeListDTO = employeeService.retrieveEmployeeByBranch(id);
        return new ResponseEntity(employeeListDTO, HttpStatus.OK);
    }

    //Elimina una sucursal
    @DeleteMapping("/{id}")
    private ResponseEntity deleteById(@Valid @PathVariable Integer id){
        branchService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    //Modifica algunos campos de una sucursal específica
    @PatchMapping("/{id}/address")
    private ResponseEntity modify(@Valid @PathVariable Integer id, @Valid @RequestBody Map<String, Object> fieldsToModify) {
        branchService.modify(id, fieldsToModify);
        return new ResponseEntity(HttpStatus.OK);
    }

}
