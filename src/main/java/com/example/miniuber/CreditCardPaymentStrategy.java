package com.example.miniuber;

public class CreditCardPaymentStrategy implements PaymentStrategy{
    public void pay(double amount){
        System.out.println("You have paid credit "+amount+" $ successfully.");//////UI
    }
}
