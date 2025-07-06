package com.taxwise.model;
//Représente une déclaration d'impôt produite pour un contribuable.

public class TaxReport {
    private int id;
    private int year;     // Année fiscale
    private double income; // Revenu déclaré
    private double tax;   // Montant d'impôt calculé

    public TaxReport(int year, double income, double tax) {
        this.year = year;
        this.income = income;
        this.tax = tax;
    }

    public String toString() {
        return "Année: " + year + ", Revenu: " + income + ", Impôt: " + tax;
    }

    public double getTax() { return tax; }
}



