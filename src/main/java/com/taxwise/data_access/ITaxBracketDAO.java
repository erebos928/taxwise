package com.taxwise.data_access;

//Interface DAO pour abstraction des tranches fiscales.

import com.taxwise.model.TaxBracket;

import java.util.List;

public interface ITaxBracketDAO {
    List<TaxBracket> findAllBrackets(String authority);
    double getTaxFreeThreshold(String authority);
}