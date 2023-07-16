package com.solvd.db.mysql.service;

import com.solvd.db.mysql.mapper.BusStopMapper;
import com.solvd.model.BusStop;
import java.util.List;

public class BusStopService {
    private BusStopMapper busStopMapper;

    public BusStopService(BusStopMapper busStopMapper) {
        this.busStopMapper = busStopMapper;
    }

    public boolean create(BusStop busStop) {
        return busStopMapper.createBusStop(busStop);
    }

    public BusStop getById(long id) {
        return busStopMapper.getBusStopById(id);
    }

    public List<BusStop> getAll() {
        return busStopMapper.getAllBusStops();
    }

    public boolean update(BusStop busStop) {
        return busStopMapper.updateBusStop(busStop);
    }

    public boolean delete(long id) {
        return busStopMapper.deleteBusStop(id);
    }
}
