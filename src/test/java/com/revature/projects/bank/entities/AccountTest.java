package com.revature.projects.bank.entities;

import java.math.BigDecimal;
import java.math.MathContext;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class AccountTest {
    private Account account;

    @Before
    public void initialize() {
        account = new Account(0, "Kei", "1234", new BigDecimal(500, MathContext.UNLIMITED));
    }

    @Test
    public void accountNameTest() {
        assertEquals("Kei", account.getAccountName());
    }

    @Test
    public void pinTest() {
        assertEquals("1234", account.getPin());
    }

    @Test
    public void balanceTest() {
        assertEquals(new BigDecimal(500, MathContext.UNLIMITED), account.getBalance());
    }

    @Test
    public void addFundsTest() {
        BigDecimal value = new BigDecimal(1200, MathContext.UNLIMITED);

        account.addFunds(new BigDecimal(700, MathContext.UNLIMITED));
        assertEquals(value, account.getBalance());
    }

    @Test
    public void removeFundsTest() {
        BigDecimal value = new BigDecimal(100, MathContext.UNLIMITED);
        account.removeFunds(new BigDecimal(400, MathContext.UNLIMITED));
        assertEquals(value, account.getBalance());
    }
}