package com.bankapp.bankingapp.repository;

import com.bankapp.bankingapp.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    boolean existsByCustomerNumber(int customerNumber);

}
