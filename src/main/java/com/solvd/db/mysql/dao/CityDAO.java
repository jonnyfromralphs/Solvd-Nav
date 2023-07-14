package com.solvd.db.mysql.dao;

import com.solvd.model.City;
import com.solvd.model.ZipCode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CityDAO extends AbstractDAO<City> {
    private static final Logger logger = LogManager.getLogger(CityDAO.class);

    @Override
    public boolean create(City city) {
        String insertQuery = "insert into cities (name) values(?)";
        try (PreparedStatement st = getConnection().prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
            st.setString(1, city.getName());
            if (st.executeUpdate()>0) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    long generatedId = rs.getLong(1);
                    logger.info("City created with ID: " + generatedId);
                    return true;
                }
            } else {
                logger.warn("Failed to create city.");
                return false;
            }
        } catch (SQLException e) {
            logger.error("Error occurred while creating city.", e);
        }return false;
    }

    @Override
    public City getById(long id) {
        City city = new City();
        String selectQuery = "select * from cities where id = ?";
        try (PreparedStatement st = getConnection().prepareStatement(selectQuery)) {
            st.setLong(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()){
                city = mapResultSetToObject(rs);
            }
        } catch (SQLException e) {
            logger.error("Error occurred while retrieving city.", e);
        }
        return city;
    }

    @Override
    public List<City> getAll() {
        List<City> allCities = new ArrayList<>();
        try (PreparedStatement st = getConnection().prepareStatement("select * from cities")){
            ResultSet rs = st.executeQuery();
            while (rs.next()){
                City city = mapResultSetToObject(rs);
                allCities.add(city);
            }
        }catch (SQLException e){
            logger.error("Error occurred while retrieving all cities.", e);
        }
        return allCities;
    }

    @Override
    public boolean update(City city) {
        String updateQuery =  "update cities set name = ? where id = ?";
        try (PreparedStatement st = getConnection().prepareStatement(updateQuery)) {
            st.setString(1, city.getName());
            st.setLong(2, city.getId());
            int updatedRows = st.executeUpdate();
            return updatedRows > 0;
        } catch (SQLException e) {
            logger.error("Error occurred while updating city.", e);
        } return false;
    }

    @Override
    public boolean delete(long id) {
        String deleteQuery = "delete from cities where id = ?";
        try ( PreparedStatement st = getConnection().prepareStatement(deleteQuery)){
            st.setLong(1,id);
            int deletedRows = st.executeUpdate();
            return  deletedRows > 0;
        } catch (SQLException e) {
            logger.error("Error occurred while deleting city.", e);
        } return false;
    }

    public List<ZipCode> getZipCodesForCity(long cityId) {
        List<ZipCode> zipCodes = new ArrayList<>();
        String selectQuery = "select * from zip_codes where city_id = ?";
        try (Connection connection = getConnection();
             PreparedStatement st = connection.prepareStatement(selectQuery)) {
            st.setLong(1, cityId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                long zipCodeId = rs.getLong("id");
                int code = rs.getInt("code");
                long retrievedCityId = rs.getLong("city_id");
                ZipCode zipCode = new ZipCode(zipCodeId, code, retrievedCityId);
                zipCodes.add(zipCode);
            }
        } catch (SQLException e) {
            logger.error("Error occurred while retrieving zip codes for city with ID: " + cityId, e);
        } return zipCodes;
    }

    private City mapResultSetToObject(ResultSet rs) throws SQLException {
        long id = rs.getLong("id");
        String name = rs.getString("name");
        return new City(id, name);
    }
}
