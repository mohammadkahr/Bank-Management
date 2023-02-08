package com.company.Bank;
import com.company.MyException.NotEnoughMoney;
import com.company.MySQL;
import com.company.Person;
import java.sql.SQLException;
import java.util.Scanner;

public class BankingServices {
        static Scanner input = new Scanner(System.in);
    //methods
    public static void deposit(String accountId) {
        try {
            Person person = MySQL.checkPersonId(accountId);
            assert person != null;
            System.out.println("You have " + person.getWallet() + " $ in your wallet");
            System.out.println("How much money do you want to deposit?");
            int depositMoney = input.nextInt();

            int personWallet = Integer.parseInt(person.getWallet());
            if (depositMoney > personWallet) {
                throw new NotEnoughMoney("Not enough money!");
            }
            personWallet = personWallet - depositMoney;
            String res = String.valueOf(personWallet);
            person.setWallet(res);

            MySQL.updatePerson(person);

            Account account = MySQL.checkAccountId(accountId);
            assert account != null;
            int newMoney = account.getBankroll() + depositMoney;
            account.setBankroll(newMoney);

            boolean result = MySQL.updateAccount(account);
            if (result) {
                System.out.println("Rigesterd");
            } else
                System.out.println("Error");


        }
        catch (NotEnoughMoney ex){
            ex.getMessage();
        }
        catch (Exception ex){
            ex.getStackTrace();
        }
    }
    public static void withdrawal (String accountId){
        try {
            Account account = MySQL.checkAccountId(accountId);
            assert account != null;
            System.out.println("You have " + account.getBankroll() + " $ in your bankroll");
            System.out.println("How much money do you want to ?");
            int withdrawalMoney = input.nextInt();

            int bankrollMoney = account.getBankroll();
            bankrollMoney = bankrollMoney - withdrawalMoney;
            account.setBankroll(bankrollMoney);
            MySQL.updateAccount(account);


            Person person = MySQL.checkPersonId(accountId);
            assert person != null;


            int newMoney = Integer.parseInt(person.getWallet());
            newMoney = newMoney + withdrawalMoney;
            person.setWallet(String.valueOf(newMoney));

            boolean result = MySQL.updatePerson(person);
            if (result) {
                System.out.println("Rigesterd");
            } else
                System.out.println("Error");
        }
        catch (Exception ex){
            ex.getStackTrace();
        }

    }
    public static void getInventory (String accountId) {
        try {
            Account account = MySQL.checkAccountId(accountId);
            assert account != null;
            System.out.println("You have " + account.getBankroll() + " $ in your bankroll");
        }
        catch (Exception ex){
            ex.getStackTrace();
        }
    }



}
