package com.taxwise.data_access;

import com.taxwise.model.TaxBracket;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TaxBracketDAO_JDBCTest {
    TaxBracketDAO_JDBC taxBracketDAOJdbc = new TaxBracketDAO_JDBC();
    @Test
    void findAllBrackets() {
        List<TaxBracket> brackets = taxBracketDAOJdbc.findAllBrackets("Ontario");
        assertNotNull(brackets);
        brackets.stream().forEach(System.out::println);

    }
}