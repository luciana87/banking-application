package com.bankapp.bankingapp.service;

import com.bankapp.bankingapp.DTO.request.AccountRequestDTO;
import com.bankapp.bankingapp.DTO.request.CustomerRequestDTO;
import com.bankapp.bankingapp.DTO.response.AddressResponseDTO;
import com.bankapp.bankingapp.DTO.response.CustomerResponseDTO;
import com.bankapp.bankingapp.entity.Address;
import com.bankapp.bankingapp.entity.Customer;
import com.bankapp.bankingapp.exceptions.ExistingResourceException;
import com.bankapp.bankingapp.exceptions.ResourceNotFoundException;
import com.bankapp.bankingapp.repository.CustomerRepository;
import com.bankapp.bankingapp.utils.Util;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final AddressService addressService;

    public CustomerService(CustomerRepository customerRepository, AddressService addressService) {
        this.customerRepository = customerRepository;
        this.addressService = addressService;
    }

    @Transactional
    public Customer create(CustomerRequestDTO customerRequestDTO) {
        Optional<Customer> customerOptional = findByCustomerNumber(customerRequestDTO.getCustomerNumber());
        if (customerOptional.isEmpty()){
            Address address = addressService.create(customerRequestDTO.getAddressRequestDTO());

            Customer customer = mapToEntity(customerRequestDTO);
            customer.setAddress(address);
            customer = customerRepository.save(customer);

            return customer;
        } else {
            throw new ExistingResourceException("El número de cliente debe ser único. Seleccione uno diferente.");
        }
    }

    public Customer getOrCreate(CustomerRequestDTO customerRequestDTO) {

        Optional<Customer> customerOptional = customerRepository.findByCardNumber(customerRequestDTO.getCardNumber());
        Customer customer = null;

        if (customerOptional.isEmpty()){
            customer = create(customerRequestDTO);
        } else {
            customer = customerOptional.get();
        }
        return customer;
    }

    public List<CustomerResponseDTO> retrieveAll() {
        List<Customer> customerList = customerRepository.findAll();
        return customerList.stream().map(employee -> mapToDTO(employee)).collect(Collectors.toList());
    }
    public CustomerResponseDTO retrieveById(Integer id) {
        Optional<Customer> customerOptional = customerRepository.findById(id);
        if (customerOptional.isEmpty()){
            throw new ResourceNotFoundException("CLiente no encontrado");
        }
        return mapToDTO(customerOptional.get());
    }
    public void delete(Integer id) {
        customerRepository.deleteById(id);
    }

    public void modify(Integer id, Map<String, Object> fieldsToModify) {
        Optional<Customer> customerOptional = findById(id);
        if (customerOptional.isEmpty()){
            throw new ResourceNotFoundException();
        }

        Customer customerToModify = customerOptional.get();
        fieldsToModify.forEach((key,value)-> customerToModify.modifyAttributeValue(key,value));
        addressService.modify(customerToModify.getAddress().getId(), (Map<String, Object>) fieldsToModify.get("address"));

        customerRepository.save(customerToModify);
    }
    public Optional<Customer> findByCustomerNumber(int customerNumber) {
        return customerRepository.findByCustomerNumber(customerNumber);
    }

    public Optional<Customer> findById(Integer id) {
        return customerRepository.findById(id);
    }
    public CustomerResponseDTO mapToDTO(Customer customer) {
        CustomerResponseDTO customerDTO = Util.MODEL_MAPPER.map(customer, CustomerResponseDTO.class);
        AddressResponseDTO addressResponseDTO = addressService.mapToDTO(customer.getAddress());
        customerDTO.setAddressReponseDTO(addressResponseDTO);
        return customerDTO;
    }

    private Customer mapToEntity(CustomerRequestDTO customerRequestDTO) {
        Customer customer = Util.MODEL_MAPPER.map(customerRequestDTO, Customer.class);
        return customer;
    }



}
