package com.solvd.db.mysql.dao;

import com.solvd.model.Address;
import com.solvd.model.Road;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RoadDAO extends AbstractDAO<Road> {
    private static final Logger logger = LogManager.getLogger(RoadDAO.class);

    @Override
    public boolean create(Road road) {
        String insertQuery = "insert into roads (name, start_address_id, end_address_id, speed_limit) values(?, ?, ?, ?)";
        try (PreparedStatement st = getConnection().prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
            st.setString(1, road.getName());
            st.setLong(2, road.getStartAddress().getId());
            st.setLong(3, road.getEndAddress().getId());
            st.setInt(4, road.getSpeedLimit());
            if (st.executeUpdate()>0) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    long generatedId = rs.getLong(1);
                    logger.info("Road created with ID: " + generatedId);
                    return true;
                }
            } else {
                logger.warn("Failed to create road.");
                return false;
            }
        } catch (SQLException e) {
            logger.error("Error occurred while creating road.", e);
        }
        return false;
    }

    @Override
    public Road getById(long id) {
        Road road = new Road();
        String selectQuery = "select r.id, r.name, r.speed_limit, a1.id as start_address_id, " +
                "a2.id as end_address_id from roads r " +
                "join addresses a1 on r.start_address_id = a1.id " +
                "join addresses a2 on r.end_address_id = a2.id " +
                "where r.id = ?";
        try (PreparedStatement st = getConnection().prepareStatement(selectQuery)) {
            st.setLong(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                road = mapResultSetToObject(rs);
            }
        } catch (SQLException e) {
            logger.error("Error occurred while retrieving road.", e);
        }
        return road;
    }

    @Override
    public List<Road> getAll() {
        List<Road> allRoads = new ArrayList<>();
        String selectQuery = "select r.id, r.name, r.speed_limit, a1.id as start_address_id, " +
                "a2.id as end_address_id from roads r " +
                "join addresses a1 on r.start_address_id = a1.id " +
                "join addresses a2 on r.end_address_id = a2.id";
        try (PreparedStatement st = getConnection().prepareStatement(selectQuery)) {
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Road road = mapResultSetToObject(rs);
                allRoads.add(road);
            }
        } catch (SQLException e) {
            logger.error("Error occurred while retrieving all roads.", e);
        }
        return allRoads;
    }

    @Override
    public boolean update(Road road) {
        String updateQuery = "update roads set name = ?, start_address_id = ?, end_address_id = ?, speed_limit = ? where id = ?";
        try (PreparedStatement st = getConnection().prepareStatement(updateQuery)) {
            st.setString(1, road.getName());
            st.setLong(2, road.getStartAddress().getId());
            st.setLong(3, road.getEndAddress().getId());
            st.setInt(4, road.getSpeedLimit());
            st.setLong(5, road.getId());
            int updatedRows = st.executeUpdate();
            return updatedRows > 0;
        } catch (SQLException e) {
            logger.error("Error occurred while updating road.", e);
        }
        return false;
    }

    @Override
    public boolean delete(long id) {
        String deleteQuery = "delete from roads where id = ?";
        try (PreparedStatement st = getConnection().prepareStatement(deleteQuery)) {
            st.setLong(1, id);
            int deletedRows = st.executeUpdate();
            return deletedRows > 0;
        } catch (SQLException e) {
            logger.error("Error occurred while deleting road.", e);
        }
        return false;
    }

    private Road mapResultSetToObject(ResultSet rs) throws SQLException {
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
