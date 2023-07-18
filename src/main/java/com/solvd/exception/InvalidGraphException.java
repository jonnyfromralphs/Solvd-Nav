package com.solvd.exception;

public class InvalidGraphException extends Exception {
    private String msg;

    public InvalidGraphException(String msg) {
        this.msg = msg;
    }

    @Override
    public String getMessage() {
        return "InvalidGraphException!! - "+msg;
    }
}
