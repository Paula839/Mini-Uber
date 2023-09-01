package com.example.miniuber;

public class CostCalculator implements Calculator {
    private double costPerMile = 5;
    private double costPerMin = 0.3;
    private double bookingFee = 5;

    public double calculate(int fromIndex, int toIndex) {
        return (new DistanceCalculator().calculate(fromIndex, toIndex) * costPerMile)
                + (new TimeCalculator().calculate(fromIndex, toIndex) * costPerMin)
                + bookingFee;
    }
}
