package com.solvd.db.mysql.mapper;

import com.solvd.model.BusStop;
import java.util.List;

public interface BusStopMapper {

    BusStop getBusStopById(long id);

    List<BusStop> getAllBusStops();

    boolean createBusStop(BusStop busStop);

    boolean updateBusStop(BusStop busStop);

    boolean deleteBusStop(long id);
}
