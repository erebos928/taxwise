package com.taxwise.model;

public class TaxBracket {
    private final double minIncome;
    private final double maxIncome;
    private final double taxRate;

    public TaxBracket(double minIncome, double maxIncome, double taxRate) {
        this.minIncome = minIncome;
        this.maxIncome = maxIncome;
        this.taxRate = taxRate;
    }

    public double getMinIncome() {
        return minIncome;
    }

    public double getMaxIncome() {
        return maxIncome;
    }

    public double getTaxRate() {
        return taxRate;
    }
}
