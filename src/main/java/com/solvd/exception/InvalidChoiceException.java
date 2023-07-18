package com.solvd.exception;

public class InvalidChoiceException extends Exception {
    public InvalidChoiceException() {
        super("Invalid choice, please try again.");
    }
}
