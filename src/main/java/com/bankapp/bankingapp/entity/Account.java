package com.bankapp.bankingapp.entity;

import com.bankapp.bankingapp.exceptions.InsufficientBalanceException;
import jakarta.persistence.*;

@Entity
@Inheritance (strategy = InheritanceType.SINGLE_TABLE)
public abstract class Account {
    @Id
    @Column (unique = true)
    protected String accountNumber;

    protected String cbu;
    protected boolean active;
    protected double balance;
    @Column (unique = true)
    protected String alias;

    @ManyToOne
    @JoinColumn (name= "branch_id")
    protected Branch branch;

    @ManyToOne
    @JoinColumn (name= "customer_id")
    protected Customer accountHolder;

    @ManyToOne
    @JoinColumn (name= "employee_id")
    protected Employee employee;
    public Account() {
    }

    public Account(String alias, Branch branch, Customer accountHolder, String cbu) {
        this.active = true;
        this.balance = 0.00d;
        this.alias = alias;
        this.branch = branch;
        this.accountHolder = accountHolder;
        this.cbu = cbu;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public Customer getAccountHolder() {
        return accountHolder;
    }

    public void setAccountHolder(Customer accountHolder) {
        this.accountHolder = accountHolder;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getCbu() {
        return cbu;
    }

    public void setCbu(String cbu) {
        this.cbu = cbu;
    }

    public abstract String getType();

    public  void withdraw(double amount) { // Extraer. Templated method
        if (checkAvailableBalance(amount)) {
            descontarMonto(amount);
        } else {
            throw new InsufficientBalanceException();
        }
    }

    public void descontarMonto(double amount) {
        this.balance -= amount;
    }

    public abstract boolean checkAvailableBalance(double amount); //

    public void toDeposit(double amount) {
        this.balance += amount;
    }
    public void toTransfer(double amount, Account account){
        this.withdraw(amount);
        account.toDeposit(amount);

    } //Cadena de responsabilidades patron: this.extraer()

    public void generateCBU(){
        cbu = getBranch().getBank().getBankCode() + getBranch().getBranchCode() + getAccountNumber();
        setCbu(cbu);
    }

}
