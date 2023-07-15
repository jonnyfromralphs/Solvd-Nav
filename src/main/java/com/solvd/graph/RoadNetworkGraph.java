package com.solvd.graph;


import com.solvd.model.Edge;
import com.solvd.model.TransportationMethod;
import com.solvd.model.Vertex;
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

    public void addVertex(Vertex vertex) {
        vertexList.add(vertex);
    }

    public void addEdge(Edge edge) {
        edgeList.add(edge);
        int startIndex = vertexList.indexOf(edge.getStart());
        int endIndex = vertexList.indexOf(edge.getEnd());
        Vertex start = edge.getStart();
        Vertex end = edge.getEnd();
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


    public void addBusStop(Vertex busStop) {

        busStopList.add(busStop);
        vertexList.add(busStop);
    }

    public Vertex findNearestBusStop(Vertex address) {
        Vertex nearestBusStop = null;
        double minDistance = Double.POSITIVE_INFINITY;
        for (Vertex busStop : busStopList) {
            double distance = getDistanceUsingHaversine(address, busStop);
            if (distance < minDistance) {
                minDistance = distance;
                nearestBusStop = busStop;
            }
        }
        return nearestBusStop;
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


