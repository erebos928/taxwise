package com.taxwise.data_layer;

import com.taxwise.model.TaxAuthority;
import com.taxwise.model.TaxBracket;
import com.taxwise.model.TaxReport;

import java.util.ArrayList;
import java.util.List;

public class MockDB {

    //TaxBracket(double minIncome, double maxIncome, double taxRate)
    //Taux d'imposition Canada par tranches pour 2025 voir Package DiagrammeInformation
    // Le seuil de revenu de base non imposable pour Canada 15 705 $ (Université de Sherbrooke) et Quebec 17000 (Estimation sur google)
    static final double FEDERAL_THRESHOLD = 15000.0;
    static final double QUEBEC_THRESHOLD = 17000.0;
    private final static List<TaxReport> reports = new ArrayList<>();
    private static final List<TaxBracket> FEDERAL_BRACKETS = List.of(
            new TaxBracket(0, 1, 15001, 18000, 10),//
            new TaxBracket(57375, 1, 18001, 20000, 15),
            new TaxBracket(114750, 1, 20001, 25000, 16),
            new TaxBracket(177882, 1, 25001, 27000, 17),
            new TaxBracket(253414, 1, 27001, Double.MAX_VALUE, 17)
    );
    private static final List<TaxBracket> QUEBEC_BRACKETS = List.of(
            new TaxBracket(0, 2, 15001, 18000, 9.5),
            new TaxBracket(57, 2, 18001, 20000, 10.2),
            new TaxBracket(11, 2, 20001, 25000, 11),
            new TaxBracket(17, 2, 25001, 27000, 11.5),
            new TaxBracket(25, 2, 27001, Double.MAX_VALUE, 12)
    );

    private static final List<TaxAuthority> authorities = List.of(
            new TaxAuthority(1, "Federal", FEDERAL_THRESHOLD, FEDERAL_BRACKETS),
            new TaxAuthority(2, "Quebec", QUEBEC_THRESHOLD, QUEBEC_BRACKETS)
    );


    // Retourne les tranches d’une autorité
    public static List<TaxBracket> getBrackets(String authorityLabel) {
        TaxAuthority authority = findAuthority(authorityLabel);
        if (authority != null)
            return authority.getTaxBrackets();
        throw new RuntimeException(String.format("No such authority found: %s", authorityLabel));
    }

    private static TaxAuthority findAuthority(String label) {
        for( TaxAuthority authority : authorities)
            if (label.equals(authority.getLabel()))
                return authority;
        return null;
    }

    // Retourne le seuil de base d’une autorité
    public static double getThreshold(String authorityLabel) {
        TaxAuthority authority = findAuthority(authorityLabel);
        if (authority != null)
            return authority.getTaxFreeThreshold();
        throw new RuntimeException(String.format("No such authority found: %s", authorityLabel));
    }
    public static void addReport(TaxReport report){
        for (TaxReport item: reports){
          if (item.getId() == report.getId()){
              reports.remove(item);
              break;
          }
        }
        reports.add(report);
    }
    public static List<TaxReport> getReports(){
        return reports;
    }
}