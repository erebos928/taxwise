package org.example;
import com.taxwise.view.TaxwiseUI;
//Point d'entrée principal de l'application.

public class Main {
    public static void main(String[] args) {
        TaxwiseUI taxwiseUI = new TaxwiseUI();
        while(true) {
            taxwiseUI.run();
        }
    }
}