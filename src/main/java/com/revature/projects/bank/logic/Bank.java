package com.revature.projects.bank.logic;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.ExtendedSSLSession;

import com.revature.projects.bank.dao.AccountDAO;
import com.revature.projects.bank.entities.Account;

public class Bank {
    private AccountDAO accountDAO;

    public Bank() {}

    public Bank(String accountListFilename) {
        accountDAO = new AccountDAO(accountListFilename);
    }

    public void transact(String accountName, BankAction action) {
        Scanner sc = new Scanner(System.in);
        Account account = accountDAO.getAccount(accountName);

        if(account == null && action == BankAction.CREATE_ACCOUNT)  {
            String pin;
            
            do {
                System.out.println("Enter your four-digit PIN: ");
                pin = sc.nextLine();
            } while(!isPINValid(pin));
            account = new Account(accountDAO.getNumberOfAccounts(), accountName, pin, new BigDecimal(500, MathContext.UNLIMITED));
            accountDAO.saveAccount(account);
        } else if(account == null) {
            System.out.println("You do not have an account with us yet!"); 
        }else {
            
            String inputString;

            switch(action) {
                case CREATE_ACCOUNT:
                    System.out.println("You already have an account with us!");
                    break;
                case CHECK_BALANCE:
                    System.out.println("Enter your PIN: ");
                    inputString = sc.nextLine();
                    if(inputString.equals(account.getPin()))
                        System.out.println("Account Balance: " + account.getBalance().toPlainString());
                    else
                        System.out.println("Wrong PIN.");
                    break;
                case DEPOSIT_FUNDS:
                    System.out.println("Enter your PIN: ");
                    inputString = sc.nextLine();
                    if(inputString.equals(account.getPin())) {
                        do {
                            System.out.print("Enter amount to deposit: ");
                            inputString = sc.nextLine();
                        } while(!isAmountValid(inputString));
                        account.addFunds(new BigDecimal(inputString, MathContext.UNLIMITED));
                        accountDAO.saveAccount(account);
                    } else System.out.println("Wrong  PIN.");
                    break;
                case WITHDRAW_FUNDS:
                    System.out.println("Enter your PIN: ");
                    inputString = sc.nextLine();
                    if(inputString.equals(account.getPin())) {
                        do {
                            System.out.println("Enter amount to withdraw: ");
                            inputString = sc.nextLine();
                        } while(!isAmountValid(inputString));
                        account.removeFunds(new BigDecimal(inputString, MathContext.UNLIMITED));
                        accountDAO.saveAccount(account);
                    } else System.out.println("Wrong PIN.");
                    break;
                case CLOSE_ACCOUNT:
                    accountDAO.removeAccount(accountName);
                    break;
            }
            
        }
    }

    public boolean isAmountValid(String amountString) {
        Pattern p = Pattern.compile("\\d+\\.\\d{0,2}");
        Matcher m = p.matcher(amountString);
        
        if(m.matches()) return true;
        else { System.out.println("Invalid value for amount."); return false; }
    }

    public boolean isPINValid(String pin) {
        Pattern p = Pattern.compile("\\d{4}");
        Matcher m = p.matcher(pin);
        
        if(m.matches()) return true;
        else {System.out.println("Invalid PIN."); return false; }
    }

    public void walkIn() {
        String accountName;
        Scanner sc = new Scanner(System.in);
        boolean finished = false;

        System.out.println("Welcome to the Console Banking App!");
        System.out.println("What is your name?");
        accountName = sc.nextLine();
        System.out.println("Welcome, " + accountName);

        do {
            String actionString;
            char action;

            printMenu();
            actionString = sc.nextLine();
            action = actionString.charAt(0);

            switch(action) {
                case 'A':
                    transact(accountName, BankAction.CREATE_ACCOUNT);
                    break;
                case 'B':
                    transact(accountName, BankAction.CHECK_BALANCE);
                    break;
                case 'C':
                    transact(accountName, BankAction.DEPOSIT_FUNDS);
                    break;
                case 'D':
                    transact(accountName, BankAction.WITHDRAW_FUNDS);
                    break;
                case 'E':
                    transact(accountName, BankAction.CLOSE_ACCOUNT);
                    break;
                case 'F':
                    finished = true;
                    accountDAO.updateFileRecords();
                    System.out.println("Thank you for banking with us!");
            }

        } while(!finished);
    }

    private void printMenu() {
        System.out.println("Possible Actions:");
        System.out.println("  A - Create Account");
        System.out.println("  B - Check Balance");
        System.out.println("  C - Deposit Funds");
        System.out.println("  D - Withdraw Funds");
        System.out.println("  E - Close Account");
        System.out.println("  F - Walk Out");
        System.out.println("What do you want to do?");
    }
}