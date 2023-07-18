package com.solvd.exception;

public class GraphCreationException extends Exception {
    private String msg;

    public GraphCreationException(String msg) {
        this.msg = msg;
    }
    @Override
    public String getMessage() {
        return "Error!! - "+msg;
    }
}
