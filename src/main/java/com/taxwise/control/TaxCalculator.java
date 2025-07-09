package com.taxwise.control;

import com.taxwise.data_access.ITaxBracketDAO;
import com.taxwise.model.TaxBracket;
import com.taxwise.model.TaxReport;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//Contrôleur principal chargé du calcul des impôts et de la génération de rapports.

public class TaxCalculator {
    private final ITaxBracketDAO dao;

    //Constructeur utilisant l'interface, pas l'implémentation concrète
    public TaxCalculator(ITaxBracketDAO dao) {
        this.dao = dao;
    }

//Calcule le montant d'impôt à payer pour une autorité et un revenu donnés.
//authority nom de l'autorité (ex: "Ontario", "Quebec")
//income revenu annuel du contribuable
//return montant d'impôt calculé

    public double calculateTax(String authority, double income) {
        double tax = 0;
        if (income < dao.getTaxFreeThreshold(authority))
            return 0;
        // Boucle sur les tranches d'imposition
        List<TaxBracket> brackets = dao.findAllBrackets(authority);
        for (TaxBracket bracket : brackets) {
            tax += applyBracket(income, bracket);
        }
        return tax;
    }

    private double applyBracket(double income, TaxBracket bracket) {
        if (income < bracket.getMinIncome()) {
            //falls totally below the bracket
            // bracket does not apply to the income
            return 0;
        }
        if (income >= bracket.getMinIncome() && income <= bracket.getMaxIncome()) {
            // falls inside the bracket
            return getPartialInterval(bracket,income) * bracket.getTaxRate() / 100;
        }
        if (income > bracket.getMaxIncome()) {
            // covers all the bracket
            return getTotalInterval(bracket) * bracket.getTaxRate() / 100;
        }
        return 0;
    }

    private double getPartialInterval(TaxBracket bracket, double income) {
        return income - bracket.getMinIncome();
    }

    private double getTotalInterval(TaxBracket bracket) {
        return bracket.getMaxIncome() - bracket.getMinIncome();
    }

//Crée un rapport d'impôt pour un contribuable.
//sin numéro d'assurance sociale
//income revenu annuel
//year année fiscale
//authority autorité fiscale ("Canada", "Quebec")
// return TaxReport contenant les détails du calcul

    public TaxReport fileReport(int sin, double income, int year, String authority) {

        double tax = calculateTax(authority, income);
        return new TaxReport(year, income, tax);
    }
}



