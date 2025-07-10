package com.taxwise.view;

import com.taxwise.control.TaxCalculator;
import com.taxwise.data_access.*;
import com.taxwise.model.TaxAuthority;
import com.taxwise.model.TaxPayer;
import com.taxwise.model.TaxReport;
import java.util.Scanner;

//Interface utilisateur console permettant de saisir les données et d'afficher le rapport.

public class TaxwiseUI {
    ITaxBracketDAO bracketDao;
    ITaxAuthorityDao authorityDao;
    ITaxReportDAO reportDAO;
    ITaxPayerDAO payerDAO;

    public void run() {
        Scanner scanner = new Scanner(System.in);
        String datasource = chooseDataSource(scanner);
        configureDAOs(datasource);
        TaxAuthority authority = determineAuthority(scanner);
        TaxPayer payer = determinePayer(scanner);
        double income = getIncome(scanner);
        int year = getYear(scanner);

        TaxCalculator calculator = new TaxCalculator(bracketDao);
        TaxReport report = calculator.fileReport(payer.getSin(), income, year, authority.getLabel());
        report.setAuthority(authority);
        report.setPayer(payer);
        System.out.println("Declaration :");
        System.out.println(report);
        reportDAO.save(report);
    }

    private static int getYear(Scanner scanner) {
        do {
            try {
                System.out.print("Enter your fiscal year 1) 2023, 2) 2024 3) 2025: ");
                int choice = scanner.nextInt();
                switch (choice){
                    case(1):
                        return 2023;
                    case(2):
                        return 2024;
                    case(3):
                        return 2025;
                    default:
                        System.out.println("Invalid choice");
                }
            }catch(Exception e){
                System.out.println("Invalid input");
                scanner.nextLine();
            }
        }while(true);
    }

    private static double getIncome(Scanner scanner) {
        do {
            try {
                System.out.print("Enter your annual income : ");
                return scanner.nextDouble();
            }catch (Exception e){
                System.out.println("Invalid input");
            }
        }while(true);
    }

    private TaxPayer determinePayer(Scanner scanner) {
        TaxPayer payer;
        do {
            System.out.print("Enter sin number (123456 | 123450 | 123400 | 123000 | 120000) : ");
            int sin = scanner.nextInt();
            payer = payerDAO.findBySIN(sin);
            if (payer == null)
                System.out.println("No such a payer found in the system.");
        }while(payer == null);
        return payer;

    }

    private void configureDAOs(String datasource) {
        switch (datasource){
            case ("MockDB"):{
                this.authorityDao = new TaxAuthorityDAO_MockDB();
                this.reportDAO = new TaxReportDAO_MockDB();
                this.bracketDao = new TaxBracketDAO_MockDB();
                this.payerDAO = new TaxPayerDAO_MockDB();
                break;
            }
            case ("DB"):{
                this.authorityDao = new TaxAuthorityDAO_JDBC();
                this.bracketDao = new TaxBracketDAO_JDBC();
                this.reportDAO = new TaxReportDAO_JDBC();
                this.payerDAO = new TaxPayerDAO_JDBC();
            }
        }
    }

    private String chooseDataSource(Scanner scanner) {
        do {
            System.out.print("Enter datasource 1. In memory, 2. Database : ");
            int choice = scanner.nextInt();
            if (choice == 1 || choice == 2)
                return choice ==1 ? "MockDB" : "DB";
        }while(true);
    }

    private TaxAuthority determineAuthority(Scanner scanner) {
        scanner.nextLine();
        TaxAuthority authority = null;
        do {
            System.out.print("Enter an authority: 1) Federal  2) Quebec 3) Ontario ");
            int choice = scanner.nextInt();
            if (choice < 1 || choice >3)
            {
                System.out.println("Invalid choice");
                continue;
            }
            String label = "";
            switch (choice){
                case(1):
                    label = "Federal";
                    break;
                case(2):
                    label = "Quebec";
                    break;
                case(3):
                    label = "Ontario";
                    break;
            }
            authority = authorityDao.findByLabel(label);
            if (authority == null)
                System.out.println("No such an authority found in the system.");
        }while(authority == null);
        return authority;
    }
}
