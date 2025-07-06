package com.taxwise.data_access;

import com.taxwise.model.TaxBracket;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaxBracketDAO_JDBC implements ITaxBracketDAO{
    private final String dbUrl = "jdbc:mysql://mysql-behrooz.alwaysdata.net:3306/behrooz_taxwise";
    private final String user = "behrooz";
    private final String password = "qualityassurance395";
    private static Connection connection;
    static{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public List<TaxBracket> findAllBrackets(String authority) {
        final String query = "SELECT * FROM TaxBracket B JOIN TaxAuthority A on B.authority_id=A.id WHERE A.label = ?";
        try(Connection connection = DriverManager.getConnection(dbUrl,user,password)) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, authority);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<TaxBracket> brackets = extractBrackets(resultSet);
            resultSet.close();
            preparedStatement.close();
            return brackets;
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    private List<TaxBracket> extractBrackets(ResultSet resultSet) {
        List<TaxBracket> list = new ArrayList<>();
        try {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int authority_id = resultSet.getInt("authority_id");
                double minIncome = resultSet.getDouble("minIncome");
                double maxIncome = resultSet.getDouble("maxIncome");
                double taxRate = resultSet.getDouble("taxRate");
                list.add(new TaxBracket(id, authority_id, minIncome, maxIncome, taxRate));
            }
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public double getTaxFreeThreshold(String authority) {
        final String query = "SELECT A.taxFreeThreshold FROM TaxAuthority A WHERE A.label = ?";
        try(Connection connection = DriverManager.getConnection(dbUrl,user,password)) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, authority);
            ResultSet resultSet = preparedStatement.executeQuery();
            double threshold;
            if (resultSet.next())
              threshold = resultSet.getDouble(1);
            else
                throw new RuntimeException(String.format("No threshold found for authority: %s", authority));
            resultSet.close();
            preparedStatement.close();
            return threshold;
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
}
