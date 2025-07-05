package com.taxwise.data_access;
import com.taxwise.model.TaxBracket;
import java.util.List;

//Interface définissant l'accès aux tranches fiscales et seuils d'exemption.

public interface ITaxBracketDAO {
    List<TaxBracket> findAllBrackets(String authority);
    double getTaxFreeThreshold(String authority);
}