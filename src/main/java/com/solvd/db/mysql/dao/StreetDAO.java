package com.solvd.db.mysql.dao;

import com.solvd.model.Street;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StreetDAO extends AbstractDAO<Street> {
    private static final Logger logger = LogManager.getLogger(StreetDAO.class);

    @Override
    public boolean create(Street street) {
        String insertQuery = "insert into streets (name) values(?)";
        try (PreparedStatement st = getConnection().prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
            st.setString(1, street.getName());
            if (st.executeUpdate()>0) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    long generatedId = rs.getLong(1);
                    logger.info("Street created with ID: " + generatedId);
                    return true;
                }
            } else {
                logger.warn("Failed to create street.");
                return false;
            }
        } catch (SQLException e) {
            logger.error("Error occurred while creating street.", e);
        }
        return false;
    }

    @Override
    public Street getById(long id) {
        Street street = new Street();
        String selectQuery = "select * from streets where id = ?";
        try (PreparedStatement st = getConnection().prepareStatement(selectQuery)) {
            st.setLong(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()){
                street = mapResultSetToObject(rs);
            }
        } catch (SQLException e) {
            logger.error("Error occurred while retrieving street.", e);
        }
        return street;
    }

    @Override
    public List<Street> getAll() {
        List<Street> allStreets = new ArrayList<>();
        try (PreparedStatement st = getConnection().prepareStatement("select * from streets")){
            ResultSet rs = st.executeQuery();
            while (rs.next()){
                Street street = mapResultSetToObject(rs);
                allStreets.add(street);
            }
        }catch (SQLException e){
            logger.error("Error occurred while retrieving all streets.", e);
        }
        return allStreets;
    }

    @Override
    public boolean update(Street street) {
        String updateQuery =  "update streets set name = ? where id = ?";
        try (PreparedStatement st = getConnection().prepareStatement(updateQuery)) {
            st.setString(1, street.getName());
            st.setLong(2, street.getId());
            int updatedRows = st.executeUpdate();
            return updatedRows > 0;
        } catch (SQLException e) {
            logger.error("Error occurred while updating street.", e);
        } return false;
    }

    @Override
    public boolean delete(long id) {
        String deleteQuery = "delete from streets where id = ?";
        try ( PreparedStatement st = getConnection().prepareStatement(deleteQuery)){
            st.setLong(1,id);
            int deletedRows = st.executeUpdate();
            return  deletedRows > 0;
        } catch (SQLException e) {
            logger.error("Error occurred while deleting street.", e);
        } return false;
    }

    private Street mapResultSetToObject(ResultSet rs) throws SQLException {
        long id = rs.getLong("id");
        String name = rs.getString("name");
        return new Street(id, name);
    }
}
