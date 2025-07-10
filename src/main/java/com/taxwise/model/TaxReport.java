package com.taxwise.model;
//Représente une déclaration d'impôt produite pour un contribuable.

public class TaxReport {
    private final int id;
    private final int year;     // Année fiscale
    private final double income; // Revenu déclaré
    private final double tax;   // Montant d'impôt calculé
    private TaxPayer payer;
    private TaxAuthority authority;
    public TaxReport(int year, double income, double tax) {
        id = -1;//used for im memory storage
        this.year = year;
        this.income = income;
        this.tax = tax;
    }

    public TaxReport(int id, int year, double income, double tax) {
        this.id = id;
        this.year = year;
        this.income = income;
        this.tax = tax;
    }

    public String toString() {
        return "Year: " + year + ", Income: " + income + ", Tax: " + String.format("%.2f",tax);
    }

    public double getTax() { return tax; }

    public TaxAuthority getAuthority() {
        return authority;
    }

    public void setAuthority(TaxAuthority authority) {
        this.authority = authority;
    }

    public TaxPayer getPayer() {
        return payer;
    }

    public void setPayer(TaxPayer payer) {
        this.payer = payer;
    }

    public int getId() {
        return id;
    }

    public int getYear() {
        return year;
    }

    public double getIncome() {
        return income;
    }
}



