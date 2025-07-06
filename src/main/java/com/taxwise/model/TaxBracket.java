package com.taxwise.model;

public class TaxBracket {
    private final int id;
    private final int authority_id;
    private final double minIncome;
    private final double maxIncome;
    private final double taxRate;


    public TaxBracket(int id, int authority_id, double minIncome, double maxIncome, double taxRate) {
        this.id = id;
        this.authority_id = authority_id;
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

    @Override
    public String toString() {
        return "TaxBracket{" +
                "id=" + id +
                ", authority_id=" + authority_id +
                ", minIncome=" + minIncome +
                ", maxIncome=" + maxIncome +
                ", taxRate=" + taxRate +
                '}';
    }
}
