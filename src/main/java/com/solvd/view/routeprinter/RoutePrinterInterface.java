package com.solvd.view.routeprinter;

import com.solvd.model.graph.Vertex;

public interface RoutePrinterInterface {
    void printShortestRoute(Vertex source, Vertex destination);
    void printFastestRoute(Vertex source, Vertex destination);
}
