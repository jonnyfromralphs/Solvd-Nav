package com.solvd.exception;

public class CarRoutePrinterException extends Exception {
    private String message;
    public CarRoutePrinterException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return "Error!! - "+message;
    }
}
