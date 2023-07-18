package com.solvd.view;

import com.solvd.exception.CarRoutePrinterException;
import com.solvd.exception.GraphDataMissingException;
import com.solvd.exception.NoRouteFoundException;
import com.solvd.model.TransportationMethod;
import com.solvd.model.graph.Vertex;
import com.solvd.view.routeprinter.CarRoutePrinter;
import com.solvd.view.routeprinter.PublicTransportationRoutePrinter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RoutePrinterService {
    private static final Logger LOGGER = LogManager.getLogger(RoutePrinterService.class);
    private CarRoutePrinter carRoutePrinter;
    private PublicTransportationRoutePrinter publicTransportationRoutePrinter;

    public RoutePrinterService(CarRoutePrinter carRoutePrinter, PublicTransportationRoutePrinter publicTransportationRoutePrinter) {
        this.carRoutePrinter = carRoutePrinter;
        this.publicTransportationRoutePrinter = publicTransportationRoutePrinter;
    }

    public void printRoute(Vertex source, Vertex destination, TransportationMethod transportationMethod, boolean fastest) throws GraphDataMissingException, CarRoutePrinterException, NoRouteFoundException {
        if (transportationMethod == TransportationMethod.CAR && fastest) {
            carRoutePrinter.printFastestRoute(source, destination);
        } else if (transportationMethod == TransportationMethod.CAR) {
            carRoutePrinter.printShortestRoute(source, destination);
        } else if (transportationMethod == TransportationMethod.PUBLIC_TRANSPORTATION && fastest) {
            publicTransportationRoutePrinter.printFastestRoute(source, destination);
        } else if (transportationMethod == TransportationMethod.PUBLIC_TRANSPORTATION) {
            publicTransportationRoutePrinter.printShortestRoute(source, destination);
        } else {
            LOGGER.info("Invalid transportation method.");
        }
    }
}
