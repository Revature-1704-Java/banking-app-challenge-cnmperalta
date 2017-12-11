package com.revature.projects.bank;

import java.util.Scanner;

import com.revature.projects.bank.logic.Bank;

public class Main {
    public static void main(String[] args) {
        Bank b;
        boolean finished = false;
        Scanner sc = new Scanner(System.in);
        String inputString;

        if(args.length < 1) System.out.println("Usage: java Main /path/to/account/list.txt");
        b = new Bank(args[0]);

        do {
            System.out.println("Do you want to use the bank application (yes/no)? ");
            inputString = sc.nextLine();
            if(inputString.equalsIgnoreCase("yes") || inputString.equalsIgnoreCase("y"))
                b.walkIn();
            else finished = true;
        } while(!finished);
    }
}