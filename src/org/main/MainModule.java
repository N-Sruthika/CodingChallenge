package org.main;

import java.util.List;
import java.util.Scanner;
import org.dao.ILoanRepository;
import org.dao.ILoanRepositoryImpl;
import org.entity.*;
import org.exception.InvalidLoanException;

public class MainModule {

    private static ILoanRepository loanRepo = new ILoanRepositoryImpl();  // Loan Repository
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Menu-driven interface for loan management
        while (true) {
            System.out.println("\n------ Loan Management System ------");
            System.out.println("1. Apply Loan");
            System.out.println("2. Get All Loans");
            System.out.println("3. Get Loan By ID");
            System.out.println("4. Loan Repayment");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            
            int choice = scanner.nextInt();  // User input for choice

            switch (choice) {
                case 1:
                    applyLoan();  // Apply a new loan
                    break;
                case 2:
                    getAllLoans();  // View all loans
                    break;
                case 3:
                    getLoanById();  // Get loan by ID
                    break;
                case 4:
                    loanRepayment();  // Make a loan repayment
                    break;
                case 5:
                    System.out.println("Exiting Loan Management System.");
                    System.exit(0);  // Exit the program
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }

    // Method to apply for a new loan
    private static void applyLoan() {
        System.out.println("\n--- Apply Loan ---");

        // Prompt user for loan details
        System.out.print("Enter Loan ID: ");
        int loanId = scanner.nextInt();

        System.out.print("Enter Customer ID: ");
        int customerId = scanner.nextInt();

        System.out.print("Enter Customer Name: ");
        scanner.nextLine();  // Consume newline
        String name = scanner.nextLine();

        System.out.print("Enter Principal Amount: ");
        double principalAmount = scanner.nextDouble();

        System.out.print("Enter Interest Rate: ");
        double interestRate = scanner.nextDouble();

        System.out.print("Enter Loan Term (months): ");
        int loanTerm = scanner.nextInt();

        System.out.print("Enter Loan Status (Pending/Approved): ");
        scanner.nextLine();  // Consume newline
        String loanStatus = scanner.nextLine();

        // Create Customer and Loan objects
        Customer customer = new Customer(customerId, name, "dummyEmail", "dummyPhone", "dummyAddress", 700);
        Loan loan = new Loan(loanId, customer, principalAmount, interestRate, loanTerm, loanStatus);

        try {
            // Apply the loan using the repository
            loanRepo.applyLoan(loan);
            System.out.println("Loan successfully applied: " + loan);
        } catch (InvalidLoanException e) {
            System.err.println("Error applying loan: " + e.getMessage());
        }
    }

    // Method to display all loans
    private static void getAllLoans() {
        System.out.println("\n--- Get All Loans ---");

        try {
            List<Loan> loans = loanRepo.getAllLoan();
            if (loans.isEmpty()) {
                System.out.println("No loans found.");
            } else {
                for (Loan loan : loans) {
                    System.out.println(loan);
                }
            }
        } catch (Exception e) {
            System.err.println("Error retrieving loans: " + e.getMessage());
        }
    }

    // Method to get loan details by ID
    private static void getLoanById() {
        System.out.println("\n--- Get Loan By ID ---");
        System.out.print("Enter Loan ID: ");
        int loanId = scanner.nextInt();

        try {
            Loan loan = loanRepo.getLoanById(loanId);
            System.out.println("Loan details: " + loan);
        } catch (InvalidLoanException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    // Method to make a loan repayment
    private static void loanRepayment() {
        System.out.println("\n--- Loan Repayment ---");
        System.out.print("Enter Loan ID: ");
        int loanId = scanner.nextInt();

        System.out.print("Enter Repayment Amount: ");
        double amount = scanner.nextDouble();

        try {
            loanRepo.loanRepayment(loanId, amount);
        } catch (InvalidLoanException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
