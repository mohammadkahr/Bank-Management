package com.company;
import java.sql.SQLException;
import java.util.Date;
import java.util.Objects;
import java.util.Scanner;

public class Admin {
    static Scanner input = new Scanner(System.in);

    final String userName = "Admin";
    final String password = "Admin";

    //methods
    static boolean registerLogIn(String user, String password) {
        return Objects.equals(user, "Admin") && Objects.equals(password, "Admin");
    }

    static void logIn() {
        System.out.println("\tLog in \nEnter username : ");
        String username = input.next();
        System.out.println("Enter password : ");
        String password = input.next();
        boolean result = registerLogIn(username, password);
        if (result) {
            Admin.adminPanel();
        } else {
            System.out.println("Username or password is incorrect");
            Main.mainPanel();
        }
    }

    static void adminPanel() {
        while (true) {
            System.out.println("Select a number :");
            System.out.println("1.Estate");
            System.out.println("2.person");
            System.out.println("3.Bank accounts");
            System.out.println("4.Management day ");
            System.out.println("9.back");
            short select = input.nextShort();
            if (select == 1) {
                Admin.estatePanel();
            }//Estate
            else if (select == 2) {
                Admin.personPanel();
            }//person
            else if (select == 3) {
                MySQL.loadFromBankAccount();
            }//Bank accounts
            else if (select == 4) {
                Admin.updateDate();
            }//Management day
//            else if (select == ){}
//            else if (select == ){}
            if (select == 9) {
                Main.mainPanel();
            }//back
        }
    }

    static void estatePanel() {
        while (true) {
            System.out.println("Select a number :");
            System.out.println("1.Add new estate");
            System.out.println("2.Update estate");
            System.out.println("3.Delete estate");
            System.out.println("4.Load estate");
            System.out.println("5.List of estates");
            System.out.println("9.Back");

            short select = input.nextShort();

            if (select == 1) {
                Estate.addNewEstate();
            }//Add new estate
            else if (select == 2) {
                Estate.updateEstate();
            }//Update estate
            else if (select == 3) {
                Estate.deleteEstate();
            }//Delete estate
            else if (select == 4) {
                Estate.loadEstate();
            }//Load estate
            else if (select == 5) {
                MySQL.loadFromSqlEstate();
            }//List of estates
            if (select == 9) {
                Admin.adminPanel();
            }//back
        }
    }

    static void personPanel() {
        while (true) {
            System.out.println("Select a number :");
            System.out.println("1.Add new person");
            System.out.println("2.Update person");
            System.out.println("3.Delete person");
            System.out.println("4.Load person");
            System.out.println("5.List of persons");
            System.out.println("9.Back");

            short select = input.nextShort();

            if (select == 1) {
                Person.addPerson();
            }//Add new person
            else if (select == 2) {
                Person.updatePerson();
            }//Update person
            else if (select == 3) {
                Person.deletePerson();
            }//Delete person
            else if (select == 4) {
                Person.loadPerson();
            }//Load person
            else if (select == 5) {
                MySQL.loadFromSqlPerson();
            }//List of persons
            if (select == 9) {
                Admin.adminPanel();
            }//back
        }
    }

    static void updateDate() {
        try {
            System.out.println("Yo are going to update date ");
            System.out.println("We are in : " + MySQL.loadDay());

            Date date = MySQL.loadDay();

            assert date != null;
            System.out.println("Enter new day : ");
            int day = input.nextInt();
            date.setDate(day);

            System.out.println("Today is : " + date);

            boolean res = MySQL.updateDate((java.sql.Date) date);
            if (res) {
                System.out.println("Rigesterd");
            } else {
                System.out.println("Error");
            }
        } catch (Exception ex) {
            ex.getStackTrace();
        }
    }
}

