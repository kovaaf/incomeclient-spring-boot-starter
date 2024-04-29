package org.company.exceptions;

public class NonValidExternalResourceOrEmptyException extends RuntimeException {
    public NonValidExternalResourceOrEmptyException(String url) {
        super("Non valid resource with incomes: " + url);
    }
}
