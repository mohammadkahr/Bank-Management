package com.company.Bank;

import com.company.MySQL;
import com.company.Person;

import java.sql.SQLException;
import java.util.Date;
import java.util.Objects;
import java.util.Scanner;

public class DepositAccount extends Account{
    //سپرده
    static Scanner input = new Scanner(System.in);

    private double interestRates ;
    private int time ;

    //constructor
    public DepositAccount(String accountId, String personId, int bankroll, String date, int demerit, double interestRates, int time) {
        super(accountId, personId, bankroll, date, demerit);
        this.interestRates = interestRates;
        this.time = time;
    }


    //setters
    public void setInterestRates(double interestRates) {
        this.interestRates = interestRates;
    }
    public void setTime(int time) {
        this.time = time;
    }

    //getters
    public double getInterestRates() {
        return interestRates;
    }
    public int getTime() {
        return time;
    }

    //methods
    public static void addAccount() {
        try {
            System.out.println("You are going to add new account. ");
            System.out.println("Enter your personal id : ");
            String personalId = input.next();
            System.out.println("Enter your name : ");
            String name = input.next();
            System.out.println("Enter date today : ");
            String date = input.next();

            DepositAccount account = new DepositAccount("0", personalId, 0, date, 0, 0 , 0);

            System.out.println("Select your account type :");
            System.out.println("1.Short (10% - 10d)");
            System.out.println("2.Long (30% - 30d)");
            System.out.println("3.VIP (50% - 50d)");
            short select = input.nextShort();
            if (select == 1) {
                account.setInterestRates(1.10);
                account.setTime(10);
            } else if (select == 2) {
                account.setInterestRates(1.30);
                account.setTime(30);
            } else {
                account.setInterestRates(1.50);
                account.setTime(50);
            }
            BankingServices.deposit(personalId);
            System.out.println("Please enter money that you want to deposit");
            int bankroll = input.nextInt();
            account.setBankroll(bankroll);

            boolean result = MySQL.addAccountDep(account);
            if (result)
                System.out.println("Rigesterd");
            else
                System.out.println("Error");


        }
        catch (Exception ex){
            ex.getStackTrace();
        }
    }
    public static void details(String personalId) {
        DepositAccount dep = MySQL.detailsDep(personalId);
        if (dep != null) {
            System.out.println("Account id : " + dep.getAccountId());
            System.out.println("Bankroll : " + dep.getBankroll());
            System.out.println("Interest rate : " + dep.getInterestRates());
            System.out.println("Date : " + dep.getDate());
            System.out.println("");
        }
        else
            System.out.println("Not found ");
    }
    public static void receiveProfit (String personalId)  {
        DepositAccount dep = MySQL.detailsDep(personalId);
        if (dep != null) {
            details(personalId);

            Date date = MySQL.loadDay();
            System.out.println("Today is : " + date);

            int money = (int) (dep.getBankroll() * dep.getInterestRates());

            Person person = MySQL.checkPersonId(personalId);
            int wallet = Integer.parseInt(person.getWallet());
            wallet = wallet + money ;

            person.setWallet(wallet+"");

            boolean res = MySQL.updatePerson(person);
            if(res){
                System.out.println("ok");
                //pak kardanbn dep acc
                boolean result = MySQL.deleteDepAcc(personalId);
                if (result){
                    System.out.println("Your account deleted");
                }
                else
                    System.out.println("Your account didn't deleted");
            }
            else
                System.out.println("Error");


        }
        //tarikh emroz
    }


}
