package com.example.miniuber;

public class DistanceCalculator implements Calculator {
    public double calculate(int fromIndex, int toIndex) {
        return Math.abs(fromIndex - toIndex);
    }
}
