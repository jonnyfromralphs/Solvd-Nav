package com.solvd.exception;

import com.solvd.model.graph.Vertex;

public class CarRoutePrinterException extends Exception {
    private Vertex source;
    private Vertex destination;
    private String message;
    public CarRoutePrinterException(Vertex source, Vertex destination, String message) {
        this.message = message;
        this.source = source;
        this.destination = destination;
    }

    @Override
    public String getMessage() {
        return "Source: "+source.getName()+" and Destination: "+destination.getName()+" - "+message;
    }
}
