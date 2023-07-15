package com.solvd.controller;

import com.solvd.graph.RoadNetworkGraph;
import com.solvd.model.Vertex;

import java.util.List;

public class FloydWarshallAlgorithm {
    private RoadNetworkGraph graph;
    private double[][] shortestRouteMatrix;
    private double[][] fastestRouteMatrix;

    public FloydWarshallAlgorithm(RoadNetworkGraph graph) {
        this.graph = graph;
        this.shortestRouteMatrix = graph.getShortestRouteMatrix();
        this.fastestRouteMatrix = graph.getFastestRouteMatrix();
    }
    public void calculateShortestAndFastestRoutes() {
        int vertexCount = graph.getVertexCount();
        double[][] shortestRouteMatrix = graph.getShortestRouteMatrix();
        double[][] fastestRouteMatrix = graph.getFastestRouteMatrix();

        for (int i = 0; i < vertexCount; i++) {
            for (int j = 0; j < vertexCount; j++) {
                for (int k = 0; k < vertexCount; k++) {
                    shortestRouteMatrix[j][k] = Math.min(shortestRouteMatrix[j][k], shortestRouteMatrix[j][i] + shortestRouteMatrix[i][k]);
                    fastestRouteMatrix[j][k] = Math.min(fastestRouteMatrix[j][k], fastestRouteMatrix[j][i] + fastestRouteMatrix[i][k]);
                }
            }
        }

        List<Vertex> busStopList = graph.getBusStopList();
        for (Vertex busStop : busStopList) {
            int busStopIndex = graph.getVertexList().indexOf(busStop);
            for (int i = 0; i < vertexCount; i++) {
                for (int j = 0; j < vertexCount; j++) {
                    double shortestViaBusStop = shortestRouteMatrix[i][busStopIndex] + shortestRouteMatrix[busStopIndex][j];
                    double fastestViaBusStop = fastestRouteMatrix[i][busStopIndex] + fastestRouteMatrix[busStopIndex][j];
                    if (shortestViaBusStop < shortestRouteMatrix[i][j]) {
                        shortestRouteMatrix[i][j] = shortestViaBusStop;
                    }
                    if (fastestViaBusStop < fastestRouteMatrix[i][j]) {
                        fastestRouteMatrix[i][j] = fastestViaBusStop;
                    }
                }
            }
        }
    }


    public double getShortestRouteDistance(int sourceIndex, int destinationIndex) {
        return shortestRouteMatrix[sourceIndex][destinationIndex];
    }

    public double getFastestRouteTime(int sourceIndex, int destinationIndex) {
        return fastestRouteMatrix[sourceIndex][destinationIndex];
    }

    public double[][] getShortestRouteMatrix() {
        return shortestRouteMatrix;
    }

    public void setShortestRouteMatrix(double[][] shortestRouteMatrix) {
        this.shortestRouteMatrix = shortestRouteMatrix;
    }

    public double[][] getFastestRouteMatrix() {
        return fastestRouteMatrix;
    }

    public void setFastestRouteMatrix(double[][] fastestRouteMatrix) {
        this.fastestRouteMatrix = fastestRouteMatrix;
    }
}


