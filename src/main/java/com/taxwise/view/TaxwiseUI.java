package com.taxwise.view;

import com.taxwise.control.TaxCalculator;
import com.taxwise.data_access.TaxBracketDAO_MockDB;
import com.taxwise.model.TaxReport;
import java.util.Scanner;

//Interface utilisateur console permettant de saisir les données et d'afficher le rapport.

public class TaxwiseUI {
    public void run() {
        Scanner scanner = new Scanner(System.in);

        // Saisie utilisateur
        System.out.print("Entrez votre NAS : ");
        int sin = scanner.nextInt();

        System.out.print("Entrez votre revenu annuel : ");
        double income = scanner.nextDouble();

        System.out.print("Entrez l'année fiscale : ");
        int year = scanner.nextInt();
        scanner.nextLine(); // consommer le retour à la ligne

        System.out.print("Entrez l'autorité fiscale (Canada ou Quebec) : ");
        String authority = scanner.nextLine();

        // Traitement et résultat
        TaxCalculator calculator = new TaxCalculator(new TaxBracketDAO_MockDB());
        TaxReport report = calculator.fileReport(sin, income, year, authority);

        // Affichage du rapport
        System.out.println("\nDéclaration d'impôt :");
        System.out.println(report);
    }
}
