package com.solvd.service.graphservice;

import com.solvd.exception.GraphCreationException;
import com.solvd.model.Address;
import com.solvd.model.BusStop;
import com.solvd.model.graph.RoadNetworkGraph;
import java.util.List;

public interface GraphService {
    RoadNetworkGraph loadGraphFromDatabase() throws GraphCreationException;

    void loadVerticesFromDatabase(List<Address> addresses) throws GraphCreationException;

    void loadEdgesFromDatabase() throws GraphCreationException;

    void loadBusStopsFromDatabase(List<BusStop> busStops) throws GraphCreationException;
}
