package com.bankapp.bankingapp.repository;

import com.bankapp.bankingapp.entity.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankRepository extends JpaRepository<Bank, Integer> {
    boolean existsByBankCode(String bankCode);
}
