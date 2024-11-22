package org.entity;
public class CarLoan extends Loan {
    private String carModel;
    private int carValue;

    public CarLoan(int loanId, Customer customer, double principalAmount, double interestRate, int loanTerm, String loanStatus, String carModel, int carValue) {
        super(loanId, customer, principalAmount, interestRate, loanTerm, "CarLoan", loanStatus);
        this.carModel = carModel;
        this.carValue = carValue;
    }

    // Getters and Setters
    @Override
    public String toString() {
        return super.toString() + ", CarModel='" + carModel + "', CarValue=" + carValue;
    }
}
