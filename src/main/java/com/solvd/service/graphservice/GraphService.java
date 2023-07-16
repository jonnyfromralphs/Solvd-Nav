package com.solvd.service.graphservice;

import com.solvd.model.graph.RoadNetworkGraph;

public interface GraphService {
    RoadNetworkGraph loadGraphFromDatabase();
    void loadVerticesFromDatabase();
    void loadEdgesFromDatabase();
    void loadBusStopsFromDatabase();
}