package com.taxwise.control;

import com.taxwise.data_access.ITaxBracketDAO;
import com.taxwise.model.TaxBracket;
import com.taxwise.model.TaxReport;
import java.util.List;

//Contrôleur principal chargé du calcul des impôts et de la génération de rapports.

public class TaxCalculator {
    private ITaxBracketDAO dao;

    //Constructeur utilisant l'interface, pas l'implémentation concrète
    public TaxCalculator(ITaxBracketDAO dao) {
        this.dao = dao;
    }

//Calcule le montant d'impôt à payer pour une autorité et un revenu donnés.
//authority nom de l'autorité (ex: "Canada", "Quebec")
//income revenu annuel du contribuable
//return montant d'impôt calculé

    public double calculateTax(String authority, double income) {
        double threshold = dao.getTaxFreeThreshold(authority); // seuil d'exemption
        double taxableIncome = Math.max(0, income - threshold); // revenu imposable
        double tax = 0;

        // Boucle sur les tranches d'imposition
        List<TaxBracket> brackets = dao.findAllBrackets(authority);
        for (TaxBracket bracket : brackets) {
            if (taxableIncome > bracket.getMinIncome()) {
                double upperBound = Math.min(taxableIncome, bracket.getMaxIncome());
                tax += (upperBound - bracket.getMinIncome()) * bracket.getTaxRate();
            }
        }

        return tax;
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



