package com.company.Bank;

import com.company.MyException.NotEnoughMoney;
import com.company.MySQL;
import com.company.Person;

import java.sql.SQLException;
import java.util.Scanner;
import java.util.concurrent.BrokenBarrierException;

public class Borrowing {

    static Scanner input = new Scanner(System.in);

    private int id ; //آیدی وام
    private String personalId ;
    private int price ;
    private int numberOfPayment ;
    private int payment ;
    private int percent;


    //constructor
    public Borrowing(int id, String personalId, int price, int numberOfPayment, int payment, int percent) {
        this.id = id;
        this.personalId = personalId;
        this.price = price;
        this.numberOfPayment = numberOfPayment;
        this.payment = payment;
        this.percent = percent;
    }

    //setters
    public void setId(int id) {
        this.id = id;
    }
    public void setPersonalId(String personalId) {
        this.personalId = personalId;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public void setNumberOfPayment(int numberOfPayment) {
        this.numberOfPayment = numberOfPayment;
    }
    public void setPayment(int payment) {
        this.payment = payment;
    }
    public void setPercent(int percent) {
        this.percent = percent;
    }

    //getters
    public int getId() {
        return id;
    }
    public String getPersonalId() {
        return personalId;
    }
    public int getPrice() {
        return price;
    }
    public int getNumberOfPayment() {
        return numberOfPayment;
    }
    public int getPayment() {
        return payment;
    }
    public int getPercent() {
        return percent;
    }

    //methods
    public static void addNewBorrowing (String personalId) {
        try {
            Account account = MySQL.checkAccountId(personalId);
            assert account != null;
            if (account.getDemerit() < 5) {
                System.out.println("Select price : \n1.10000 $ \n2.25000 $");
                short select = input.nextShort();
                int price;
                if (select == 1) {
                    price = 10000;
                } else
                    price = 25000;

                System.out.println("Select number of payment : \n1.6 \n2.12");
                int numberOfPayment;
                select = input.nextShort();
                if (select == 1) {
                    numberOfPayment = 6;
                } else
                    numberOfPayment = 12;

                System.out.println("Select bank loan interest rate \n1.9 \n2.19");
                int percent;
                select = input.nextShort();
                if (select == 1) {
                    percent = 10;
                } else
                    percent = 20;

                int payment = ((price * percent) / (numberOfPayment * 100));

                int id = MySQL.getMaxIdBorrowing();
                id++;

                Borrowing borrowing = new Borrowing(id, personalId, price, numberOfPayment, payment, percent);


                boolean result = MySQL.addBorrowing(borrowing);
                if (result) {
                    System.out.println("Rigesterd");
                    Person per = MySQL.checkPersonId(personalId);
                    int money = Integer.parseInt(per.getWallet());
                    money = money + price;
                    per.setWallet(money + "");

                    boolean re = MySQL.updatePerson(per);
                    if (re) {
                        System.out.println("Your money in wallet is : " + money);
                        System.out.println("Rigesterd");
                    } else {
                        System.out.println("Error");
                    }
                } else
                    System.out.println("Error");
            }
            else {
                System.out.println("You can't get vam");
                System.out.println("your demerit is more than 4");
            }
        }
        catch (Exception ex){
            ex.getStackTrace();
        }

    }
    public static void repayment (String personalId){
        try {
            Person per = MySQL.checkPersonId(personalId);
            int money = Integer.parseInt(per.getWallet());
            Borrowing bor = MySQL.checkBorrowing(personalId);
            Account acc = MySQL.checkAccountId(personalId);
            if (per != null && bor != null && acc != null) {
                System.out.println("Your loan repayment amount is : " + bor.getPayment() + " $");
                System.out.println("You have " + money + " $ in your wallet");
                if (bor.getPayment() > money) {
                    int demerit = acc.getDemerit();
                    demerit ++ ;
                    acc.setDemerit(demerit);
                    MySQL.updateAccount(acc);
                    throw new NotEnoughMoney("Not enough money ! ");
                }
                else{
                    int wallet = Integer.parseInt(per.getWallet()) - bor.getPayment();
                    per.setWallet(wallet+"");
                    MySQL.updatePerson(per);

                    int numberOfPayment = bor.getNumberOfPayment();
                    numberOfPayment--;
                    bor.setNumberOfPayment(numberOfPayment);
                    boolean res = MySQL.updateBorrowing(bor);
                    if (res){
                        System.out.println("You have "+wallet+" $ in your wallet");
                        System.out.println("Number of payment is : " + numberOfPayment);
                        System.out.println("Rigesterd");
                    }
                    else
                        System.out.println("Error");

                }



            }
            else {
                System.out.println("You don't have any vam");
            }
        }
        catch (NotEnoughMoney ex){
            System.out.println(ex.getMessage());
        }
        catch (Exception ex){
            ex.getStackTrace();
        }
    }


}
