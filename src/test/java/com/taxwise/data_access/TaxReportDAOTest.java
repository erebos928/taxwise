package com.taxwise.data_access;

import com.taxwise.model.TaxAuthority;
import com.taxwise.model.TaxPayer;
import com.taxwise.model.TaxReport;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaxReportDAO_JDBCTest {

    @Test
    void save() {
        TaxReportDAO_JDBC dao = new TaxReportDAO_JDBC();
        TaxReport report = new TaxReport(2021,55000,3400);
        report.setPayer(new TaxPayer(2,"Bob Martin",987654321));
        report.setAuthority(new TaxAuthority(4,"Alberta",20000,null));
        dao.save(report);
        assertTrue(dao.reportExists(report));
        dao.deleteReport(report);
        assertFalse(dao.reportExists(report));
    }
}