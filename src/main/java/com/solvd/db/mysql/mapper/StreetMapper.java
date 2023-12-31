package com.solvd.db.mysql.mapper;

import com.solvd.model.Street;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
@Mapper
public interface StreetMapper {

    Street getStreetById(long id);

    Street getStreetByName(String name);

    List<Street> getAllStreets();

    boolean createStreet(Street street);
}
