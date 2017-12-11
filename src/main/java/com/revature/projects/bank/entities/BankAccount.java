package com.revature.projects.bank.entities;

import java.math.BigDecimal;

public interface BankAccount {
    public String getAccountName();
    public String getPin();
    public int getAccountNumber();
    public BigDecimal getBalance();
    public void addFunds(BigDecimal amount);
    public void removeFunds(BigDecimal amount);
}