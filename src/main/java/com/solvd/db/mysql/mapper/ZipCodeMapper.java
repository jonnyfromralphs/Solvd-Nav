package com.solvd.db.mysql.mapper;

import com.solvd.model.ZipCode;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
@Mapper
public interface ZipCodeMapper {

    ZipCode getZipCodeById(long id);

    ZipCode getZipCodeByCode(int code);

    List<ZipCode> getAllZipCodes();

    boolean createZipCode(ZipCode zipCode);
}
