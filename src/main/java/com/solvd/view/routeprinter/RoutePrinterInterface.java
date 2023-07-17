package com.solvd.view.routeprinter;

import com.solvd.exception.GraphDataMissingException;
import com.solvd.model.graph.Vertex;

public interface RoutePrinterInterface {
    void printShortestRoute(Vertex source, Vertex destination) throws GraphDataMissingException;
    void printFastestRoute(Vertex source, Vertex destination) throws GraphDataMissingException;
}
