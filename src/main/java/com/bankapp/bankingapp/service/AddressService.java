package com.bankapp.bankingapp.service;

import com.bankapp.bankingapp.DTO.request.AddressRequestDTO;
import com.bankapp.bankingapp.DTO.response.AddressResponseDTO;
import com.bankapp.bankingapp.entity.Address;
import com.bankapp.bankingapp.exceptions.EmptyResourceException;
import com.bankapp.bankingapp.exceptions.ResourceNotFoundException;
import com.bankapp.bankingapp.repository.AddressRepository;
import com.bankapp.bankingapp.utils.Util;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class AddressService {

    private final AddressRepository addressRepository;

    public AddressService (AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public Address create (AddressRequestDTO addressRequestDTO) {
        validateAddress(addressRequestDTO);

        Address address = mapToEntity(addressRequestDTO);
        address = addressRepository.save(address);

        return address;
    }



    public Address mapToEntity (AddressRequestDTO addressRequestDTO) {
        Address address = Util.MODEL_MAPPER.map(addressRequestDTO, Address.class);
        return address;
    }

    public AddressResponseDTO mapToDTO(Address address) {
        AddressResponseDTO addressDTO = Util.MODEL_MAPPER.map(address, AddressResponseDTO.class);
        return addressDTO;
    }

    public void modify (Integer id, Map<String, Object> fieldsToModify) {
        Optional<Address> address = findById(id);

        if (address.isEmpty()){
            throw new ResourceNotFoundException();
        }

        Address addressToModify = address.get();
        fieldsToModify.forEach((key,value)-> addressToModify.modifyAttributeValue(key,value));
        addressRepository.save(addressToModify);
    }
    public void replace (Integer id, AddressRequestDTO addressRequestDTO) {
        Optional<Address> addressOptional = addressRepository.findById(id);

        if (addressOptional.isEmpty()){
            throw new ResourceNotFoundException();
        }

        Address addressToReplace = addressOptional.get();

        addressToReplace.setCity(addressRequestDTO.getCity());
        addressToReplace.setCountry(addressRequestDTO.getCountry());
        addressToReplace.setState(addressRequestDTO.getState());
        addressToReplace.setStreet(addressRequestDTO.getStreet());
        addressToReplace.setPostalCode(addressRequestDTO.getPostalCode());

        addressRepository.save(addressToReplace);
    }

    public Optional<Address> findById(Integer id) {
        return addressRepository.findById(id);
    }

    private void validateAddress(AddressRequestDTO addressRequestDTO) {
        if (addressRequestDTO.getCity().isEmpty()) {
            throw new EmptyResourceException("La ciudad no puede estar vacía.");
        }
        if (addressRequestDTO.getCountry().isEmpty()) {
            throw new EmptyResourceException("El país no puede estar vacío.");
        }
        if (addressRequestDTO.getState().isEmpty()) {
            throw new EmptyResourceException("La provincia no puede estar vacía.");
        }
        if (addressRequestDTO.getPostalCode().isEmpty()) {
            throw new EmptyResourceException("El código postal no puede estar vacío.");
        }
        if (addressRequestDTO.getStreet().isEmpty()) {
            throw new EmptyResourceException("La calle no puede estar vacía.");
        }
    }

}
