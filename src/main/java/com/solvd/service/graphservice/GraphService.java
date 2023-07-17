package com.solvd.service.graphservice;

import com.solvd.model.Address;
import com.solvd.model.BusStop;
import com.solvd.model.graph.RoadNetworkGraph;

import java.util.List;

public interface GraphService {
    RoadNetworkGraph loadGraphFromDatabase();
    void loadVerticesFromDatabase(List<Address> addresses);
    void loadEdgesFromDatabase();
    void loadBusStopsFromDatabase(List<BusStop> busStops);
}