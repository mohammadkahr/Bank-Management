package com.company;

import com.company.Bank.*;


import java.util.Scanner;

import static java.lang.System.exit;

public class Main {
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        //mohammad._.hr
        mainPanel();
    }

    //methods
    static void mainPanel() {
        while (true) {
            System.out.println("Hello dear user.select a number.");
            System.out.println("1.Admin");
            System.out.println("2.User");
            System.out.println("3.Money heyfes");
            System.out.println("9.Exit");

            short select = input.nextShort();
            if (select == 1) {
                Admin.logIn();
            }//Admin
            else if (select == 2) {
                Main.Register();
            }//User
            else if (select == 3) {
                MoneyHeyfes.start();
            }//Money heyfes
            else if (select == 9) {
                System.err.println("\tGood job");
                exit(1);
            }//Exit
        }

    }
    static void Register() {
        System.out.println("Enter your personal id : ");
        String personalId = input.next();
        boolean logIn = MySQL.checkAcc(personalId);
        if (logIn) {
            userPanel(personalId);
        } else
            System.out.println("Admin please add a person with this personal id");
    }
    static void userPanel(String personalId) {
        while (true) {
            System.out.println("Select a number : ");
            System.out.println("1.Bank services");
            System.out.println("2.Sell estate");
            System.out.println("3.Registration services");
            System.out.println("9.Back");

            short select = input.nextShort();

            if (select == 1) {
                Main.bankServicesPAnel(personalId);
            }//Bank services
            else if (select == 2) {
                Estate.sellEstate(personalId);
            }//Sell estate
            else if (select == 3) {
                Main.registrationPanel(personalId);
            }//Registration services
            else if (select == 9) {
                Main.mainPanel();
            }//Back
        }
    }
    static void bankServicesPAnel(String personalId) {
        while (true) {
            System.out.println("Select a number : ");
            System.out.println("1.Create a loan account ");
            System.out.println("2.Create a current account");
            System.out.println("3.Create a deposit account");
            System.out.println("4.Loan account services");
            System.out.println("5.Current account services");
            System.out.println("6.Deposit account services");
            System.out.println("9.Back");

            short select = input.nextShort();

            if (select == 1) {
                LoanAccount.addAccount();
            }//loan account
            else if (select == 2) {
                CurrentAccount.addAccount();
            }//current account
            else if (select == 3) {
                DepositAccount.addAccount();
            }//deposit account
            else if (select == 4) {
                Main.loanPanel(personalId);
            }//Loan account services
            else if (select == 5) {
                Main.currentPanel(personalId);
            }//Current account services
            else if (select == 6){
                Main.depositPanel(personalId);
            }//Deposit account services
            //        else if (select == ){}//

            else if (select == 9) {
                Main.userPanel(personalId);
            }//Back
        }
    }
    static void loanPanel(String personalId) {
        while (true) {
            System.out.println("Select a number : ");
            System.out.println("1.Deposit");
            System.out.println("2.Withdrawal");
            System.out.println("3.Get inventory");
            System.out.println("9.Back");

            short select = input.nextShort();

            if (select == 1) {
                BankingServices.deposit(personalId);
            }//Deposit
            else if (select == 2) {
                BankingServices.withdrawal(personalId);
            }//withdrawal
            else if (select == 3) {
                BankingServices.getInventory(personalId);
            }//getInventory
            else if (select == 9) {
                Main.bankServicesPAnel(personalId);
            }//Back
        }
    }
    static void currentPanel(String personalId) {
        while (true) {
            System.out.println("Select a number : ");
            System.out.println("1.Deposit");
            System.out.println("2.Withdrawal");
            System.out.println("3.Get inventory");
            System.out.println("4.Create a cheque");
            System.out.println("5.Receive a cheque");
            System.out.println("6.Receive a Borrowing");
            System.out.println("7.repayment");
            System.out.println("9.Back");

            short select = input.nextShort();

            if (select == 1) {
                BankingServices.deposit(personalId);
            }//Deposit
            else if (select == 2) {
                BankingServices.withdrawal(personalId);
            }//withdrawal
            else if (select == 3) {
                BankingServices.getInventory(personalId);
            }//getInventory
            else if (select == 4) {
                Cheque.createCheque(personalId);
            }//Create a cheque
            else if (select == 5) {
                Cheque.passCheque(personalId);
            }//Receive a cheque
            else if (select == 6) {
                Borrowing.addNewBorrowing(personalId);
            }//Receive a Borrowing
            else if (select == 7) {
                Borrowing.repayment(personalId);
            }//repayment


            else if (select == 9) {
                Main.bankServicesPAnel(personalId);
            }//Back
        }
    }
    static void depositPanel(String personalId) {
        while (true) {
            System.out.println("Select a number : ");
            System.out.println("1.Details");
            System.out.println("2.Receive profit");

            System.out.println("9.Back");

            short select = input.nextShort();

            if (select == 1) {
                DepositAccount.details(personalId);
            }//Details
            else if (select == 2){
                DepositAccount.receiveProfit(personalId);
            }//Receive profit
            else if (select == 9) {
                Main.bankServicesPAnel(personalId);
            }//Back
        }
    }
    static void registrationPanel(String personalId) {
        while (true) {
            System.out.println("Select a number :");
            System.out.println("1.Update person");
            System.out.println("9.Back");

            short select = input.nextShort();

            if (select == 1) {
                Person.updatePerson();
            }//Update person
            else if (select == 9) {
                Main.userPanel(personalId);
            }//back
        }
    }
}
