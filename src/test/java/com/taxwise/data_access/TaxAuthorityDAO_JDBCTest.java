package com.taxwise.data_access;

import com.taxwise.model.TaxAuthority;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaxAuthorityDAO_JDBCTest {

    @Test
    void findByLabel() {
        TaxAuthorityDAO_JDBC dao = new TaxAuthorityDAO_JDBC();
        TaxAuthority federal = dao.findByLabel("Federal");
        assertNotNull(federal);
        assertEquals(15000,federal.getTaxFreeThreshold());
    }
    @Test
    void findByLabel_fail(){
        TaxAuthorityDAO_JDBC dao = new TaxAuthorityDAO_JDBC();
        TaxAuthority federal = dao.findByLabel("Medium");
        assertNull(federal);
    }
}