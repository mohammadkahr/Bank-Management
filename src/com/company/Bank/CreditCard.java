package com.company.Bank;

public class CreditCard {
    private String name;
    private double number;
    private String cvv2 ;
    private String dateExpiration ;

    //constructor
    public CreditCard(String name, double number, String cvv2, String dateExpiration) {
        this.name = name;
        this.number = number;
        this.cvv2 = cvv2;
        this.dateExpiration = dateExpiration;
    }

    //setters
    public void setName(String name) {
        this.name = name;
    }
    public void setNumber(double number) {
        this.number = number;
    }
    public void setCvv2(String cvv2) {
        this.cvv2 = cvv2;
    }
    public void setDateExpiration(String dateExpiration) {
        this.dateExpiration = dateExpiration;
    }

    //getters
    public String getName() {
        return name;
    }
    public double getNumber() {
        return number;
    }
    public String getCvv2() {
        return cvv2;
    }
    public String getDateExpiration() {
        return dateExpiration;
    }

    //methods:



}
