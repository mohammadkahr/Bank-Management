package com.company.Bank;

import com.company.MySQL;
import java.sql.SQLException;
import java.util.Scanner;

public class Cheque {

    static Scanner input = new Scanner(System.in);

    private String idReceiver;
    private String price ;
    private String date ;
    private String accountId ;
    private String idSender ;


    //constructor
    public Cheque(String idReceiver, String price, String date, String accountId, String idSender) {
        this.idReceiver = idReceiver;
        this.price = price;
        this.date = date;
        this.accountId = accountId;
        this.idSender = idSender;
    }

    //setters
    public void setPrice(String price) {
        this.price = price;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
    public void setIdReceiver(String nameReceiver) {
        this.idReceiver = nameReceiver;
    }
    public void setIdSender(String idSender) {
        this.idSender = idSender;
    }

    //getters
    public String getPrice() {
        return price;
    }
    public String getDate() {
        return date;
    }
    public String getAccountId() {
        return accountId;
    }
    public String getIdReceiver() {
        return idReceiver;
    }
    public String getIdSender() {
        return idSender;
    }


    //method
    public static void createCheque(String accountId ) {
        try {
            System.out.println("Enter receiver's personal id : ");
            String idReceiver = input.next();
            System.out.println("Enter price of cheque : ");
            String price = input.next();
            System.out.println("Enter date cheque : ");
            String date = input.next();
            System.out.println("Enter your account id :");
            String accountId1 = input.next();

            Cheque cheque = new Cheque(idReceiver, price, date,accountId1 , accountId);

            boolean result = MySQL.addNewCheque(cheque);
            if (result)
                System.out.println("Rigesterd");
            else
                System.out.println("Error");
        }
        catch( Exception ex){
            ex.getStackTrace();
        }

    }
    public static void passCheque(String idReceiver)  {
        try {
            Cheque cheque = MySQL.checkCheque(idReceiver);
            assert cheque != null;
            String price = cheque.getPrice();
            int priceInt = Integer.parseInt(price);

            Account account = MySQL.checkAccountId(idReceiver);
            assert account != null;
            int priceCheque = account.getBankroll();
            priceCheque = priceCheque + priceInt;
            account.setBankroll(priceCheque);

            Account account1 = MySQL.checkAccountId(cheque.getIdSender());
            assert account1 != null;
            int priceCheque1 = account1.getBankroll();
            priceCheque1 = priceCheque - priceInt;
            account1.setBankroll(priceCheque1);

            boolean result = MySQL.updateAccount(account);
            if (result) {
                System.out.println("your new bankroll is " + account.getBankroll() + " $");
                boolean res = MySQL.deleteCheque(cheque);
                if (res) {
                    System.out.println("Cheque deleted");
                } else {
                    System.out.println("Cheque wasn't deleted");
                }
            } else {
                System.out.println("Error");
            }
            boolean res = MySQL.updateAccount(account1);
            if (res) {
                System.out.println("pol az hesab sader konnade check kam shod");
            } else {
                System.out.println("Error");
            }
        }
        catch (Exception ex){
            ex.getStackTrace();
        }
    }


}
