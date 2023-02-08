package com.company.Bank;
import com.company.MySQL;
import java.sql.SQLException;
import java.util.Scanner;

public class LoanAccount extends Account{

    static Scanner input = new Scanner(System.in);
    //قرض الحسنه
    private CreditCard card ;
    //constructor
    public LoanAccount(String accountId, String personId, int bankroll, String date, int demerit) {
        super(accountId, personId, bankroll, date, demerit);
    }


    //setters
    public void setCard(CreditCard card) {
        this.card = card;
    }

    //getters
    public CreditCard getCard() {
        return card;
    }

    //methods
    public static void addAccount() {
        try{
        System.out.println("You are going to add new account. ");
        System.out.println("Enter your personal id : ");
        String personalId = input.next();
        System.out.println("Enter your name : ");
        String name = input.next();
        System.out.println("Enter date today : ");
        String date = input.next();

        LoanAccount loanAccount = new LoanAccount("0",personalId,0,date,0);

        boolean result = MySQL.addAccount(loanAccount);
        if (result)
            System.out.println("Rigesterd");
        else
            System.out.println("Error");

        boolean resultCard = MySQL.addCreditCard(loanAccount,name);
        if (resultCard)
            System.out.println("Card rigesterd");
        else
            System.out.println("The car didn't created");
        }
        catch (Exception ex ){
            ex.getStackTrace();
        }

    }




}
