package com.taxwise.model;

public class TaxPayer {

        private String name;
        private int sin; // numéro d'assurance sociale

        public TaxPayer(String name, int sin) {
            this.name = name;
            this.sin = sin;
        }

        public String getName() {
            return name;
        }

        public int getSin() {
            return sin;
        }
}
