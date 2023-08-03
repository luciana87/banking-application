package com.bankapp.bankingapp.repository;

import com.bankapp.bankingapp.entity.Employee;
import com.bankapp.bankingapp.entity.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    boolean existsByEmployeeNumber(int employeeNumber);

    @Query("SELECT e FROM Employee e WHERE e.branch = :branch")
    public List<Employee>retrieveEmployeeByBranch(@Param("branch") Branch branch);
}
