package com.revature.projects.bank.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.MathContext;

public class Account implements Serializable, BankAccount {
    private static final long serialVersionUID = 42L;
    private int accountNumber;
    private String accountName;
    private String pin;
    private BigDecimal balance;

    public Account() {}

    public Account(int accountNumber, String accountName, String pin, BigDecimal balance) {
        this.accountNumber = accountNumber;
        this.accountName = accountName;
        this.pin = pin;
        this.balance = balance;
    }

    /**
     * @return the accountName
     */
    public String getAccountName() {
        return accountName;
    }

    /**
     * @return the pin
     */
    public String getPin() {
        return pin;
    }

    /**
     * @return the accountNumber
     */
    public int getAccountNumber() {
        return accountNumber;
    }

    /**
     * @return the balance
     */
    public BigDecimal getBalance() {
        return balance;
    }

    /**
     * @param amount to add to balance
     */
    public void addFunds(BigDecimal amount) {
        balance = balance.add(amount, MathContext.UNLIMITED);
    }

    /**
     * @param amount to deduct from balance
     */
    public void removeFunds(BigDecimal amount) {
        balance = balance.subtract(amount, MathContext.UNLIMITED);
    }
}