package com.solvd.db.mysql.mapper;

import com.solvd.model.Road;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
@Mapper
public interface RoadMapper {

    Road getRoadById(long id);

    List<Road> getAllRoads();

    boolean createRoad(Road road);

    boolean updateRoad(Road road);

    boolean deleteRoad(long id);
}
