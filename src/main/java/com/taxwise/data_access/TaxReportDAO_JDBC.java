package com.taxwise.data_access;

import com.taxwise.model.TaxAuthority;
import com.taxwise.model.TaxPayer;
import com.taxwise.model.TaxReport;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaxReportDAO_JDBC implements ITaxReportDAO{
    @Override
    public void save(TaxReport report) {
        if (reportExists(report))
            updateReport(report);
        else
            insertReport(report);
    }

    @Override
    public List<TaxReport> findAll() {
        String sql = getSelectSqlString();
        try(Connection connection = DriverManager.getConnection(DbSpecs.url,DbSpecs.user,DbSpecs.password)) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            return extract(resultSet);
        }catch(Exception e){
            throw new RuntimeException(e);
        }


    }

    private String getSelectSqlString() {
        return "SELECT * FROM TaxReport JOIN TaxPayer on TaxReport.payer_id = TaxPayer.id JOIN TaxAuthority on TaxAuthority.id = TaxReport.authority_id";
    }

    private List<TaxReport> extract(ResultSet resultSet) throws SQLException {
        List<TaxReport> storage = new ArrayList<>();
        while (resultSet.next()){
            int year = resultSet.getInt("year");
            double income = resultSet.getDouble("income");
            double tax = resultSet.getDouble("tax");
            String label = resultSet.getString("label");
            int sin = resultSet.getInt("sin");
            String name = resultSet.getString("name");
            int id = resultSet.getInt("id");
            TaxAuthority authority = new TaxAuthority(-1,label,-1,null);
            TaxPayer payer = new TaxPayer(-1,name,sin);
            TaxReport taxReport = new TaxReport(id, year, income, tax);
            taxReport.setPayer(payer);
            taxReport.setAuthority(authority);
            storage.add(taxReport);
        }
        return storage;
    }

    private void insertReport(TaxReport report) {
        String sql = getInsertSqlString();
        try(Connection connection = DriverManager.getConnection(DbSpecs.url,DbSpecs.user,DbSpecs.password)) {
            PreparedStatement preparedStatement = getPreparedStatement(connection, sql);
            fillInsertStatement(report, preparedStatement);
            preparedStatement.execute();
            System.out.println("One row added to the database");
        }catch(Exception e){
            throw new RuntimeException(e);
        }

    }

    private void fillInsertStatement(TaxReport report, PreparedStatement preparedStatement) {
        try {
            preparedStatement.setDouble(5, report.getIncome());
            preparedStatement.setDouble(4, report.getTax());
            preparedStatement.setInt(1, report.getAuthority().getId());
            preparedStatement.setInt(2, report.getPayer().getId());
            preparedStatement.setInt(3, report.getYear());
        }catch(Exception e){
            throw new RuntimeException(e);
        }

    }

    private String getInsertSqlString() {
        return "INSERT INTO TaxReport (authority_id, payer_id, year, tax, income) " +
                "VALUE (?,?,?,?,?)";
    }

    private void updateReport(TaxReport report) {
        String sql = getUpdateSqlString();
        try(Connection connection = DriverManager.getConnection(DbSpecs.url,DbSpecs.user,DbSpecs.password)) {
            PreparedStatement preparedStatement = getPreparedStatement(connection, sql);
            fillUpdateStatement(report, preparedStatement);
            preparedStatement.executeUpdate();
            System.out.println("One row is updated in database");
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    private static String getUpdateSqlString() {
        return "UPDATE TaxReport R SET R.year = ?, R.income = ? ,R.tax = ?" +
                        " WHERE R.authority_id = ? AND R.payer_id = ? AND year = ?";
    }

    private void fillUpdateStatement(TaxReport report, PreparedStatement preparedStatement) {
        try {
            preparedStatement.setInt(1, report.getYear());
            preparedStatement.setDouble(2, report.getIncome());
            preparedStatement.setDouble(3, report.getTax());
            preparedStatement.setInt(4, report.getAuthority().getId());
            preparedStatement.setInt(5, report.getPayer().getId());
            preparedStatement.setInt(6, report.getYear());
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    protected boolean reportExists(TaxReport report) {
        String sql = getQuerySqlString();
        try(Connection connection = DriverManager.getConnection(DbSpecs.url,DbSpecs.user,DbSpecs.password)){
            PreparedStatement preparedStatement = getPreparedStatement(connection, sql);
            fillQueryStatement(report, preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static String getQuerySqlString() {
        return "SELECT * FROM TaxReport R " +
                        "WHERE R.authority_id = ? AND R.payer_id = ? AND year = ?";
    }

    private static PreparedStatement getPreparedStatement(Connection connection, String sql) throws SQLException {
        return connection.prepareStatement(sql);
    }

    private static void fillQueryStatement(TaxReport report, PreparedStatement preparedStatement) throws SQLException {
        if (report.getAuthority() != null)
            preparedStatement.setInt(1, report.getAuthority().getId());
        else
            throw new RuntimeException("Report does not have authority.");
        if (report.getPayer() != null)
            preparedStatement.setInt(2, report.getPayer().getId());
        else
            throw new RuntimeException("Report does not have payer.");
        preparedStatement.setInt(3, report.getYear());
    }
    protected void deleteReport(TaxReport report){
        String sql = getDeleteSqlString();
        try(Connection connection = DriverManager.getConnection(DbSpecs.url,DbSpecs.user,DbSpecs.password)) {
            PreparedStatement preparedStatement = getPreparedStatement(connection, sql);
            fillDeleteStatement(report, preparedStatement);
            preparedStatement.execute();
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    private void fillDeleteStatement(TaxReport report, PreparedStatement preparedStatement) {
        try{
        preparedStatement.setInt(1,report.getAuthority().getId());
        preparedStatement.setInt(2,report.getPayer().getId());
        preparedStatement.setInt(3,report.getYear());}
        catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    private String getDeleteSqlString() {
        return "DELETE FROM TaxReport " +
                "WHERE authority_id = ? AND payer_id = ? AND year = ?";
    }
}
