package com.taxwise.data_access;

import com.taxwise.model.TaxPayer;

public interface ITaxPayerDAO {
    TaxPayer findBySIN(int sin);
}
