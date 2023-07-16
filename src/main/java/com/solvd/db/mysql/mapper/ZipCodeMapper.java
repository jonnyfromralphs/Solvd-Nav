package com.solvd.db.mysql.mapper;

import com.solvd.model.ZipCode;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
@Mapper
public interface ZipCodeMapper {

    ZipCode getZipCodeById(long id);

    List<ZipCode> getAllZipCodes();

    boolean createZipCode(ZipCode zipCode);

    boolean updateZipCode(ZipCode zipCode);

    boolean deleteZipCode(long id);
}
