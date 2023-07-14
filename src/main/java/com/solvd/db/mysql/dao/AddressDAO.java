package com.solvd.db.mysql.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.solvd.model.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AddressDAO extends AbstractDAO<Address> {
    private static final Logger logger = LogManager.getLogger(AddressDAO.class);

    @Override
    public boolean create(Address address) {
        String insertQuery = "insert into addresses (house_number, street_id, city_id, state, zip_code_id, " +
                "longitude, latitude, landmark_name) values (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement st = getConnection().prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
            st.setString(1, address.getHouseNumber());
            st.setLong(2, address.getStreet().getId());
            st.setLong(3, address.getCity().getId());
            st.setString(4, address.getState());
            st.setLong(5, address.getZipCode().getId());
            st.setDouble(6, address.getLongitude());
            st.setDouble(7, address.getLatitude());
            st.setString(8, address.getLandmarkName());

            if (st.executeUpdate() > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    long generatedId = rs.getLong(1);
                    address.setId(generatedId);
                    logger.info("Address created with ID: " + generatedId);
                    return true;
                }
            } else {
                logger.warn("Failed to create address.");
                return false;
            }
        } catch (SQLException e) {
            logger.error("Error occurred while creating address.", e);
        }
        return false;
    }

    @Override
    public Address getById(long id) {
        Address address = new Address();
        String selectQuery = "select a.id, a.house_number, a.street_id, s.name as street_name, a.city_id, c.name as city_name, " +
                "a.state, a.zip_code_id, z.code as zip_code, a.longitude, a.latitude, a.landmark_name " +
                "from addresses a " +
                "join streets s on a.street_id = s.id " +
                "join cities c on a.city_id = c.id " +
                "join zip_codes z on a.zip_code_id = z.id " +
                "where a.id = ?";
        try (PreparedStatement st = getConnection().prepareStatement(selectQuery)) {
            st.setLong(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                address = mapResultSetToObject(rs);
            }
        } catch (SQLException e) {
            logger.error("Error occurred while retrieving address.", e);
        }
        return address;
    }

    @Override
    public List<Address> getAll() {
        List<Address> allAddresses = new ArrayList<>();
        String selectQuery = "select a.id, a.house_number, a.street_id, s.name as street_name, a.city_id, c.name as city_name, " +
                "a.state, a.zip_code_id, z.code as zip_code, a.longitude, a.latitude, a.landmark_name " +
                "from addresses a " +
                "join streets s on a.street_id = s.id " +
                "join cities c on a.city_id = c.id " +
                "join zip_codes z on a.zip_code_id = z.id";
        try (PreparedStatement st = getConnection().prepareStatement(selectQuery)) {
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Address address = mapResultSetToObject(rs);
                allAddresses.add(address);
            }
        } catch (SQLException e) {
            logger.error("Error occurred while retrieving all addresses.", e);
        }
        return allAddresses;
    }

    @Override
    public boolean update(Address address) {
        String updateQuery = "update addresses set house_number = ?, street_id = ?, city_id = ?, state = ?, " +
                "zip_code_id = ?, longitude = ?, latitude = ?, landmark_name = ? where id = ?";
        try (PreparedStatement st = getConnection().prepareStatement(updateQuery)) {
            st.setString(1, address.getHouseNumber());
            st.setLong(2, address.getStreet().getId());
            st.setLong(3, address.getCity().getId());
            st.setString(4, address.getState());
            st.setLong(5, address.getZipCode().getId());
            st.setDouble(6, address.getLongitude());
            st.setDouble(7, address.getLatitude());
            st.setString(8, address.getLandmarkName());
            st.setLong(9, address.getId());
            int updatedRows = st.executeUpdate();
            return updatedRows > 0;
        } catch (SQLException e) {
            logger.error("Error occurred while updating address.", e);
        }
        return false;
    }

    @Override
    public boolean delete(long id) {
        String deleteQuery = "delete from addresses where id = ?";
        try (PreparedStatement st = getConnection().prepareStatement(deleteQuery)) {
            st.setLong(1, id);
            int deletedRows = st.executeUpdate();
            return deletedRows > 0;
        } catch (SQLException e) {
            logger.error("Error occurred while deleting address.", e);
        }
        return false;
    }

    public List<Road> getRoadsForAddress(Address address) {
        List<Road> roads = new ArrayList<>();
        String selectQuery = "select r.id, r.name, r.speed_limit, " +
                "a1.id as start_address_id, " +
                "a2.id as end_address_id from roads r " +
                "join addresses a1 on r.start_address_id = a1.id " +
                "join addresses a2 on r.end_address_id = a2.id " +
                "where r.start_address_id = ? or r.end_address_id = ?";
        try (PreparedStatement st = getConnection().prepareStatement(selectQuery)) {
            st.setLong(1, address.getId());
            st.setLong(2, address.getId());
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Road road = mapResultSetToRoad(rs);
                roads.add(road);
            }
        } catch (SQLException e) {
            logger.error("Error occurred while retrieving roads for address.", e);
        }
        return roads;
    }

    private Address mapResultSetToObject(ResultSet rs) throws SQLException {
        Address address = new Address();
        address.setId(rs.getLong("id"));
        address.setHouseNumber(rs.getString("house_number"));

        Street street = new Street();
        street.setId(rs.getLong("street_id"));
        street.setName(rs.getString("street_name"));
        address.setStreet(street);

        City city = new City();
        city.setId(rs.getLong("city_id"));
        city.setName(rs.getString("city_name"));
        address.setCity(city);

        address.setState(rs.getString("state"));

        ZipCode zipCode = new ZipCode();
        zipCode.setId(rs.getLong("zip_code_id"));
        zipCode.setCode(rs.getInt("zip_code"));
        address.setZipCode(zipCode);

        address.setLongitude(rs.getDouble("longitude"));
        address.setLatitude(rs.getDouble("latitude"));
        address.setLandmarkName(rs.getString("landmark_name"));

        return address;
    }

    private Road mapResultSetToRoad(ResultSet rs) throws SQLException {
        long id = rs.getLong("id");
        String name = rs.getString("name");
        int speedLimit = rs.getInt("speed_limit");
        long startAddressId = rs.getLong("start_address_id");
        long endAddressId = rs.getLong("end_address_id");
        Address startAddress = new Address(startAddressId);
        Address endAddress = new Address(endAddressId);
        return new Road(id, name, startAddress, endAddress, speedLimit);
    }
}
