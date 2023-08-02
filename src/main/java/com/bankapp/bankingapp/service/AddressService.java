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
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Optional;

@Service
public class AddressService {

    private final AddressRepository addressRepository;

    public AddressService (AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }
    @Transactional
    public Address create (AddressRequestDTO addressRequestDTO) {

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
    @Transactional
    public void modify (Integer id, Map<String, Object> fieldsToModify) {
        Optional<Address> address = findById(id);

        if (address.isEmpty()){
            throw new ResourceNotFoundException();
        }

        Address addressToModify = address.get();
        fieldsToModify.forEach((key,value)-> addressToModify.modifyAttributeValue(key,value));
        addressRepository.save(addressToModify);
    }
    @Transactional
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


}
