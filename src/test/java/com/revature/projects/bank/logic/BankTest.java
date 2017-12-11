package com.revature.projects.bank.logic;

import org.junit.Test;
import static org.junit.Assert.*;

public class BankTest {
    @Test
    public void amountValidationTest() {
        Bank b = new Bank();

        assertTrue(b.isAmountValid("12.12"));
        assertTrue(b.isAmountValid("138768431.12"));
        assertFalse(b.isAmountValid("125412.2312"));
    }
}