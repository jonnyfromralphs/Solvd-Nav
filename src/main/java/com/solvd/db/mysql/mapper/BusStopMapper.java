package com.solvd.db.mysql.mapper;

import com.solvd.model.BusStop;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
@Mapper
public interface BusStopMapper {

    BusStop getBusStopById(long id);

    List<BusStop> getAllBusStops();

    boolean createBusStop(BusStop busStop);

    boolean updateBusStop(BusStop busStop);

    boolean deleteBusStop(long id);
}
