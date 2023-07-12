package com.solvd.graph;

import com.solvd.model.Edge;
import com.solvd.model.Vertex;

import java.util.ArrayList;
import java.util.List;

public class RoadNetworkGraph {
    private double[][] shortestRouteMatrix;
    private double[][] fastestRouteMatrix;
    private int vertexCount;
    private List<Vertex> vertexList;
    private List<Edge> edgeList;
    private double edgeWeightDistance;
    private double edgeWeightTime;

    public RoadNetworkGraph(int vertexCount) {
        this.vertexList = new ArrayList<>();
        this.edgeList = new ArrayList<>();
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
        double sourceLatitude = edge.getStart().getLatitude();
        double sourceLongitude = edge.getStart().getLongitude();
        double destinationLatitude = edge.getEnd().getLatitude();
        double destinationLongitude = edge.getEnd().getLongitude();
        edgeWeightDistance = getDistanceHaversine(sourceLatitude, sourceLongitude, destinationLatitude, destinationLongitude);
        shortestRouteMatrix[startIndex][endIndex] = edgeWeightDistance;
        shortestRouteMatrix[endIndex][startIndex] = edgeWeightDistance;
        edgeWeightTime = edgeWeightDistance / edge.getSpeed();
        fastestRouteMatrix[startIndex][endIndex] = edgeWeightTime;
        fastestRouteMatrix[endIndex][startIndex] = edgeWeightTime;
    }

    private double getDistanceHaversine(double lat1, double long1, double lat2, double long2) {
        double earthRadiusKM = 6371.0;
        double sourceLatRadian = Math.toRadians(lat1);
        double destinationLatRadian = Math.toRadians(lat2);
        double latDiff = Math.toRadians(lat1) - Math.toRadians(lat2);
        double longDiff = Math.toRadians(long1) - Math.toRadians(long2);
        double firstPart = Math.pow(Math.sin(latDiff / 2), 2) +
                Math.pow(Math.sin(longDiff / 2), 2) *
                        Math.cos(sourceLatRadian) *
                        Math.cos(destinationLatRadian);
        double secondPart = 2 * Math.asin(Math.sqrt(firstPart));
        return earthRadiusKM * secondPart;
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

}
