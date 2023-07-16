package com.solvd.view;

import com.solvd.model.TransportationMethod;
import com.solvd.model.graph.Vertex;
import com.solvd.view.routeprinter.CarRoutePrinter;
import com.solvd.view.routeprinter.PublicTransportationRoutePrinter;

public class RoutePrinterService {
    private CarRoutePrinter carRoutePrinter;
    private PublicTransportationRoutePrinter publicTransportationRoutePrinter;

    public RoutePrinterService(CarRoutePrinter carRoutePrinter, PublicTransportationRoutePrinter publicTransportationRoutePrinter) {
        this.carRoutePrinter = carRoutePrinter;
        this.publicTransportationRoutePrinter = publicTransportationRoutePrinter;
    }

    public void printRoute(Vertex source, Vertex destination, TransportationMethod transportationMethod) {
        if (transportationMethod == TransportationMethod.CAR) {
            carRoutePrinter.printShortestRoute(source, destination);
            carRoutePrinter.printFastestRoute(source, destination);
        } else if (transportationMethod == TransportationMethod.PUBLIC_TRANSPORTATION) {
            publicTransportationRoutePrinter.printShortestRoute(source, destination);
            publicTransportationRoutePrinter.printFastestRoute(source, destination);
        } else {
            System.out.println("Invalid transportation method.");
        }
    }
}
