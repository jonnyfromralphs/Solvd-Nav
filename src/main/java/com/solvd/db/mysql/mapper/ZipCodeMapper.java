package com.solvd.db.mysql.mapper;

import com.solvd.model.ZipCode;
import java.util.List;

public interface ZipCodeMapper {

    ZipCode getZipCodeById(long id);

    List<ZipCode> getAllZipCodes();

    boolean createZipCode(ZipCode zipCode);

    boolean updateZipCode(ZipCode zipCode);

    boolean deleteZipCode(long id);
}
