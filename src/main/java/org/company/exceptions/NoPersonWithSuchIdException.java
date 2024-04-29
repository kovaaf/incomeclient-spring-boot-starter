package org.company.exceptions;

public class NoPersonWithSuchIdException extends RuntimeException {
    public NoPersonWithSuchIdException(Long id) {
        super("No person with such id: " + id);
    }
}
