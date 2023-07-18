package com.solvd.service;

import com.solvd.db.mysql.mapper.RoadMapper;
import com.solvd.model.Road;
import java.util.List;

public class RoadService {
    private RoadMapper roadMapper;

    public RoadService(RoadMapper roadMapper) {
        this.roadMapper = roadMapper;
    }

    public boolean create(Road road) {
        return roadMapper.createRoad(road);
    }

    public List<Road> getAllRoadsForAddresses() {
        return roadMapper.getAllRoadsForAddresses();
    }

    public List<Road> getAllRoadsForBusStops() {
        return roadMapper.getAllRoadsForBusStops();
    }
}
