package com.taxwise.data_access;

import com.taxwise.model.TaxAuthority;

public interface ITaxAuthorityDao {
    TaxAuthority findByLabel(String label);
}
