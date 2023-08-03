package com.bankapp.bankingapp.service;

import com.bankapp.bankingapp.DTO.request.AccountRequestDTO;
import com.bankapp.bankingapp.DTO.request.AmountDTO;
import com.bankapp.bankingapp.DTO.request.TransferRequestDTO;
import com.bankapp.bankingapp.DTO.response.AccountResponseDTO;
import com.bankapp.bankingapp.DTO.response.CustomerResponseDTO;
import com.bankapp.bankingapp.entity.*;
import com.bankapp.bankingapp.exceptions.ResourceNotFoundException;
import com.bankapp.bankingapp.repository.AccountRepository;
import com.bankapp.bankingapp.utils.Util;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

        // Obtengo el id del customerDTO
        Integer customerId = accountRequestDTO.getCustomerRequestDTO().getId();
        Customer customer;

        if (customerId != null) {
            // Busco y obtengo el cliente de la DB
            Optional<Customer> customerOptional = customerService.findById(customerId);
            if (customerOptional.isEmpty()) {
                throw new ResourceNotFoundException();
            }
            customer = customerOptional.get();
        } else {
            // Si no existe un cliente con ese id, valido que el clienteDTO no traiga campos vacíos
            customerService.validateCustomer(accountRequestDTO.getCustomerRequestDTO());
            //Creo un cliente nuevo
            customer = customerService.create(accountRequestDTO.getCustomerRequestDTO());
        }


        // Busco y obtengo la sucursal a la que va a pertenecer la cuenta
        Optional<Branch> branchOptional = branchService.findById(accountRequestDTO.getBranchId());
        if (branchOptional.isEmpty()){
            throw new ResourceNotFoundException();
        }
        Branch branch = branchOptional.get();

        // Busco y obtengo el empleado asociado a la cuenta
        Optional<Employee> employeeOptional = employeeService.findById(accountRequestDTO.getEmployeeId());
        if (employeeOptional.isEmpty()){
            throw new ResourceNotFoundException();
        }
        Employee employee = employeeOptional.get();

        // Setteo las entidades encontradas para la creación de la cuenta
        account.setAccountHolder(customer);
        account.setBranch(branch);
        account.setEmployee(employee);

        return account;
    }
    @Transactional
    public String createCurrentAccount (AccountRequestDTO accountRequestDTO){
        CurrentAccount currentAccount = mapToEntityCurrentAccount(accountRequestDTO);

        currentAccount = (CurrentAccount) getAttributes(accountRequestDTO, currentAccount);

        currentAccount = accountRepository.save(currentAccount);

        currentAccount.generateCBU();
        currentAccount = accountRepository.save(currentAccount);

        return currentAccount.getCbu();
    }
    @Transactional
    public String createSavingsAccount (AccountRequestDTO accountRequestDTO){
        SavingsAccount savingsAccount = mapToEntitySavingsAccount(accountRequestDTO);

        savingsAccount = (SavingsAccount) getAttributes(accountRequestDTO, savingsAccount);

        savingsAccount = accountRepository.save(savingsAccount);

        savingsAccount.generateCBU();
        savingsAccount = accountRepository.save(savingsAccount);

        return savingsAccount.getCbu();
    }

    public List<AccountResponseDTO> retrieveAccountsByCustomer(Integer id) {
        Optional<Customer> customerOptional = customerService.findById(id);
        if (customerOptional.isEmpty()){
            throw new ResourceNotFoundException("Cliente no encontrado");
        }

        List<Account> accountList = accountRepository.retrieveAccountsByCustomer(customerOptional.get());
        return accountList.stream().map(account -> mapToDTO(account)).collect(Collectors.toList());
    }

    public AccountResponseDTO retrieveById(String id) {
        Optional<Account> accountOptional = findById(id);
        if (accountOptional.isEmpty()){
            throw new ResourceNotFoundException("Cuenta bancaria no encontrada");
        }
        return mapToDTO(accountOptional.get());
    }

    public void transfer(String id, TransferRequestDTO transferRequestDTO) {
        //Obtengo cuenta de origen según ID
        Optional<Account> accountOptional = accountRepository.findById(id);
        if (accountOptional.isEmpty()){
            throw new ResourceNotFoundException();
        }
        Account fromAccount = accountOptional.get();

        //Obtengo cuenta destino mediante CBU
        String toCBU = transferRequestDTO.getCbu();
        Optional<Account> toAccountOptional = accountRepository.findByCbu(toCBU);

        if (toAccountOptional.isEmpty()){
            throw new ResourceNotFoundException();
        }
        Account toAccount = toAccountOptional.get();

        fromAccount.transfer(transferRequestDTO.getAmount(), toAccount);

        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);
    }

    public void withdraw(String id, AmountDTO amountDTO) {
        Optional<Account> accountOptional = accountRepository.findById(id);
        if (accountOptional.isEmpty()){
            throw new ResourceNotFoundException();
        }

        Account account = accountOptional.get();
        account.withdraw(amountDTO.getAmount());

        accountRepository.save(account);
    }
    public void toDeposit(String id, AmountDTO amountDTO) {
        Optional<Account> accountOptional = accountRepository.findById(id);
        if (accountOptional.isEmpty()){
            throw new ResourceNotFoundException();
        }

        Account account = accountOptional.get();
        account.deposit(amountDTO.getAmount());

        accountRepository.save(account);

    }

    private Optional<Account> findById(String id) {
        return accountRepository.findById(id);
    }

    private AccountResponseDTO mapToDTO(Account account) {
        AccountResponseDTO accountDTO = Util.MODEL_MAPPER.map(account, AccountResponseDTO.class);
        CustomerResponseDTO customerResponseDTO = customerService.mapToDTO(account.getAccountHolder());
        accountDTO.setCustomerResponseDTO(customerResponseDTO);
        accountDTO.setType(account.getType());
        return accountDTO;
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
