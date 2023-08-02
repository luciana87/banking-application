package com.bankapp.bankingapp.repository;

import com.bankapp.bankingapp.entity.Account;
import com.bankapp.bankingapp.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, String> {
    Optional<Account> findByAccountNumber(String accountNumber);

    @Query("SELECT a FROM Account a WHERE a.accountHolder = :accountHolder")
    public List<Account>retrieveAccountsByCustomer(@Param("accountHolder") Customer accountHolder);

    Optional<Account> findByCbu(String toCBU);

}
