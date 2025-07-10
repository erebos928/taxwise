package com.taxwise.data_access;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;


class DbSpecsTest {
    @Test
    @Disabled
    void clearDatabase(){
        try(Connection connection = DriverManager.getConnection(DbSpecs.url,
                DbSpecs.user,
                DbSpecs.password))
        {
            Statement statement = connection.createStatement();
            String sql = getDeleteSqlString("TaxReport");
            statement.execute(sql);
            sql = getDeleteSqlString("TaxBracket");
            statement.execute(sql);
            sql = getDeleteSqlString("TaxAuthority");
            statement.execute(sql);
            sql = getDeleteSqlString("TaxPayer");
            statement.execute(sql);

        }catch(Exception e){
            System.out.println(e);
        }

}

    private String getDeleteSqlString(String tableName) {
        return String.format("DELETE FROM %s", tableName);
    }
    }