package com.taxwise.data_access;

import com.taxwise.data_layer.MockDB;
import com.taxwise.model.TaxPayer;

import java.util.List;

public class TaxPayerDAO_MockDB implements ITaxPayerDAO {

    @Override
    public TaxPayer findBySIN(int sin) {
        List<TaxPayer> payers = MockDB.getPayers();
        for (TaxPayer payer:payers){
            if (payer.getSin() == sin)
                return payer;
        }
        return null;
    }
}
