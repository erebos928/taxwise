package com.taxwise.data_access;

import com.taxwise.data_layer.MockDB;
import com.taxwise.model.TaxBracket;

import java.util.List;

//Implémentation concrète de l'interface TaxBracketDAO (utilise MockDB).

public class TaxBracketDAO_MockDB implements ITaxBracketDAO {

    // Récupère toutes les tranches fiscales pour une autorité (Canada, Québec)
    @Override
    public List<TaxBracket> findAllBrackets(String authority) {
        return MockDB.getBrackets(authority);
    }

    // Récupère le seuil non imposable pour l'autorité choisie
    @Override
    public double getTaxFreeThreshold(String authority) {
        return MockDB.getThreshold(authority);
    }
}

