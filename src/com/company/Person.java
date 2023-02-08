package com.company;
import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Person {


    static Scanner input = new Scanner(System.in);


    private String name;
    private String id ; //کدملی
    private String age;
    private String gender;
    private String wallet ;

    static ArrayList<Person> persons = new ArrayList<>();

    //constructor
    public Person(String name, String id, String age, String gender, String wallet) {
        this.name = name;
        this.id = id;
        this.age = age;
        this.gender = gender;
        this.wallet = wallet;
    }


    //setters
    public void setName(String name) {
        this.name = name;
    }
    public void setId(String id) {
        this.id = id;
    }
    public void setAge(String age) {
        this.age = age;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public void setWallet(String wallet) {
        this.wallet = wallet;
    }

    //getters
    public String getName() {
        return name;
    }
    public String getId() {
        return id;
    }
    public String getAge() {
        return age;
    }
    public String getGender() {
        return gender;
    }
    public String getWallet() {
        return wallet;
    }

    //methods
    static void addPerson() {
        try {
            System.out.println("you are going to add new person.");
            System.out.println("Enter name : ");
            String name = input.next();
            System.out.println("Enter your national code");
            String id = input.next();
            System.out.println("Enter your age");
            String age = input.next();
            System.out.println("Gender?(type) \n1.man\n2.woman");
            short select = input.nextShort();
            String gender;
            if (select == 1) {
                gender = "man";
            } else
                gender = "woman";


            Person person = new Person(name, id, age, gender, "0");

            boolean result = MySQL.addPerson(person);
            if (result)
                System.out.println("Rigesterd");
            else
                System.out.println("Error");
        }catch (Exception ex){
            ex.getStackTrace();
        }
    }
    static void loadPerson () {
        try {
            System.out.println("Enter your personal id :");
            String id = input.next();
            boolean result = MySQL.loadPerson(id);
            if (!result)
                System.out.println("Not found");
            System.out.println("Estates : ");
            boolean result1 = MySQL.loadEstate(id);
            if (!result1)
                System.out.println("There isn't any estate with this personal id!");
        }catch (Exception ex){
            ex.getStackTrace();
        }
    }
    static void updatePerson() {
        try {
            System.out.println("you are going to update new person.");
            System.out.println("Enter your national code");
            String id = input.next();

            System.out.println("Enter new name : ");
            String name = input.next();

            System.out.println("Enter your new age");
            String age = input.next();

            System.out.println("Gender?(type) \n1.man\n2.woman");
            short select = input.nextShort();
            String gender;
            if (select == 1) {
                gender = "man";
            } else
                gender = "woman";

            Person person = new Person(name, id, age, gender, "0");

            boolean result = MySQL.updatePerson(person);
            if (result)
                System.out.println("Rigesterd");
            else
                System.out.println("Error");
        }
        catch (Exception ex){
            ex.getStackTrace();
        }
    }
    static void deletePerson() {
        try {
            System.out.println("you are going to delete a person.");
            System.out.println("Enter  national code : ");
            String id = input.next();


            boolean result = MySQL.deletePerson(id);
            if (result)
                System.out.println("Rigesterd");
            else
                System.out.println("Error");
        }
        catch (Exception ex){
            ex.getStackTrace() ;
        }
    }


}
