
package org.exception;

public class InvalidLoanException extends Exception {
    // Add serialVersionUID to prevent the warning
    private static final long serialVersionUID = 1L;

    public InvalidLoanException(String message) {
        super(message);
    }
}
