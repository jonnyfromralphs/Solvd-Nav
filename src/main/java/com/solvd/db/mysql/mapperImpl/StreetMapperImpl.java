package com.solvd.db.mysql.mapperImpl;

import com.solvd.db.mysql.mapper.StreetMapper;
import com.solvd.model.Street;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import java.util.List;

public class StreetMapperImpl implements StreetMapper {
    private final SqlSessionFactory sqlSessionFactory;

    public StreetMapperImpl(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public Street getStreetById(long id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            StreetMapper mapper = session.getMapper(StreetMapper.class);
            return mapper.getStreetById(id);
        }
    }

    @Override
    public Street getStreetByName(String name) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            StreetMapper mapper = session.getMapper(StreetMapper.class);
            return mapper.getStreetByName(name);
        }
    }

    @Override
    public List<Street> getAllStreets() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            StreetMapper mapper = session.getMapper(StreetMapper.class);
            return mapper.getAllStreets();
        }
    }
    @Override
    public boolean createStreet(Street street) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            StreetMapper mapper = session.getMapper(StreetMapper.class);
            return mapper.createStreet(street);
        }
    }
}
