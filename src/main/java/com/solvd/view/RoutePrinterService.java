package com.solvd.view;

import com.solvd.exception.GraphDataMissingException;
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

    public void printRoute(Vertex source, Vertex destination, TransportationMethod transportationMethod, boolean fastest) throws GraphDataMissingException {
        if (transportationMethod == TransportationMethod.CAR && fastest) {
            carRoutePrinter.printFastestRoute(source, destination);
        } else if (transportationMethod == TransportationMethod.CAR) {
            carRoutePrinter.printShortestRoute(source, destination);
        } else if (transportationMethod == TransportationMethod.PUBLIC_TRANSPORTATION && fastest) {
            publicTransportationRoutePrinter.printFastestRoute(source, destination);
        } else if (transportationMethod == TransportationMethod.PUBLIC_TRANSPORTATION) {
            publicTransportationRoutePrinter.printShortestRoute(source, destination);
        }else {
            System.out.println("Invalid transportation method.");
        }
    }
}
