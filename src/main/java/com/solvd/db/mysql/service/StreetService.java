package com.solvd.db.mysql.service;

import com.solvd.db.mysql.mapper.StreetMapper;
import com.solvd.db.utils.GenericDAO;
import com.solvd.model.Street;
import java.util.List;

public class StreetService implements GenericDAO<Street> {
    private StreetMapper streetMapper;

    public StreetService(StreetMapper streetMapper) {
        this.streetMapper = streetMapper;
    }

    @Override
    public boolean create(Street street) {
        return streetMapper.createStreet(street);
    }

    @Override
    public Street getById(long id) {
        return streetMapper.getStreetById(id);
    }

    @Override
    public List<Street> getAll() {
        return streetMapper.getAllStreets();
    }

    @Override
    public boolean update(Street street) {
        return streetMapper.updateStreet(street);
    }

    @Override
    public boolean delete(long id) {
        return streetMapper.deleteStreet(id);
    }
}
