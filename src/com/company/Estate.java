package com.company;
import java.sql.SQLException;
import java.util.Scanner;

public class Estate {
    static Scanner input = new Scanner(System.in);

    private int id ;
    private String personId;
    private String address ;
    private String dateBuy ;
    private String value ;


    //constructor
    public Estate( String personId, String address, String dateBuy, String value) {
        this.personId = personId;
        this.address = address;
        this.dateBuy = dateBuy;
        this.value = value;
    }

    //setters
    public void setId(int id) {
        this.id = id;
    }
    public void setPersonId(String personId) {
        this.personId = personId;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void setDateBuy(String dateBuy) {
        this.dateBuy = dateBuy;
    }
    public void setValue(String value) {
        this.value = value;
    }

    //getters
    public int getId() {
        return id;
    }
    public String getPersonId() {
        return personId;
    }
    public String getAddress() {
        return address;
    }
    public String getDateBuy() {
        return dateBuy;
    }
    public String getValue() {
        return value;
    }


    //Methods
    static void addNewEstate() {
        try {
            System.out.println("you are going to add new estate");
            System.out.println("Enter national code : ");
            String personId = input.next();
            System.out.println("Enter address");
            String address = input.next();
            System.out.println("Enter dateBuy(string)");
            String dateBuy = input.next();
            System.out.println("Enter value");
            String value = input.next();

            Estate estate = new Estate(personId, address, dateBuy, value);

            boolean result = MySQL.addEstate(estate);
            if (result) {
                System.out.println("Id is : " + MySQL.getMaxId());
                System.out.println("Rigesterd");
            } else
                System.out.println("Error");
        }catch (Exception ex){
            ex.getStackTrace();
        }
    }
    static void updateEstate() {
        try {
            System.out.println("you are going to update estate");
            System.out.println("Enter your estate id ");
            int id = input.nextInt();
            System.out.println("Enter national code : ");
            String personId = input.next();
            System.out.println("Enter address");
            String address = input.next();
            System.out.println("Enter dateBuy(string)");
            String dateBuy = input.next();
            System.out.println("Enter value");
            String value = input.next();

            Estate estate = new Estate(personId, address, dateBuy, value);

            boolean result = MySQL.updateEstate(estate, id);
            if (result) {
                System.out.println("Rigesterd");
            } else
                System.out.println("Error");
        }catch (Exception ex){
            ex.getStackTrace();
        }
    }
    static void deleteEstate() {
        try {
            System.out.println("you are going to delete a estate.");
            System.out.println("Enter  estate's id : ");
            int id = input.nextInt();


            boolean result = MySQL.deleteEstate(id);
            if (result)
                System.out.println("Rigesterd");
            else
                System.out.println("Error");
        }catch (Exception ex){
            ex.getStackTrace();
        }
    }
    static void loadEstate () {
        try {
            System.out.println("Enter your personal id :");
            String id = input.next();
            boolean result = MySQL.loadEstate(id);
            if (!result)
                System.out.println("Not found");
        }catch(Exception ex){
            ex.getStackTrace();
        }
    }
    static void sellEstate(String personalID)  {
        Estate estate = MySQL.checkEstate(personalID);
        if (estate != null) {
            System.out.println("You are going to sell your estate ");
            System.out.println("Value of your estate is : " + estate.getValue());
            Person person = MySQL.checkPersonId(personalID);
            int value = Integer.parseInt(estate.getValue());
            int wallet = Integer.parseInt(person.getWallet());
            int newWallet = wallet + value;
            person.setWallet(newWallet + "");
            boolean res = MySQL.updatePerson(person);
            if (res) {
                System.out.println("you got " + value + " in your wallet");
                System.out.println("Rigesterd");
                boolean result = MySQL.deleteEstate(estate.getId());
                if (result) {
                    System.out.println("Estate deleted!");
                } else
                    System.out.println("Error");
            } else {
                System.out.println("Error");
            }
        }
        else
            System.out.println("You don't have any estate");

    }
}
