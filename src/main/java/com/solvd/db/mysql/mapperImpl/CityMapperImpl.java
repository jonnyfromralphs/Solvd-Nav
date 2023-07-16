package com.solvd.db.mysql.mapperImpl;

import com.solvd.db.mysql.mapper.CityMapper;
import com.solvd.model.City;
import com.solvd.model.ZipCode;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import java.util.List;

public class CityMapperImpl implements CityMapper {
    private final SqlSessionFactory sqlSessionFactory;

    public CityMapperImpl(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public City getCityById(long id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            CityMapper mapper = session.getMapper(CityMapper.class);
            return mapper.getCityById(id);
        }
    }
    @Override
    public List<City> getAllCities() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            CityMapper mapper = session.getMapper(CityMapper.class);
            return mapper.getAllCities();
        }
    }
    @Override
    public boolean createCity(City city) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            CityMapper mapper = session.getMapper(CityMapper.class);
            return mapper.createCity(city);
        }
    }
    @Override
    public boolean updateCity(City city) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            CityMapper mapper = session.getMapper(CityMapper.class);
            return mapper.updateCity(city);
        }
    }
    @Override
    public boolean deleteCity(long id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            CityMapper mapper = session.getMapper(CityMapper.class);
            return mapper.deleteCity(id);
        }
    }
    @Override
    public List<ZipCode> getZipCodesForCity(long cityId) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            CityMapper mapper = session.getMapper(CityMapper.class);
            return mapper.getZipCodesForCity(cityId);
        }
    }
}
