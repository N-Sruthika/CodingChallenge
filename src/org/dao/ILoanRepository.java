package org.dao;
import org.entity.Loan;
import org.exception.InvalidLoanException;

import java.util.List;

public interface ILoanRepository {
    void applyLoan(Loan loan) throws InvalidLoanException;
    double calculateInterest(int loanId) throws InvalidLoanException;
    void loanStatus(int loanId) throws InvalidLoanException;
    double calculateEMI(int loanId) throws InvalidLoanException;
    void loanRepayment(int loanId, double amount) throws InvalidLoanException;
    List<Loan> getAllLoan();
    Loan getLoanById(int loanId) throws InvalidLoanException;
}
