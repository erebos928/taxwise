package com.taxwise.data_access;

import com.taxwise.model.TaxBracket;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaxBracketDAO_JDBC implements ITaxBracketDAO{
    private static Connection connection;
    @Override
    public List<TaxBracket> findAllBrackets(String authority) {
        final String query = getBracketsQuerySqlString();
        try(Connection connection = DriverManager.getConnection(DbSpecs.url,
                DbSpecs.user,
                DbSpecs.password))
            {
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

    private static String getBracketsQuerySqlString() {
        return "SELECT * FROM TaxBracket B JOIN TaxAuthority A on B.authority_id=A.id WHERE A.label = ?";
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
        final String query = getQuerySqlString();
        try(Connection connection = DriverManager.getConnection(DbSpecs.url,
                DbSpecs.user,
                DbSpecs.password))
        {
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

    private static String getQuerySqlString() {
        return "SELECT A.taxFreeThreshold FROM TaxAuthority A WHERE A.label = ?";
    }
}
