package com.solvd.db.mysql.service;

import com.solvd.db.mysql.mapper.CityMapper;
import com.solvd.db.utils.GenericDAO;
import com.solvd.model.City;
import com.solvd.model.ZipCode;
import java.util.List;

public class CityService implements GenericDAO<City> {
    private CityMapper cityMapper;

    public CityService(CityMapper cityMapper) {
        this.cityMapper = cityMapper;
    }

    @Override
    public boolean create(City city) {
        return cityMapper.createCity(city);
    }

    @Override
    public City getById(long id) {
        return cityMapper.getCityById(id);
    }

    @Override
    public List<City> getAll() {
        return cityMapper.getAllCities();
    }

    @Override
    public boolean update(City city) {
        return cityMapper.updateCity(city);
    }

    @Override
    public boolean delete(long id) {
        return cityMapper.deleteCity(id);
    }

    public List<ZipCode> getZipCodesForCity(long cityId) {
        return cityMapper.getZipCodesForCity(cityId);
    }
}
