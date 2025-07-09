package com.taxwise.data_access;

import com.taxwise.data_layer.MockDB;
import com.taxwise.model.TaxReport;

import java.util.List;

public class TaxReportDAO_MockDB implements ITaxReportDAO{

    @Override
    public void save(TaxReport report) {
        MockDB.addReport(report);
    }
    public void delete(TaxReport report){
        TaxReport found = findReport(report);
        if (found != null)
            MockDB.getReports().remove(found);
    }
    protected boolean reportExists(TaxReport report){
        return findReport(report) != null;
    }

    private static TaxReport findReport(TaxReport report) {
        List<TaxReport> reports = MockDB.getReports();
        for (TaxReport item: reports){
            if (item.getId() == report.getId())
                return item;
        }
        return null;
    }
}
