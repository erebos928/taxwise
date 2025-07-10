package com.taxwise.data_access;

import com.taxwise.data_layer.MockDB;
import com.taxwise.model.TaxAuthority;

import java.util.List;

public class TaxAuthorityDAO_MockDB implements ITaxAuthorityDao{

    @Override
    public TaxAuthority findByLabel(String label) {
        List<TaxAuthority> authorities = MockDB.getAuthorities();
        for (TaxAuthority authority: authorities){
            if (authority.getLabel().equals(label))
                return authority;
        }
        return null;
    }
}
