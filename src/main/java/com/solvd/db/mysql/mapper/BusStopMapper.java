package com.solvd.db.mysql.mapper;

import com.solvd.model.BusStop;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
@Mapper
public interface BusStopMapper {

    BusStop getBusStopById(long id);

    BusStop getBusStopByName(String name);

    List<BusStop> getAllBusStops();

    boolean createBusStop(BusStop busStop);
}
