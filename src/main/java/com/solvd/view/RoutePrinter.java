package com.solvd.view;

import com.solvd.controller.FloydWarshallAlgorithm;
import com.solvd.model.Edge;
import com.solvd.graph.RoadNetworkGraph;
import com.solvd.model.Vertex;
import java.util.ArrayList;
import java.util.List;

public class RoutePrinter {
    private RoadNetworkGraph graph;
    private FloydWarshallAlgorithm floydWarshall;
    private List<Edge> edgeList;

    public RoutePrinter(RoadNetworkGraph graph, FloydWarshallAlgorithm floydWarshall) {
        this.graph = graph;
        this.floydWarshall = floydWarshall;
        this.edgeList = graph.getEdgeList();
    }

    public void printShortestRoute(Vertex source, Vertex destination) {
        int sourceIndex = graph.getVertexList().indexOf(source);
        int destinationIndex = graph.getVertexList().indexOf(destination);
        double distance = floydWarshall.getShortestRouteDistance(sourceIndex, destinationIndex);
        List<Vertex> path = getPath(sourceIndex, destinationIndex, floydWarshall.getShortestRouteMatrix());
        if (source != destination) {
            System.out.println("Source: " + source);
            System.out.println("Destination: " + destination);
            System.out.println("Distance: " + distance * 0.621371 + " miles");
            System.out.println("Shortest Route: " + formatPath(path));
            System.out.println(formatPathWithDirections(path));
            System.out.println();
        } else {
            System.out.println("Source: " + source);
            System.out.println("Destination: " + destination);
            System.out.print("Shortest Route: ");
            System.out.print("No route found since the source and destination are same\n");
            System.out.println(" ");
        }
    }

    public void printFastestRoute(Vertex source, Vertex destination) {
        int sourceIndex = graph.getVertexList().indexOf(source);
        int destinationIndex = graph.getVertexList().indexOf(destination);
        double time = floydWarshall.getFastestRouteTime(sourceIndex, destinationIndex);
        List<Vertex> path = getPath(sourceIndex, destinationIndex, floydWarshall.getFastestRouteMatrix());
        if (source != destination) {
            System.out.println("Source: " + source);
            System.out.println("Destination: " + destination);
            if (time >= 60) {
                System.out.println("Time: " + time / 60 + " minutes");
            } else {
                System.out.println("Time: " + time + " seconds");
            }
            System.out.println("Fastest Route: " + formatPath(path));
            System.out.println(formatPathWithDirections(path));
            System.out.println();
        } else {
            System.out.println("Source: " + source);
            System.out.println("Destination: " + destination);
            System.out.print("Fastest Route: ");
            System.out.print("No route found since the source and destination are same\n");
            System.out.println(" ");
        }

    }

    private List<Vertex> getPath(int sourceIndex, int destinationIndex, double[][] routeMatrix) {
        List<Vertex> path = new ArrayList<>();
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

        for (int i = 0; i < graph.getVertexCount(); i++) {
            if (i != sourceIndex && i != destinationIndex) {
                double distance = routeMatrix[sourceIndex][i] + routeMatrix[i][destinationIndex];
                if (distance == shortestDistance) {
                    return i;
                }
            }
        }

        return destinationIndex;
    }


    private String formatPath(List<Vertex> path) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < path.size(); i++) {
            sb.append(path.get(i));
            if (i != path.size() - 1) {
                sb.append(" -> ");
            }
        }
        return sb.toString();
    }

    private String formatPathWithDirections(List<Vertex> path) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < path.size() - 1; i++) {
            Vertex currentVertex = path.get(i);
            Vertex nextVertex = path.get(i + 1);
            Edge edge = findEdgeBetweenVertices(currentVertex, nextVertex);
            sb.append("From ").append(currentVertex.getId()).append(" take road ");
            if (edge != null) {
                sb.append(edge.getRoadName()+",");
            } else {
                sb.append("Unnamed Road,");
            }
            if ( i == path.size()-2) {
                sb.append(" to reach ").append(nextVertex.getId()+".");
            } else {
                sb.append(" to reach ").append(nextVertex.getId()+",");
            }

            if (i != path.size() - 2) {
                sb.append(" and then ");
            }
        }
        return sb.toString();
    }

    private Edge findEdgeBetweenVertices(Vertex sourceVertex, Vertex destinationVertex) {
        for (Edge edge : edgeList) {
            if ((edge.getStart().equals(sourceVertex) && edge.getEnd().equals(destinationVertex))
                    || (edge.getStart().equals(destinationVertex) && edge.getEnd().equals(sourceVertex))) {
                return edge;
            }
        }
        return null;
    }

}
