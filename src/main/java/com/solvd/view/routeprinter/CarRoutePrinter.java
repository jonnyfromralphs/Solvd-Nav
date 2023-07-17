package com.solvd.view.routeprinter;

import com.solvd.controller.FloydWarshallAlgorithm;
import com.solvd.controller.RouteCalculator;
import com.solvd.exception.CarRoutePrinterException;
import com.solvd.exception.InvalidGraphException;
import com.solvd.exception.NoRouteFoundException;
import com.solvd.model.graph.RoadNetworkGraph;
import com.solvd.model.graph.Vertex;
import com.solvd.view.RoutePrinting;
import java.util.List;

public class CarRoutePrinter extends RoutePrinting implements RoutePrinterInterface  {
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
            System.out.println("Source: " + source);
            System.out.println("Destination: " + destination);
            System.out.println("Shortest route using Car: " + formatPath(path) + "\n");
            System.out.println(formatPathWithDirections(path));
            System.out.println();
        } else {
            System.out.println("Source: " + source);
            System.out.println("Destination: " + destination);
            System.out.print("Shortest Route using Car: ");
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
        System.out.println("Source: " + source);
        System.out.println("Destination: " + destination);
        if (source != destination) {
            System.out.println("Fastest Route using Car: " + formatPath(path) + "\n");
            System.out.println(formatPathWithDirections(path));
            System.out.println();
        } else {
            System.out.print("Fastest Route using Car: ");
            throw new NoRouteFoundException(source, destination, "");
        }
    }
}
