package com.solvd.service;

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

    public BusStop getByName(String name) {
        return busStopMapper.getBusStopByName(name);
    }

    public List<BusStop> getAll() {
        return busStopMapper.getAllBusStops();
    }
}
