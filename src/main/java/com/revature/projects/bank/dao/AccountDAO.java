package com.revature.projects.bank.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

import com.revature.projects.bank.entities.Account;

public class AccountDAO {
    private String accountListFilename;
    private Map<String, Integer> accountList;

    public AccountDAO(String accountListFilename) {
        this.accountListFilename = accountListFilename;
        accountList = new HashMap<String, Integer>();
        parseAccountListFile();
    }

    private void parseAccountListFile() {
        File accountListFile = new File(accountListFilename);

        if(accountListFile.exists()) {
            try(BufferedReader in = new BufferedReader(new FileReader(accountListFile))) {
                String temp;

                while((temp = in.readLine()) != null) {
                    String[] strs = temp.split("\\s");
                    if(strs.length >= 2) accountList.put(strs[1], Integer.parseInt(strs[0]));
                    else System.out.println("Account list file format invalid.");
                }
            } catch(IOException e) {
                System.out.println("Error reading file.");
            } catch(NumberFormatException e) {
                System.out.println("Account number format invalid.");
            }
        }
    }

    public int getNumberOfAccounts() {
        return accountList.size();
    }

    public Account getAccount(String accountName) {
        Account account = null;

        if(accountList.containsKey(accountName)) {
            try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(accountName.trim() + ".ser"))) {
                account = (Account) in.readObject();
            } catch(IOException e) {
                System.out.println("Account file not found.");
            } catch(ClassNotFoundException e) {
                System.out.println("Error reading object from file.");
            }
        }

        return account;
    }

    public void saveAccount(Account account) {
        if(!accountList.containsKey(account.getAccountName()))
            accountList.put(account.getAccountName(), account.getAccountNumber());
        
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(account.getAccountName() + ".ser"))) {
            out.writeObject(account);
        } catch(IOException e) {
            System.out.println("Error writing account information to file.");
        }
    }

    public void updateFileRecords() {
        try(BufferedWriter out = new BufferedWriter(new FileWriter(accountListFilename))) {
            for(String key : accountList.keySet())
                out.write(accountList.get(key) + " " + key + "\n");
        } catch(IOException e) {
            System.out.println("Error writing account list to file.");
        }
    }

    public void removeAccount(String accountName) {
        if(!accountList.containsKey(accountName)) System.out.println("Nothing to remove.");
        else {
            File accountFile = new File(accountName + ".ser");
            if(accountFile.exists()) accountFile.delete();
            accountList.remove(accountName);
        }
    }
}