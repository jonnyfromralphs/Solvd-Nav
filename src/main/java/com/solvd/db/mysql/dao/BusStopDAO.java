package com.solvd.db.mysql.dao;

import com.solvd.model.BusStop;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BusStopDAO extends AbstractDAO<BusStop> {
    private static final Logger logger = LogManager.getLogger(BusStopDAO.class);

    @Override
    public boolean create(BusStop busStop) {
        String insertQuery = "insert into bus_stops (name, latitude, longitude, frequency) values (?, ?, ?, ?)";
        try (PreparedStatement st = getConnection().prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
            st.setString(1, busStop.getName());
            st.setDouble(2, busStop.getLatitude());
            st.setDouble(3, busStop.getLongitude());
            st.setInt(4, busStop.getFrequency());

            if (st.executeUpdate() > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    long generatedId = rs.getLong(1);
                    busStop.setId(generatedId);
                    logger.info("Bus stop created with ID: " + generatedId);
                    return true;
                }
            } else {
                logger.warn("Failed to create bus stop.");
                return false;
            }
        } catch (SQLException e) {
            logger.error("Error occurred while creating bus stop.", e);
        }
        return false;
    }

    @Override
    public BusStop getById(long id) {
        BusStop busStop = new BusStop();
        String selectQuery = "select * from bus_stops where id = ?";
        try (PreparedStatement st = getConnection().prepareStatement(selectQuery)) {
            st.setLong(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                busStop = mapResultSetToObject(rs);
            }
        } catch (SQLException e) {
            logger.error("Error occurred while retrieving bus stop.", e);
        }
        return busStop;
    }

    @Override
    public List<BusStop> getAll() {
        List<BusStop> allBusStops = new ArrayList<>();
        String selectQuery = "select * from bus_stops";
        try (PreparedStatement st = getConnection().prepareStatement(selectQuery)) {
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                BusStop busStop = mapResultSetToObject(rs);
                allBusStops.add(busStop);
            }
        } catch (SQLException e) {
            logger.error("Error occurred while retrieving all bus stops.", e);
        }
        return allBusStops;
    }

    @Override
    public boolean update(BusStop busStop) {
        String updateQuery = "update bus_stops set name = ?, latitude = ?, longitude = ?, frequency = ? where id = ?";
        try (PreparedStatement st = getConnection().prepareStatement(updateQuery)) {
            st.setString(1, busStop.getName());
            st.setDouble(2, busStop.getLatitude());
            st.setDouble(3, busStop.getLongitude());
            st.setInt(4, busStop.getFrequency());
            st.setLong(5, busStop.getId());

            int updatedRows = st.executeUpdate();
            return updatedRows > 0;
        } catch (SQLException e) {
            logger.error("Error occurred while updating bus stop.", e);
        }
        return false;
    }

    @Override
    public boolean delete(long id) {
        String deleteQuery = "delete from bus_stops where id = ?";
        try (PreparedStatement st = getConnection().prepareStatement(deleteQuery)) {
            st.setLong(1, id);
            int deletedRows = st.executeUpdate();
            return deletedRows > 0;
        } catch (SQLException e) {
            logger.error("Error occurred while deleting bus stop.", e);
        }
        return false;
    }

    private BusStop mapResultSetToObject(ResultSet rs) throws SQLException {
        long id = rs.getLong("id");
        String name = rs.getString("name");
        double latitude = rs.getDouble("latitude");
        double longitude = rs.getDouble("longitude");
        int frequency = rs.getInt("frequency");
        return new BusStop(id, name, latitude, longitude, frequency);
    }
}
