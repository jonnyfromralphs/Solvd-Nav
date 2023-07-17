package com.solvd.view.routeprinter;

import com.solvd.controller.FloydWarshallAlgorithm;
import com.solvd.controller.RouteCalculator;
import com.solvd.exception.CarRoutePrinterException;
import com.solvd.exception.InvalidGraphException;
import com.solvd.exception.NoRouteFoundException;
import com.solvd.model.graph.RoadNetworkGraph;
import com.solvd.model.graph.Vertex;
import com.solvd.view.RoutePrinting;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.List;

public class CarRoutePrinter extends RoutePrinting implements RoutePrinterInterface  {
    private static final Logger LOGGER = LogManager.getLogger(CarRoutePrinter.class);
    private RouteCalculator routeCalculator;

    public CarRoutePrinter(RoadNetworkGraph graph, FloydWarshallAlgorithm floydWarshall) throws InvalidGraphException {
        super(graph, floydWarshall);
        this.routeCalculator = new RouteCalculator(graph);
    }

    @Override
    public void printShortestRoute(Vertex source, Vertex destination) throws CarRoutePrinterException {
        int sourceIndex = graph.getVertexList().indexOf(source);
        int destinationIndex = graph.getVertexList().indexOf(destination);
        if (sourceIndex == -1 || destinationIndex == -1) {
            throw new CarRoutePrinterException(source, destination, "not found in the graph.");
        }
        List<Vertex> path = routeCalculator.calculateShortestPath(source, destination);
        if (!path.isEmpty()) {
            LOGGER.info("\nSource: " + source);
            LOGGER.info("Destination: " + destination);
            LOGGER.info("Shortest route using Car: " + formatPath(path) + "\n");
            LOGGER.info(formatPathWithDirections(path));
        } else {
            LOGGER.info("Source: " + source);
            LOGGER.info("Destination: " + destination);
            LOGGER.info("Shortest Route using Car: ");
            throw new CarRoutePrinterException(source, destination, "No route found");
        }
    }

    @Override
    public void printFastestRoute(Vertex source, Vertex destination) throws CarRoutePrinterException, NoRouteFoundException {
        int sourceIndex = graph.getVertexList().indexOf(source);
        int destinationIndex = graph.getVertexList().indexOf(destination);
        if (sourceIndex == -1 || destinationIndex == -1) {
            throw new CarRoutePrinterException(source, destination, "not found in the graph.");
        }
        List<Vertex> path = routeCalculator.getPath(sourceIndex, destinationIndex, floydWarshall.getFastestRouteMatrix());
        LOGGER.info("\nSource: " + source);
        LOGGER.info("Destination: " + destination);
        if (source != destination) {
            LOGGER.info("Fastest Route using Car: " + formatPath(path) + "\n");
            LOGGER.info(formatPathWithDirections(path));
        } else {
            LOGGER.info("Fastest Route using Car: ");
            throw new NoRouteFoundException(source, destination, "");
        }
    }
}
