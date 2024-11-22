package org.dao;
import java.util.ArrayList;
import java.util.List;
import org.entity.Loan;
import org.exception.InvalidLoanException;

public class ILoanRepositoryImpl implements ILoanRepository {
    private List<Loan> loanDatabase = new ArrayList<>();

    @Override
    public void applyLoan(Loan loan) throws InvalidLoanException {
        loanDatabase.add(loan);
    }

    @Override
    public double calculateInterest(int loanId) throws InvalidLoanException {
        Loan loan = getLoanById(loanId);
        if (loan == null) throw new InvalidLoanException("Loan not found.");
        return (loan.getPrincipalAmount() * loan.getInterestRate() * loan.getLoanTerm()) / 100;  // Simple interest formula
    }

    @Override
    public void loanStatus(int loanId) throws InvalidLoanException {
        Loan loan = getLoanById(loanId);
        if (loan == null) throw new InvalidLoanException("Loan not found.");
        if (loan.getCustomer().getCreditScore() > 650) {
            loan.setLoanStatus("Approved");
        } else {
            loan.setLoanStatus("Rejected");
        }
    }

    @Override
    public double calculateEMI(int loanId) throws InvalidLoanException {
        Loan loan = getLoanById(loanId);
        if (loan == null) throw new InvalidLoanException("Loan not found.");
        double P = loan.getPrincipalAmount();
        double R = loan.getInterestRate() / 12 / 100;  // Monthly interest rate
        int N = loan.getLoanTerm();
        return (P * R * Math.pow(1 + R, N)) / (Math.pow(1 + R, N) - 1);
    }

    @Override
    public void loanRepayment(int loanId, double amount) throws InvalidLoanException {
        Loan loan = getLoanById(loanId);
        if (loan == null) throw new InvalidLoanException("Loan not found.");
        double emi = calculateEMI(loanId);
        if (amount < emi) {
            System.out.println("Insufficient funds to pay EMI.");
        } else {
            loan.setPrincipalAmount(loan.getPrincipalAmount() - amount);
            System.out.println("EMI paid successfully. Remaining loan amount: " + loan.getPrincipalAmount());
        }
    }

    @Override
    public List<Loan> getAllLoan() {
        return loanDatabase;
    }

    @Override
    public Loan getLoanById(int loanId) throws InvalidLoanException {
        return loanDatabase.stream().filter(loan -> loan.getLoanId() == loanId).findFirst().orElseThrow(() -> new InvalidLoanException("Loan not found."));
    }
}
