package com.bankapp.bankingapp.service;

import com.bankapp.bankingapp.DTO.request.AccountRequestDTO;
import com.bankapp.bankingapp.entity.*;
import com.bankapp.bankingapp.exceptions.ExistingResourceException;
import com.bankapp.bankingapp.exceptions.ResourceNotFoundException;
import com.bankapp.bankingapp.repository.AccountRepository;
import com.bankapp.bankingapp.utils.Util;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final CustomerService customerService;
    private final BranchService branchService;
    private final EmployeeService employeeService;

    public AccountService(AccountRepository accountRepository, CustomerService customerService, BranchService branchService, EmployeeService employeeService) {
        this.accountRepository = accountRepository;
        this.customerService = customerService;
        this.branchService = branchService;
        this.employeeService = employeeService;
    }
    public Account getAttributes(AccountRequestDTO accountRequestDTO, Account account) {
        Customer customer = customerService.getOrCreate(accountRequestDTO.getCustomerRequestDTO());

        Optional<Branch> branchOptional = branchService.findById(accountRequestDTO.getBranchId());
        if (branchOptional.isEmpty()){
            throw new ResourceNotFoundException();
        }
        Branch branch = branchOptional.get();

        Optional<Employee> employeeOptional = employeeService.findById(accountRequestDTO.getEmployeeId());
        if (employeeOptional.isEmpty()){
            throw new ResourceNotFoundException();
        }
        Employee employee = employeeOptional.get();

        account.setAccountHolder(customer);
        account.setBranch(branch);
        account.setEmployee(employee);

        return account;
    }

    public String createCurrentAccount (AccountRequestDTO accountRequestDTO){
        CurrentAccount currentAccount = mapToEntityCurrentAccount(accountRequestDTO);

        Optional<Account> accountOptional = accountRepository.findByAccountNumber(accountRequestDTO.getAccountNumber());
        if (!accountOptional.isEmpty()){
            throw new ExistingResourceException("El número de cuenta seleccionado ya existe. Seleccione uno diferente.");
        }
        currentAccount = (CurrentAccount) getAttributes(accountRequestDTO, currentAccount);

        accountRepository.save(currentAccount);

        return currentAccount.getAccountNumber();
    }

    public String createSavingsAccount (AccountRequestDTO accountRequestDTO){
        SavingsAccount savingsAccount = mapToEntitySavingsAccount(accountRequestDTO);

        savingsAccount = (SavingsAccount) getAttributes(accountRequestDTO, savingsAccount);

        accountRepository.save(savingsAccount);

        return savingsAccount.getAccountNumber();
    }
    private CurrentAccount mapToEntityCurrentAccount(AccountRequestDTO accountRequestDTO) {
        CurrentAccount currentAccount = Util.MODEL_MAPPER.map(accountRequestDTO, CurrentAccount.class);
        return currentAccount;
    }
    private SavingsAccount mapToEntitySavingsAccount(AccountRequestDTO accountRequestDTO) {
        SavingsAccount savingsAccount = Util.MODEL_MAPPER.map(accountRequestDTO, SavingsAccount.class);
        return savingsAccount;
    }

}
