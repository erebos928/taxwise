package com.taxwise.control;

import com.taxwise.data_access.TaxBracketDAO_JDBC;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaxCalculatorDbTest {
    TaxCalculator calculator = new TaxCalculator(new TaxBracketDAO_JDBC());
    @Test
    void calculateTaxUnderThreshold() {
        double income = 13000;
        double expected = 0;
        assertEquals(expected,calculator.calculateTax("Quebec",income));
    }
    @Test
    void calculateTaxInFirstBracket() {
        double income = 42000;
        double expected = 42000 * .15;
        assertEquals(expected,calculator.calculateTax("Quebec",income));
    }
    @Test
    void calculateTaxInSecondBracket() {
        double income = 80000;
        double expected = 43790 * .15 + (80000 - 43791) * .2;
        assertEquals(expected,calculator.calculateTax("Quebec",income));
    }

}