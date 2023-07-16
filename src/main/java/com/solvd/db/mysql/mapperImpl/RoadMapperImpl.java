package com.solvd.db.mysql.mapperImpl;

import com.solvd.db.mysql.mapper.RoadMapper;
import com.solvd.model.Road;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import java.util.List;

public class RoadMapperImpl implements RoadMapper {
    private final SqlSessionFactory sqlSessionFactory;

    public RoadMapperImpl(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public Road getRoadById(long id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            RoadMapper mapper = session.getMapper(RoadMapper.class);
            return mapper.getRoadById(id);
        }
    }
    @Override
    public List<Road> getAllRoadsForAddresses() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            RoadMapper mapper = session.getMapper(RoadMapper.class);
            return mapper.getAllRoadsForAddresses();
        }
    }

    @Override
    public List<Road> getAllRoadsForBusStops() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            RoadMapper mapper = session.getMapper(RoadMapper.class);
            return mapper.getAllRoadsForBusStops();
        }
    }
    @Override
    public boolean createRoad(Road road) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            RoadMapper mapper = session.getMapper(RoadMapper.class);
            return mapper.createRoad(road);
        }
    }
    @Override
    public boolean updateRoad(Road road) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            RoadMapper mapper = session.getMapper(RoadMapper.class);
            return mapper.updateRoad(road);
        }
    }
    @Override
    public boolean deleteRoad(long id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            RoadMapper mapper = session.getMapper(RoadMapper.class);
            return mapper.deleteRoad(id);
        }
    }
}
