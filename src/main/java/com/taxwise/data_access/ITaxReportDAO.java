package com.taxwise.data_access;

import com.taxwise.model.TaxReport;

import java.util.List;

public interface ITaxReportDAO {
    void save(TaxReport report);
    List<TaxReport> findAll();
}
