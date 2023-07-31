package com.bankapp.bankingapp.service;

import com.bankapp.bankingapp.DTO.request.EmployeeRequestDTO;
import com.bankapp.bankingapp.DTO.response.AddressResponseDTO;
import com.bankapp.bankingapp.DTO.response.EmployeeResponseDTO;
import com.bankapp.bankingapp.entity.Address;
import com.bankapp.bankingapp.entity.Branch;
import com.bankapp.bankingapp.entity.Employee;
import com.bankapp.bankingapp.exceptions.ExistingResourceException;
import com.bankapp.bankingapp.exceptions.ResourceNotFoundException;
import com.bankapp.bankingapp.repository.EmployeeRepository;
import com.bankapp.bankingapp.utils.Util;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final AddressService addressService;
    private final BranchService branchService;

    public EmployeeService(EmployeeRepository employeeRepository, AddressService addressService, BranchService branchService) {
        this.employeeRepository = employeeRepository;
        this.addressService = addressService;
        this.branchService = branchService;
    }


    @Transactional
    public Integer create(EmployeeRequestDTO employeeRequestDTO) {
        checkForExistingEmployee(employeeRequestDTO.getEmployeeNumber());

        Address address = addressService.create(employeeRequestDTO.getAddressRequestDTO());

        Optional<Branch> branchOptional = branchService.findById(employeeRequestDTO.getBranch());
        if (branchOptional.isEmpty()){
            throw new ResourceNotFoundException();
        }
        Branch branch = branchOptional.get();

        Employee employee = mapToEntity(employeeRequestDTO);
        employee.setAddress(address);
        employee.setBranch(branch);
        employee = employeeRepository.save(employee);

        return employee.getId();
    }

    public List<EmployeeResponseDTO> retrieveAll() {

        List<Employee> employeeList = employeeRepository.findAll();
        return employeeList.stream().map(employee -> mapToDTO(employee)).collect(Collectors.toList());
    }

    public EmployeeResponseDTO retrieveById(Integer id) {
        Optional<Employee> employeeOptional = findById(id);
        if (employeeOptional.isEmpty()){
            throw new ResourceNotFoundException("Empleado no encontrado");
        }
        return mapToDTO(employeeOptional.get());
    }

    public void delete(Integer id) {
        employeeRepository.deleteById(id);
    }

    public List<EmployeeResponseDTO> retrieveEmployeeByBranch(Integer id) {
        Optional<Branch> optionalBranch = branchService.findById(id);
        if (optionalBranch.isEmpty()){
            throw new ResourceNotFoundException("Sucursal no encontrada");
        }

        List<Employee> employeeList = employeeRepository.retrieveEmployeeByBranch(optionalBranch.get());
        return employeeList.stream().map(employee -> mapToDTO(employee)).collect(Collectors.toList());
    }

    @Transactional
    public void modify(Integer id, Map<String, Object> fieldsToModify) {

        Optional<Employee> employeeOptional = findById(id);
        if (employeeOptional.isEmpty()){
            throw new ResourceNotFoundException();
        }
        Employee employeeToModify = employeeOptional.get();

        fieldsToModify.forEach((key,value)-> employeeToModify.modifyAttributeValue(key,value));

        addressService.modify(employeeToModify.getAddress().getId(), (Map<String, Object>) fieldsToModify.get("address"));

        Integer branchId = (Integer) fieldsToModify.get("branch_id");
        if (branchId != null) {
            Optional<Branch> branchOptional = branchService.findById(branchId);
            if (branchOptional.isEmpty()){
                throw new ResourceNotFoundException();
            }
            Branch branch = branchOptional.get();
            employeeToModify.setBranch(branch);
        }

        employeeRepository.save(employeeToModify);
    }

    public Optional<Employee> findById(Integer id) {
        return employeeRepository.findById(id);
    }

    private Employee mapToEntity(EmployeeRequestDTO employeeRequestDTO) {
        Employee employee = Util.MODEL_MAPPER.map(employeeRequestDTO, Employee.class);
        return employee;
    }

    private EmployeeResponseDTO mapToDTO(Employee employee) {
        EmployeeResponseDTO employeeDTO = Util.MODEL_MAPPER.map(employee, EmployeeResponseDTO.class);
        AddressResponseDTO addressResponseDTO = addressService.mapToDTO(employee.getAddress());
        employeeDTO.setAddressReponseDTO(addressResponseDTO);
        return employeeDTO;
    }

    private void checkForExistingEmployee(int employeeNumber) {
        if (employeeRepository.existsByEmployeeNumber(employeeNumber)) {
            throw new ExistingResourceException();
        }
    }

}
