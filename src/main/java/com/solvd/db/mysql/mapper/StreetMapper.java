package com.solvd.db.mysql.mapper;

import com.solvd.model.Street;
import java.util.List;

public interface StreetMapper {

    Street getStreetById(long id);

    List<Street> getAllStreets();

    boolean createStreet(Street street);

    boolean updateStreet(Street street);

    boolean deleteStreet(long id);
}
