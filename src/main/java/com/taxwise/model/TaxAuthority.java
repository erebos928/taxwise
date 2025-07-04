package com.taxwise.model;

import java.util.List;

public class TaxAuthority {
    private String label;
    private double taxFreeThreshold;
    private List<TaxBracket> taxBrackets;

    public TaxAuthority(String label, double taxFreeThreshold, List<TaxBracket> taxBrackets) {
        this.label = label;
        this.taxFreeThreshold = taxFreeThreshold;
        this.taxBrackets = taxBrackets;
    }

    public String getLabel() {
        return label;
    }

    public double getTaxFreeThreshold() {
        return taxFreeThreshold;
    }

    public List<TaxBracket> getTaxBrackets() {
        return taxBrackets;
    }
    List<TaxBracket> rates;
}
