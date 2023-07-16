package com.solvd.controller;

import com.solvd.model.graph.RoadNetworkGraph;
import com.solvd.model.graph.Vertex;

import java.util.ArrayList;
import java.util.List;

public class RouteCalculator {
    private RoadNetworkGraph graph;
    private FloydWarshallAlgorithm floydWarshall;

    public RouteCalculator(RoadNetworkGraph graph) {
        this.graph = graph;
        this.floydWarshall = new FloydWarshallAlgorithm(graph);
    }

    public List<Vertex> calculateShortestPath(Vertex source, Vertex destination) {
        floydWarshall.calculateShortestAndFastestRoutes();
        int sourceIndex = graph.getVertexList().indexOf(source);
        int destinationIndex = graph.getVertexList().indexOf(destination);
        double[][] shortestRouteMatrix = floydWarshall.getShortestRouteMatrix();
        List<Vertex> shortestPath = getPath(sourceIndex, destinationIndex, shortestRouteMatrix);
        return shortestPath;
    }

    public List<Vertex> calculateFastestPath(Vertex source, Vertex destination) {
        floydWarshall.calculateShortestAndFastestRoutes();
        int sourceIndex = graph.getVertexList().indexOf(source);
        int destinationIndex = graph.getVertexList().indexOf(destination);
        double[][] fastestRouteMatrix = floydWarshall.getFastestRouteMatrix();
        List<Vertex> fastestPath = getPath(sourceIndex, destinationIndex, fastestRouteMatrix);
        return fastestPath;
    }

    public List<Vertex> getPath(int sourceIndex, int destinationIndex, double[][] routeMatrix) {
        List<Vertex> path = new ArrayList<>();
        if (sourceIndex == destinationIndex) {
            path.add(graph.getVertexList().get(sourceIndex));
            return path;
        }
        path.add(graph.getVertexList().get(sourceIndex));
        int intermediateVertexIndex = findIntermediateVertex(sourceIndex, destinationIndex, routeMatrix);
        while (intermediateVertexIndex != destinationIndex) {
            path.add(graph.getVertexList().get(intermediateVertexIndex));
            intermediateVertexIndex = findIntermediateVertex(intermediateVertexIndex, destinationIndex, routeMatrix);
        }
        path.add(graph.getVertexList().get(destinationIndex));
        return path;
    }

    private int findIntermediateVertex(int sourceIndex, int destinationIndex, double[][] routeMatrix) {

        double shortestDistance = routeMatrix[sourceIndex][destinationIndex];

        if (shortestDistance == Double.POSITIVE_INFINITY) {
            return destinationIndex;
        }

        int newSize = graph.getVertexList().size();

        for (int i = 0; i < newSize; i++) {
            if (i != sourceIndex && i != destinationIndex) {
                double distance = routeMatrix[sourceIndex][i] + routeMatrix[i][destinationIndex];
                if (distance == shortestDistance) {
                    return i;
                }
            }
        }

        return destinationIndex;
    }


}
