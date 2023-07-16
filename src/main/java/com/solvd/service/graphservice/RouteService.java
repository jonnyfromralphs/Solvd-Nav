package com.solvd.service.graphservice;

import com.solvd.model.Vertex;

public interface RouteService {

        double calculateShortestDistanceByCar(Vertex source, Vertex destination);
        double calculateFastestDistanceByCar(Vertex source, Vertex destination);
        double calculateShortestDistanceByPublicTransport(Vertex source, Vertex destination);
        double calculateFastestDistanceByPublicTransport(Vertex source, Vertex destination);

}
