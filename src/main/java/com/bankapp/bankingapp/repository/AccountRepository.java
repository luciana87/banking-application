package com.bankapp.bankingapp.repository;

import com.bankapp.bankingapp.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    Optional<Account> findByAccountNumber(String accountNumber);

}
