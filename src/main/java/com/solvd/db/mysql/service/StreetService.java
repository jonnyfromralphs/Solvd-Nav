package com.solvd.db.mysql.service;

import com.solvd.db.mysql.mapper.StreetMapper;
import com.solvd.model.Street;
import java.util.List;

public class StreetService {
    private StreetMapper streetMapper;

    public StreetService(StreetMapper streetMapper) {
        this.streetMapper = streetMapper;
    }

    public boolean create(Street street) {
        return streetMapper.createStreet(street);
    }

    public Street getById(long id) {
        return streetMapper.getStreetById(id);
    }

    public List<Street> getAll() {
        return streetMapper.getAllStreets();
    }

    public boolean update(Street street) {
        return streetMapper.updateStreet(street);
    }

    public boolean delete(long id) {
        return streetMapper.deleteStreet(id);
    }
}
