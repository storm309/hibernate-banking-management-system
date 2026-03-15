package com.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "account")
public class Account {

    @Id
    @Column(name = "account_no")
    private String accountNo;

    @Column(name = "acc_type", nullable = false)
    private String accType;

    @Column(name = "balance")
    private Double balance;

    @ManyToMany(mappedBy = "accounts", cascade = CascadeType.ALL)
    private Set<Customer> customers = new HashSet<>();

    public Account() {
    }

    public Account(String accountNo, String accType, Double balance) {
        this.accountNo = accountNo;
        this.accType = accType;
        this.balance = balance;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getAccType() {
        return accType;
    }

    public void setAccType(String accType) {
        this.accType = accType;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Set<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(Set<Customer> customers) {
        this.customers = customers;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountNo='" + accountNo + '\'' +
                ", accType='" + accType + '\'' +
                ", balance=" + balance +
                '}';
    }
}
