package com.taxwise.control;

import com.taxwise.data_access.TaxBracketDAO_MockDB;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaxCalculatorTest {
    TaxCalculator taxCalculator = new TaxCalculator(new TaxBracketDAO_MockDB());
    @Test
    void calculateTaxFederalLessThan15000() {
        double income = 14900;
        double expected = 0;
        double calculatedTax = taxCalculator.calculateTax("Federal", income);
        assertEquals(expected,calculatedTax);
    }
    @Test
    void calculateTaxFederalEqualsTheBeginningOfBracket() {
        double income = 15001;
        double expected = 0.0;
        double calculatedTax = taxCalculator.calculateTax("Federal", income);
        assertEquals(expected,calculatedTax);
    }
    @Test
    void calculateTaxFederalInsideBracket() {
        double income = 16000;
        double expected = 99.9;
        double calculatedTax = taxCalculator.calculateTax("Federal", income);
        assertEquals(expected,calculatedTax);
    }
    @Test
    void calculateTaxFederalInsideSecondBracket() {
        double income = 19000;
        double expected = 449.75;
        double calculatedTax = taxCalculator.calculateTax("Federal", income);
        assertEquals(expected,calculatedTax);
    }
    @Test
    void calculateTaxFederalMoreThanMaximumIncome() {
        double income = 30000;
        double expected = 2249.25;
        double calculatedTax = taxCalculator.calculateTax("Federal", income);
        assertEquals(expected,calculatedTax);
    }
    @Test
    void calculateTaxMoreThanMaximumIncome() {
        double income = 30000;
        double expected = 2249.25;
        double calculatedTax = taxCalculator.calculateTax("Federal", income);
        assertEquals(expected,calculatedTax);
    }

}