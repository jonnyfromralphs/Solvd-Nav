package com.solvd.db.mysql.service;

import com.solvd.db.mysql.mapper.BusStopMapper;
import com.solvd.db.utils.GenericDAO;
import com.solvd.model.BusStop;
import java.util.List;

public class BusStopService implements GenericDAO<BusStop> {
    private BusStopMapper busStopMapper;

    public BusStopService(BusStopMapper busStopMapper) {
        this.busStopMapper = busStopMapper;
    }
    @Override
    public boolean create(BusStop busStop) {
        return busStopMapper.createBusStop(busStop);
    }

    @Override
    public BusStop getById(long id) {
        return busStopMapper.getBusStopById(id);
    }

    @Override
    public List<BusStop> getAll() {
        return busStopMapper.getAllBusStops();
    }

    @Override
    public boolean update(BusStop busStop) {
        return busStopMapper.updateBusStop(busStop);
    }

    @Override
    public boolean delete(long id) {
        return busStopMapper.deleteBusStop(id);
    }
}
