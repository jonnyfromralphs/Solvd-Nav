package com.solvd.service;

import com.solvd.db.mysql.mapper.CityMapper;
import com.solvd.model.City;
import com.solvd.model.ZipCode;
import java.util.List;

public class CityService {
    private CityMapper cityMapper;

    public CityService(CityMapper cityMapper) {
        this.cityMapper = cityMapper;
    }

    public boolean create(City city) {
        return cityMapper.createCity(city);
    }

    public City getById(long id) {
        return cityMapper.getCityById(id);
    }

    public City getByName(String name) { return cityMapper.getCityByName(name); }

    public List<City> getAll() {
        return cityMapper.getAllCities();
    }

    public boolean update(City city) {
        return cityMapper.updateCity(city);
    }

    public boolean delete(long id) {
        return cityMapper.deleteCity(id);
    }

    public List<ZipCode> getZipCodesForCity(long cityId) {
        return cityMapper.getZipCodesForCity(cityId);
    }
}
