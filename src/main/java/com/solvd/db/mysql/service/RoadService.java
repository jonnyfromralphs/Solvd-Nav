package com.solvd.db.mysql.service;

import com.solvd.db.mysql.mapper.RoadMapper;
import com.solvd.db.utils.GenericDAO;
import com.solvd.model.Road;
import java.util.List;

public class RoadService implements GenericDAO<Road> {
    private RoadMapper roadMapper;

    public RoadService(RoadMapper roadMapper) {
        this.roadMapper = roadMapper;
    }

    @Override
    public boolean create(Road road) {
        return roadMapper.createRoad(road);
    }

    @Override
    public Road getById(long id) {
        return roadMapper.getRoadById(id);
    }

    @Override
    public List<Road> getAll() {
        return roadMapper.getAllRoads();
    }

    @Override
    public boolean update(Road road) {
        return roadMapper.updateRoad(road);
    }

    @Override
    public boolean delete(long id) {
        return roadMapper.deleteRoad(id);
    }
}
