package com.company.MyException;

public class NotEnoughMoney extends Exception{
    public NotEnoughMoney(String message) {
        super(message);
    }
}
