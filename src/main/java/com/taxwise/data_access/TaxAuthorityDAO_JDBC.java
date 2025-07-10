package com.taxwise.data_access;

import com.taxwise.model.TaxAuthority;

import java.sql.*;

public class TaxAuthorityDAO_JDBC implements ITaxAuthorityDao{

    @Override
    public TaxAuthority findByLabel(String label) {
        try(Connection connection = DriverManager.getConnection(DbSpecs.url,
                DbSpecs.user,
                DbSpecs.password))
        {
            String sql = getQueryString();
            PreparedStatement preparedStatement = getPreparedStatement(sql,connection);
            preparedStatement.setString(1,label);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                int id = resultSet.getInt("id");
                double taxFreeThreshold = resultSet.getDouble("taxFreeThreshold");
                return new TaxAuthority(id,label,taxFreeThreshold,null);
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
        return
                "SELECT * FROM TaxAuthority A " +
                        "WHERE A.label = ?";
    }
}
