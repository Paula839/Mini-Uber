package com.example.miniuber;

public class CashPaymentStrategy implements PaymentStrategy{
    public void pay(double amount) {
    System.out.println("You have paid cash "+amount+" $ successfully.");///// UI
    }
}
