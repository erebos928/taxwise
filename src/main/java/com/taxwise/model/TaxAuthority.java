package com.taxwise.model;

import java.util.List;

public class TaxAuthority {
    private final int id;
    private final String label;
    private final double taxFreeThreshold;
    private final List<TaxBracket> taxBrackets;

    public TaxAuthority(int id, String label, double taxFreeThreshold, List<TaxBracket> taxBrackets) {
        this.id = id;
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

    public int getId() {
        return id;
    }
}
