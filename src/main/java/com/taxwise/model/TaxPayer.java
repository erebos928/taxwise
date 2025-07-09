package com.taxwise.model;

public class TaxPayer {
        private final int id;
        private final String name;
        private final int sin; // numéro d'assurance sociale

        public TaxPayer(int id, String name, int sin) {
            this.id = id;
            this.name = name;
            this.sin = sin;
        }

        public String getName() {
            return name;
        }

        public int getSin() {
            return sin;
        }

    public int getId() {
            return id;
    }
}
