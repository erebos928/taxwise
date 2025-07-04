package com.taxwise.data_access;
package com.taxwise.data_access;

import com.taxwise.model.TaxBracket;
import java.util.List;

//Interface DAO pour abstraction des tranches fiscales.

public interface TaxBracketDAO {
    List<TaxBracket> findAllBrackets(String authority);
    double getTaxFreeThreshold(String authority);
}