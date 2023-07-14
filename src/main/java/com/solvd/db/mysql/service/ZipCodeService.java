package com.solvd.db.mysql.service;

import com.solvd.db.mysql.mapper.ZipCodeMapper;
import com.solvd.db.utils.GenericDAO;
import com.solvd.model.ZipCode;
import java.util.List;

public class ZipCodeService implements GenericDAO<ZipCode> {
    private ZipCodeMapper zipCodeMapper;

    public ZipCodeService(ZipCodeMapper zipCodeMapper) {
        this.zipCodeMapper = zipCodeMapper;
    }

    @Override
    public boolean create(ZipCode zipCode) {
        return zipCodeMapper.createZipCode(zipCode);
    }

    @Override
    public ZipCode getById(long id) {
        return zipCodeMapper.getZipCodeById(id);
    }

    @Override
    public List<ZipCode> getAll() {
        return zipCodeMapper.getAllZipCodes();
    }

    @Override
    public boolean update(ZipCode zipCode) {
        return zipCodeMapper.updateZipCode(zipCode);
    }

    @Override
    public boolean delete(long id) {
        return zipCodeMapper.deleteZipCode(id);
    }
}
