package com.solvd.db.mysql.mapper;

import com.solvd.model.City;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
@Mapper
public interface CityMapper {

    City getCityById(long id);

    City getCityByName(String name);

    List<City> getAllCities();

    boolean createCity(City city);
}
