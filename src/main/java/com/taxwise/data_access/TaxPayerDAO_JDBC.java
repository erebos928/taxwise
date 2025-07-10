package com.taxwise.data_access;

import com.taxwise.model.TaxPayer;

import java.sql.*;

public class TaxPayerDAO_JDBC implements ITaxPayerDAO {
    @Override
    public TaxPayer findBySIN(int sin) {
        try(Connection connection = DriverManager.getConnection(DbSpecs.url,
                DbSpecs.user,
                DbSpecs.password))
        {
            String sql = getQueryString();
            PreparedStatement preparedStatement = getPreparedStatement(sql,connection);
            preparedStatement.setInt(1,sin);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                int id = resultSet.getInt("id");
                double taxFreeThreshold = resultSet.getInt("sin");
                String name = resultSet.getString("name");
                return new TaxPayer(id,name,sin);
            }
            return null;
        }catch(Exception e){
            throw new RuntimeException(e);
        }

    }

    private PreparedStatement getPreparedStatement(String sql, Connection connection) {
        try {
            return connection.prepareStatement(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private String getQueryString() {
        return "SELECT * FROM TaxPayer P " +
                "WHERE P.sin = ?";
    }
}
