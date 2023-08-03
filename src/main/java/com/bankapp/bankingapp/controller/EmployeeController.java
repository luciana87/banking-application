package com.bankapp.bankingapp.controller;

import com.bankapp.bankingapp.DTO.request.EmployeeRequestDTO;
import com.bankapp.bankingapp.DTO.response.EmployeeResponseDTO;
import com.bankapp.bankingapp.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping (path = "/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public ResponseEntity create(@Valid @RequestBody EmployeeRequestDTO employeeRequestDTO){
        Integer employeeId = employeeService.create(employeeRequestDTO);
        return new ResponseEntity(employeeId, HttpStatus.CREATED);
    }

    // Devuelve todos los empleados de todas las sucursales
    @GetMapping
    public ResponseEntity retrieveAll(){
        return new ResponseEntity(employeeService.retrieveAll(),HttpStatus.OK);
    }

    // Devuelve un empleado específico
    @GetMapping("/{id}")
    public ResponseEntity retrieveById (@Valid @PathVariable Integer id) {
        EmployeeResponseDTO employeeDTO = employeeService.retrieveById(id);
        return new ResponseEntity(employeeDTO, HttpStatus.OK);
    }

    // Elimina un empleado
    @DeleteMapping("/{id}")
    private ResponseEntity deleteById(@Valid @PathVariable Integer id){
        employeeService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    private ResponseEntity modify(@Valid @PathVariable Integer id, @Valid @RequestBody Map<String, Object> fieldsToModify) {
        employeeService.modify(id, fieldsToModify);
        return new ResponseEntity(HttpStatus.OK);
    }
}
