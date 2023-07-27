package com.bankapp.bankingapp.service;

import com.bankapp.bankingapp.DTO.request.BankRequestDTO;
import com.bankapp.bankingapp.entity.Bank;
import com.bankapp.bankingapp.repository.BankRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class BankService {
    ModelMapper modelMapper = new ModelMapper();
    private final BankRepository bankRepository;

    public BankService(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    public BankRequestDTO create(BankRequestDTO bankRequestDTO) {

        checkForExistingBank(bankRequestDTO.getBankCode());

        Bank bank = modelMapper.map(bankRequestDTO, Bank.class);
        bank = bankRepository.save(bank);


        return bankRequestDTO;
    }

    private void checkForExistingBank(String bankCode) {
        if (bankRepository.existsByBankCode(bankCode)) {
//            throw new ExistingResourceException();
        }
    }
}
