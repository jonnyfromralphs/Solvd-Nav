package com.solvd.exception;

public class GraphDataMissingException extends Exception {
    private String msg;
    public GraphDataMissingException(String msg) {
        this.msg = msg;
    }
    @Override
    public String getMessage() {
        return "Error!! - "+msg;
    }
}
