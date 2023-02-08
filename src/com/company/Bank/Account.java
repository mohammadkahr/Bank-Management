package com.company.Bank;

public class Account {
    private String accountId ;
    private String personId ;
    private int bankroll ; //موجودی
    private String date ;
    private int demerit ;//نمره منفی


    //constructor
    public Account(String accountId, String personId, int bankroll, String date, int demerit) {
        this.accountId = accountId;
        this.personId = personId;
        this.bankroll = bankroll;
        this.date = date;
        this.demerit = demerit;
    }

    //setters
    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
    public void setPersonId(String personId) {
        this.personId = personId;
    }
    public void setBankroll(int bankroll) {
        this.bankroll = bankroll;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public void setDemerit(int demerit) {
        this.demerit = demerit;
    }

    //getters
    public String getAccountId() {
        return accountId;
    }
    public String getPersonId() {
        return personId;
    }
    public int getBankroll() {
        return bankroll;
    }
    public String getDate() {
        return date;
    }
    public int getDemerit() {
        return demerit;
    }


    //methods



}
