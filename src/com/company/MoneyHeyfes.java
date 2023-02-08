package com.company;

import java.sql.SQLException;
import java.util.Scanner;

import static java.lang.System.exit;

public class MoneyHeyfes {
    static Scanner input = new Scanner(System.in);


    static void start() {
        try {
            System.out.println("We are going to have a great bank robbery ...");
            System.out.println("So , lets do it \n\n");
            System.out.println("Yeeees , we did it  ( puver:) )");
            int bankRobbery = MySQL.sumOfBankroll();
            System.out.println("We found " + bankRobbery + " $");
            System.out.println("Lets separated it between us \nYou : " + (bankRobbery / 2) + " \t me : " + (bankRobbery / 2) + "\n\n");

            System.out.println("Oh shit , we fucked up ...");
            System.out.println("We have two choices \n1.Take the money back\n2.Suicide");
            short select = input.nextShort();
            if (select == 1) {
                System.out.println("All right ...");
                System.out.println("Lest find bank accounts : \n");
                MoneyHeyfes.getMoneyBAck();

            } else {
                System.err.println("Oh nooo...");
                exit(1);
            }
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    static void getMoneyBAck() throws SQLException, ClassNotFoundException {
        MySQL.loadFromBankAccountMoneyHeyfes();

        System.out.println("Lets get back money... ");
        System.out.println("Done ");
        System.out.println("We will have a good time in prison :,( \n\n\n");

    }

}
