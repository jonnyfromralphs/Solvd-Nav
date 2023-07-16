package com.solvd.view.routeprinter;

import com.solvd.controller.FloydWarshallAlgorithm;
import com.solvd.controller.RouteCalculator;
import com.solvd.model.graph.RoadNetworkGraph;
import com.solvd.model.graph.Vertex;
import com.solvd.view.RoutePrinting;

import java.util.List;

public class CarRoutePrinter extends RoutePrinting implements RoutePrinterInterface  {
    private RouteCalculator routeCalculator;


    public CarRoutePrinter(RoadNetworkGraph graph, FloydWarshallAlgorithm floydWarshall) {
        super(graph, floydWarshall);
        this.routeCalculator = new RouteCalculator(graph);
    }

    @Override
    public void printShortestRoute(Vertex source, Vertex destination) {
        int sourceIndex = graph.getVertexList().indexOf(source);
        int destinationIndex = graph.getVertexList().indexOf(destination);
        if (sourceIndex == -1 || destinationIndex == -1) {
            System.out.println("Source or destination address not found in the graph.");
            return;
        }
        List<Vertex> path = routeCalculator.calculateShortestPath(source, destination);


        if (!path.isEmpty()) {
            System.out.println("Source: " + source);
            System.out.println("Destination: " + destination);
            System.out.println("Shortest route using Car: " + formatPath(path));
            System.out.println(formatPathWithDirections(path));
            System.out.println();
        } else {
            System.out.println("Source: " + source);
            System.out.println("Destination: " + destination);
            System.out.print("Shortest Route using Car: ");
            System.out.print("No route found.\n");
            System.out.println();
        }
    }



    @Override
    public void printFastestRoute(Vertex source, Vertex destination) {
        int sourceIndex = graph.getVertexList().indexOf(source);
        int destinationIndex = graph.getVertexList().indexOf(destination);
        if (sourceIndex == -1 || destinationIndex == -1) {
            System.out.println("Source or destination vertex not found in the graph.");
            return;
        }

        List<Vertex> path = routeCalculator.getPath(sourceIndex, destinationIndex, floydWarshall.getFastestRouteMatrix());

        if (source != destination) {
            System.out.println("Source: " + source);
            System.out.println("Destination: " + destination);
            System.out.println("Fastest Route using Car: " + formatPath(path));
            System.out.println(formatPathWithDirections(path));
            System.out.println();
        } else {
            System.out.println("Source: " + source);
            System.out.println("Destination: " + destination);
            System.out.print("Fastest Route using Car: ");
            System.out.print("No route found since the source and destination are same\n");
            System.out.println(" ");
        }
    }
}
