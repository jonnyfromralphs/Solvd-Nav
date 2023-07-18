package com.solvd.model.graph;

import com.solvd.exception.GraphCreationException;
import com.solvd.exception.GraphDataMissingException;
import java.util.ArrayList;
import java.util.List;

public class RoadNetworkGraph {
    private double[][] shortestRouteMatrix;
    private double[][] fastestRouteMatrix;
    private List<Vertex> busStopList;
    private int vertexCount;
    private List<Vertex> vertexList;
    private List<Edge> edgeList;
    private double edgeWeightDistance;
    private double edgeWeightTime;

    public RoadNetworkGraph(int vertexCount) {
        this.vertexList = new ArrayList<>();
        this.edgeList = new ArrayList<>();
        this.busStopList = new ArrayList<>();
        this.vertexCount = vertexCount;
        this.shortestRouteMatrix = new double[vertexCount][vertexCount];
        this.fastestRouteMatrix = new double[vertexCount][vertexCount];
        for (int i = 0; i < vertexCount; i++) {
            for (int j = 0; j < vertexCount; j++) {
                if (i != j) {
                    shortestRouteMatrix[i][j] = Double.POSITIVE_INFINITY;
                    fastestRouteMatrix[i][j] = Double.POSITIVE_INFINITY;
                }
            }
        }
    }

    public void addVertex(Vertex vertex) throws GraphCreationException {
        if (vertex == null) {
            throw new GraphCreationException("Add vertex unsuccessful.Vertex cannot be null.");
        }
        vertexList.add(vertex);
    }

    public void addEdge(Edge edge) throws GraphCreationException {
        if (edge == null) {
            throw new GraphCreationException("Add edge unsuccessful.Edge cannot be null.");
        }
        edgeList.add(edge);
        Vertex start = edge.getStart();
        Vertex end = edge.getEnd();
        int startIndex = vertexList.indexOf(start);
        int endIndex = vertexList.indexOf(end);
        edgeWeightDistance = getDistanceUsingHaversine(start, end);
        shortestRouteMatrix[startIndex][endIndex] = edgeWeightDistance;
        shortestRouteMatrix[endIndex][startIndex] = edgeWeightDistance;
        edgeWeightTime = edgeWeightDistance / edge.getSpeed();
        fastestRouteMatrix[startIndex][endIndex] = edgeWeightTime;
        fastestRouteMatrix[endIndex][startIndex] = edgeWeightTime;
    }

    public double getDistanceUsingHaversine( Vertex source, Vertex end) {
        double sourceLatitude = source.getLatitude();
        double sourceLongitude = source.getLongitude();
        double endLatitude = end.getLatitude();
        double endLongitude = end.getLongitude();
        double earthRadiusKM = 6371.0;
        double sourceLatRadian = Math.toRadians(sourceLatitude);
        double destinationLatRadian = Math.toRadians(endLatitude);
        double latDiff = Math.toRadians(sourceLatitude) - Math.toRadians(endLatitude);
        double longDiff = Math.toRadians(sourceLongitude) - Math.toRadians(endLongitude);
        double firstPart = Math.pow(Math.sin(latDiff / 2), 2) +
                Math.pow(Math.sin(longDiff / 2), 2) *
                        Math.cos(sourceLatRadian) *
                        Math.cos(destinationLatRadian);
        double secondPart = 2 * Math.asin(Math.sqrt(firstPart));
        return earthRadiusKM * secondPart;
    }

    public void addBusStop(Vertex busStop) throws GraphCreationException {
        if (busStop == null) {
            throw new GraphCreationException("Add bus stop unsuccessful.Bus Stop cannot be null.");
        }
        busStopList.add(busStop);
        vertexList.add(busStop);
    }

    public Vertex findNearestBusStop(Vertex address) throws GraphDataMissingException {
        if (busStopList.isEmpty()) {
            throw new GraphDataMissingException("Cannot find bus stop. Bus stop list cannot be empty.");
        }
        Vertex nearestBusStop = null;
        double minDistance = Double.POSITIVE_INFINITY;
        for (Vertex busStop : busStopList) {
            double distance = getDistanceUsingHaversine(address, busStop);
            if (distance < minDistance) {
                minDistance = distance;
                nearestBusStop = busStop;
            }
        }
        if (nearestBusStop == null) {
            throw new GraphDataMissingException("No nearest bus stop found for the given address.");
        }
        return nearestBusStop;
    }

    public double[][] getFastestRouteMatrix() {
        return fastestRouteMatrix;
    }

    public double[][] getShortestRouteMatrix() {
        return shortestRouteMatrix;
    }

    public int getVertexCount() {
        return vertexCount;
    }

    public List<Vertex> getVertexList() {
        return vertexList;
    }

    public List<Edge> getEdgeList() {
        return edgeList;
    }

    public List<Vertex> getBusStopList() {
        return busStopList;
    }
}
