package com.solvd.service;

import com.solvd.db.mysql.mapper.ZipCodeMapper;
import com.solvd.model.ZipCode;

public class ZipCodeService {
    private ZipCodeMapper zipCodeMapper;

    public ZipCodeService(ZipCodeMapper zipCodeMapper) {
        this.zipCodeMapper = zipCodeMapper;
    }

    public boolean create(ZipCode zipCode) {
        return zipCodeMapper.createZipCode(zipCode);
    }

    public ZipCode getByCode(int code) {
        return zipCodeMapper.getZipCodeByCode(code);
    }
}
