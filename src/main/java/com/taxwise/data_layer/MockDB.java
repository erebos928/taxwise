package com.taxwise.data_layer;

import com.taxwise.model.TaxBracket;
import java.util.HashMap;
import java.util.List;

public class MockDB {

        //TaxBracket(double minIncome, double maxIncome, double taxRate)
        //Taux d'imposition Canada par tranches pour 2025 voir Package DiagrammeInformation
        // Le seuil de revenu de base non imposable pour Canada 15 705 $ (Université de Sherbrooke) et Quebec 17000 (Estimation sur google)

        private static final List<TaxBracket> canadaBrackets = List.of(
                new TaxBracket(0, 57375, 0.15),
                new TaxBracket(57375, 114750, 0.205),
                new TaxBracket(114750, 177882, 0.26),
                new TaxBracket(177882, 253414 , 0.29),
                new TaxBracket(253414, Double.MAX_VALUE, 0.33)
        );
        double canadaseuil = 15000.0;

        // Définition des tranches pour le Québec voir Package DiagrammeInformation
        ////Taux d'imposition Canada par tranches pour 2025
        private static final List<TaxBracket> quebecBrackets = List.of(
                new TaxBracket(0, 53255, 0.14),
                new TaxBracket(53255, 106495, 0.19),
                new TaxBracket(106495, 129590, 0.24),
                new TaxBracket(129590, Double.MAX_VALUE, 0.2575)
        );
        double quebecseuil = 17000.0;
    }
    // Retourne les tranches d’une autorité
    public static List<TaxBracket> getBrackets(String authority) {
        if (authority.equals("Canada")) return canadaBrackets;
        else if (authority.equals("Quebec")) return quebecBrackets;
        else return List.of(); // vide par défaut
    }
    }
    // Retourne le seuil de base d’une autorité
    public static double getseuil(String authority) {
        if (authority.equals("Canada")) return canadaseuil;
        else if (authority.equals("Quebec")) return quebecseuil;
        else return 0.0;
    }
}






}
