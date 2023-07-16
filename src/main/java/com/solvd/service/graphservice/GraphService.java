package com.solvd.service.graphservice;

public interface GraphService {
    void loadGraphFromDatabase();
    void loadVerticesFromDatabase();
    void loadEdgesFromDatabase();
    void loadBusStopsFromDatabase();
}