package com.example.miniuber;

public class TimeCalculator implements Calculator {
    public double calculate(int fromIndex, int toIndex) {
        return Math.abs(fromIndex - toIndex) * 10;
    }
}
