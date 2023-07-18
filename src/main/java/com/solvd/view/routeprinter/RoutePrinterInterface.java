package com.solvd.view.routeprinter;

import com.solvd.exception.CarRoutePrinterException;
import com.solvd.exception.GraphDataMissingException;
import com.solvd.exception.NoRouteFoundException;
import com.solvd.model.graph.Vertex;

public interface RoutePrinterInterface {
    void printShortestRoute(Vertex source, Vertex destination) throws GraphDataMissingException, CarRoutePrinterException, NoRouteFoundException;
    void printFastestRoute(Vertex source, Vertex destination) throws GraphDataMissingException, CarRoutePrinterException, NoRouteFoundException;
}
