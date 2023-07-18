package com.solvd.service;

import com.solvd.db.mysql.mapper.StreetMapper;
import com.solvd.model.Street;

public class StreetService {
    private StreetMapper streetMapper;

    public StreetService(StreetMapper streetMapper) {
        this.streetMapper = streetMapper;
    }

    public boolean create(Street street) {
        return streetMapper.createStreet(street);
    }

    public Street getByName(String name) { return streetMapper.getStreetByName(name); }
}
