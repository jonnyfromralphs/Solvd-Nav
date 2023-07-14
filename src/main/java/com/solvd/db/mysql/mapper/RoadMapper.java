package com.solvd.db.mysql.mapper;

import com.solvd.model.Road;
import java.util.List;

public interface RoadMapper {

    Road getRoadById(long id);

    List<Road> getAllRoads();

    boolean createRoad(Road road);

    boolean updateRoad(Road road);

    boolean deleteRoad(long id);
}
