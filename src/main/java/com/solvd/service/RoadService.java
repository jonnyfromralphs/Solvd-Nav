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

    public Road getById(long id) {
        return roadMapper.getRoadById(id);
    }

    public List<Road> getAllRoadsForAddresses() {
        return roadMapper.getAllRoadsForAddresses();
    }
    public List<Road> getAllRoadsForBusStops() {
        return roadMapper.getAllRoadsForBusStops();
    }

    public boolean update(Road road) {
        return roadMapper.updateRoad(road);
    }

    public boolean delete(long id) {
        return roadMapper.deleteRoad(id);
    }
}
