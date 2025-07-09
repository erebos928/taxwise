package com.taxwise.data_access;

import com.taxwise.model.TaxReport;

public interface ITaxReportDAO {
    void save(TaxReport report);
}
