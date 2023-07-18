package com.solvd.model.graph;


import com.solvd.exception.GraphCreationException;
import com.solvd.exception.GraphDataMissingException;
import com.solvd.model.TransportationMethod;
import java.util.ArrayList;
import java.util.List;


public class RoadNetworkGraph {
    private TransportationMethod transportationMethod;
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

    public void updateVertexCount() {
        this.vertexCount = vertexList.size();
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

    public void addNewVertexAndUpdateRoutes(Vertex newVertex, List<Edge> edges) throws GraphCreationException {
        try {
            addNewVertexToGraph(newVertex);
            for (Edge edge : edges) {
                addEdge(edge);
            }
            updateVertexCount();
        } catch (Exception e) {
            throw new GraphCreationException("Adding new vertex and updating routes unsuccessful");
        }
    }

    private void addNewVertexToGraph(Vertex address) {
        vertexList.add(address);
        int newSize = vertexList.size();
        double[][] newShortestRouteMatrix = new double[newSize][newSize];
        for (int i = 0; i < newSize-1; i++) {
            for (int j = 0; j < newSize-1; j++) {
                newShortestRouteMatrix[i][j] = shortestRouteMatrix[i][j];
            }
        }
        for (int i = 0; i < newSize; i++) {
            newShortestRouteMatrix[i][newSize - 1] = Double.POSITIVE_INFINITY;
            newShortestRouteMatrix[newSize - 1][i] = Double.POSITIVE_INFINITY;
        }
        shortestRouteMatrix = newShortestRouteMatrix;
        double[][] newFastestRouteMatrix = new double[newSize][newSize];
        for (int i = 0; i < newSize - 1; i++) {
            for (int j = 0; j < newSize - 1; j++) {
                newFastestRouteMatrix[i][j] = fastestRouteMatrix[i][j];
            }
        }
        for (int i = 0; i < newSize; i++) {
            newFastestRouteMatrix[i][newSize - 1] = Double.POSITIVE_INFINITY;
            newFastestRouteMatrix[newSize - 1][i] = Double.POSITIVE_INFINITY;
        }
        fastestRouteMatrix = newFastestRouteMatrix;
    }

    public Vertex findNearestVertexToBusStop(Vertex busStop, List<Vertex> vertices) {
        Vertex nearestVertex = null;
        double nearestDistance = Double.MAX_VALUE;

        for (Vertex vertex : vertices) {
            if (vertex != busStop) {
                double distance = getDistanceUsingHaversine(busStop, vertex);
                if (distance < nearestDistance) {
                    nearestVertex = vertex;
                    nearestDistance = distance;
                }
            }
        }

        return nearestVertex;
    }

    public double[][] getFastestRouteMatrix() {
        return fastestRouteMatrix;
    }

    public void setFastestRouteMatrix(double[][] fastestRoute) {
        this.fastestRouteMatrix = fastestRoute;
    }

    public double[][] getShortestRouteMatrix() {
        return shortestRouteMatrix;
    }

    public void setShortestRouteMatrix(double[][] shortestRoute) {
        this.shortestRouteMatrix = shortestRoute;
    }

    public int getVertexCount() {
        return vertexCount;
    }

    public void setVertexCount(int vertexCount) {
        this.vertexCount = vertexCount;
    }

    public List<Vertex> getVertexList() {
        return vertexList;
    }

    public void setVertexList(List<Vertex> vertexList) {
        this.vertexList = vertexList;
    }

    public List<Edge> getEdgeList() {
        return edgeList;
    }


    public TransportationMethod getTransportationMethod() {
        return transportationMethod;
    }

    public void setTransportationMethod(TransportationMethod transportationMethod) {
        this.transportationMethod = transportationMethod;
    }

    public List<Vertex> getBusStopList() {
        return busStopList;
    }

    public void setBusStopList(List<Vertex> busStopList) {
        this.busStopList = busStopList;
    }


}


