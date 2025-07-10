package com.taxwise.data_layer;

import com.taxwise.model.TaxAuthority;
import com.taxwise.model.TaxBracket;
import com.taxwise.model.TaxPayer;
import com.taxwise.model.TaxReport;

import java.util.ArrayList;
import java.util.List;

public class MockDB {
    static double infinity = 9999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999.0;
    private final static List<TaxReport> reports = new ArrayList<>();
    private static final List<TaxAuthority> authorities = List.of(
            new TaxAuthority(1, "Federal", 15000.00, List.of(
                    new TaxBracket(1,1,0,48535,15),
                    new TaxBracket(2,1,48536,97069,20.5),
                    new TaxBracket(3,1,97069,infinity,25)
            )),
            new TaxAuthority(2, "Quebec", 14000.00, List.of(
                    new TaxBracket(1,2,0,43790,15),
                    new TaxBracket(2,2,43791,87575,20),
                    new TaxBracket(3,2,87576,infinity,23)
            )),
            new TaxAuthority(3, "Ontario", 13500.00, List.of(
                    new TaxBracket(1,3,0,44740,10),
                    new TaxBracket(2,3,44741,55000,12),
                    new TaxBracket(3,3,55001,infinity,18)
            )),
        new TaxAuthority(4, "Alberta", 13000.00, List.of()),
        new TaxAuthority(5, "British Columbia", 14500.00, List.of())
    );
    private static final List<TaxPayer> payers = List.of(
            new TaxPayer(1,"Alice Tremblay",123456),
            new TaxPayer(2,"Bob Martin",123450),
            new TaxPayer(3,"Chantal Gagnon",123400),
            new TaxPayer(4,"David Nguyen",123000),
            new TaxPayer(5,"Émilie Roy",120000)
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

    public static List<TaxAuthority> getAuthorities() {
        return authorities;
    }

    public static List<TaxPayer> getPayers() {
        return payers;
    }
}