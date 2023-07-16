package com.solvd.db.mysql.mapperImpl;

import com.solvd.db.mysql.mapper.BusStopMapper;
import com.solvd.model.BusStop;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import java.util.List;

public class BusStopMapperImpl implements BusStopMapper {
    private final SqlSessionFactory sqlSessionFactory;

    public BusStopMapperImpl(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public BusStop getBusStopById(long id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            BusStopMapper mapper = session.getMapper(BusStopMapper.class);
            return mapper.getBusStopById(id);
        }
    }
    @Override
    public List<BusStop> getAllBusStops() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            BusStopMapper mapper = session.getMapper(BusStopMapper.class);
            return mapper.getAllBusStops();
        }
    }
    @Override
    public boolean createBusStop(BusStop busStop) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            BusStopMapper mapper = session.getMapper(BusStopMapper.class);
            return mapper.createBusStop(busStop);
        }
    }
    @Override
    public boolean updateBusStop(BusStop busStop) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            BusStopMapper mapper = session.getMapper(BusStopMapper.class);
            return mapper.updateBusStop(busStop);
        }
    }
    @Override
    public boolean deleteBusStop(long id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            BusStopMapper mapper = session.getMapper(BusStopMapper.class);
            return mapper.deleteBusStop(id);
        }
    }
}
