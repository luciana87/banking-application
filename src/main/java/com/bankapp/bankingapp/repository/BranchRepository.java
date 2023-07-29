package com.bankapp.bankingapp.repository;

import com.bankapp.bankingapp.entity.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Integer> {
    boolean existsByBranchCode(String branchCode);

}
