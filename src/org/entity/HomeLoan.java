package org.entity;

public class HomeLoan extends Loan {
    private String propertyAddress;
    private int propertyValue;

    public HomeLoan(int loanId, Customer customer, double principalAmount, double interestRate, int loanTerm, String loanStatus, String propertyAddress, int propertyValue) {
        super(loanId, customer, principalAmount, interestRate, loanTerm, "HomeLoan", loanStatus);
        this.propertyAddress = propertyAddress;
        this.propertyValue = propertyValue;
    }

   
    @Override
    public String toString() {
        return super.toString() + ", PropertyAddress='" + propertyAddress + "', PropertyValue=" + propertyValue;
    }
}
