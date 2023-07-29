package com.bankapp.bankingapp.service;

import com.bankapp.bankingapp.DTO.request.BankRequestDTO;
import com.bankapp.bankingapp.DTO.request.BankRequestReplaceDTO;
import com.bankapp.bankingapp.DTO.response.BankResponseDTO;
import com.bankapp.bankingapp.entity.Bank;
import com.bankapp.bankingapp.exceptions.ExistingResourceException;
import com.bankapp.bankingapp.exceptions.ResourceNotFoundException;
import com.bankapp.bankingapp.repository.BankRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BankService {
    ModelMapper modelMapper = new ModelMapper();
    private final BankRepository bankRepository;

    public BankService(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    public BankRequestDTO create(BankRequestDTO bankRequestDTO) {

        checkForExistingBank(bankRequestDTO.getBankCode());

        Bank bank = mapToEntity(bankRequestDTO);
        bank = bankRepository.save(bank);

        return bankRequestDTO;
    }

    public Object retrieveAll() {
        List<Bank> bankList = bankRepository.findAll();
        return bankList.stream().map(bank -> mapToDTO(bank)).collect(Collectors.toList());
    }

    public BankResponseDTO retrieveById(Integer id) {
        Optional<Bank> bank = findById(id);
        if (bank.isEmpty()){
            throw new ResourceNotFoundException ("Banco no encontrado");
        }
        return mapToDTO(bank.get());
    }

    public void delete(Integer id) {
        bankRepository.deleteById(id);
    }

    public void modify(Integer id, Map<String,Object> fieldsToModify) {
        Optional<Bank> bank = findById(id);

        if (bank.isEmpty()){
            throw new ResourceNotFoundException("Banco no encontrado.");
        }

        Bank bankToModify = bank.get();
        fieldsToModify.forEach((key,value)-> bankToModify.modifyAttributeValue(key,value));
        bankRepository.save(bankToModify);
    }

    public void replace(Integer id, BankRequestReplaceDTO bankRequestReplaceDTO) {
            Optional<Bank> bankOptional = bankRepository.findById(id);
            if (bankOptional.isEmpty()){
                throw new ResourceNotFoundException("Banco no encontrado.");
            }

            Bank bankToReplace = bankOptional.get();
            bankToReplace.setBankCode(bankRequestReplaceDTO.getBankCode());
            bankToReplace.setName(bankRequestReplaceDTO.getName());
            bankRepository.save(bankToReplace);
    }

    private Optional<Bank> findById(Integer id) {
        return bankRepository.findById(id);
    }

    private Bank mapToEntity (BankRequestDTO bankRequestDTO) {
        Bank bank = modelMapper.map(bankRequestDTO, Bank.class);
        return bank;
    }

    private BankResponseDTO mapToDTO(Bank bank) {
        BankResponseDTO bankDTO = modelMapper.map(bank, BankResponseDTO.class);
        return bankDTO;
    }

    private void checkForExistingBank(String bankCode) {
        if (bankRepository.existsByBankCode(bankCode)) {
            throw new ExistingResourceException();
        }
    }


}
