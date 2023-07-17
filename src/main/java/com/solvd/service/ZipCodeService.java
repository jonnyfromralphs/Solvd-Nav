package com.solvd.service;

import com.solvd.db.mysql.mapper.ZipCodeMapper;
import com.solvd.model.ZipCode;
import java.util.List;

public class ZipCodeService {
    private ZipCodeMapper zipCodeMapper;

    public ZipCodeService(ZipCodeMapper zipCodeMapper) {
        this.zipCodeMapper = zipCodeMapper;
    }

    public boolean create(ZipCode zipCode) {
        return zipCodeMapper.createZipCode(zipCode);
    }

    public ZipCode getById(long id) {
        return zipCodeMapper.getZipCodeById(id);
    }
    public ZipCode getByCode(int code) {
        return zipCodeMapper.getZipCodeByCode(code);
    }

    public List<ZipCode> getAll() {
        return zipCodeMapper.getAllZipCodes();
    }

    public boolean update(ZipCode zipCode) {
        return zipCodeMapper.updateZipCode(zipCode);
    }

    public boolean delete(long id) {
        return zipCodeMapper.deleteZipCode(id);
    }
}
