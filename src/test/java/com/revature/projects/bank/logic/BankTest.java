package com.revature.projects.bank.logic;

import org.junit.Test;
import static org.junit.Assert.*;

import org.junit.Before;

public class BankTest {
    private Bank b;

    @Before
    public void initialize() {
        b = new Bank();
    }

    @Test
    public void amountValidationTest() {
        assertTrue(b.isAmountValid("12.12"));
        assertTrue(b.isAmountValid("138768431.12"));
        assertFalse(b.isAmountValid("125412.2312"));
    }

    @Test
    public void pinValidationTest() {
        assertTrue(b.isPINValid("1234"));
        assertFalse(b.isPINValid("123"));
        assertFalse(b.isPINValid("12345"));
    }
}