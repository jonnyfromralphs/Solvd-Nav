package com.solvd.db.mysql.mapper;

import com.solvd.model.City;
import com.solvd.model.ZipCode;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
@Mapper
public interface CityMapper {

    City getCityById(long id);

    List<City> getAllCities();

    boolean createCity(City city);

    boolean updateCity(City city);

    boolean deleteCity(long id);

    List<ZipCode> getZipCodesForCity(long cityId);
}
