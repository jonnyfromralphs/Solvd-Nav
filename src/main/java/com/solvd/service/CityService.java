package com.solvd.service;

import com.solvd.db.mysql.mapper.CityMapper;
import com.solvd.model.City;

public class CityService {
    private CityMapper cityMapper;

    public CityService(CityMapper cityMapper) {
        this.cityMapper = cityMapper;
    }

    public boolean create(City city) {
        return cityMapper.createCity(city);
    }

    public City getByName(String name) { return cityMapper.getCityByName(name); }
}
