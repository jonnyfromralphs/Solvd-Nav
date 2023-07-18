package com.solvd.exception;

import com.solvd.model.graph.Vertex;

public class NoRouteFoundException extends Exception {
    private Vertex source;
    private Vertex destination;
    private String message;

    public NoRouteFoundException(Vertex source, Vertex destination, String message) {
        this.source = source;
        this.destination = destination;
    }

    @Override
    public String getMessage() {
        return "No route found between "+source+" and "+destination+". "+message;
    }

}
