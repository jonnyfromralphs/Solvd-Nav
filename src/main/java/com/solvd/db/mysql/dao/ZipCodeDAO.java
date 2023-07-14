package com.solvd.db.mysql.dao;

import com.solvd.model.ZipCode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ZipCodeDAO extends AbstractDAO<ZipCode> {
    private static final Logger logger = LogManager.getLogger(ZipCodeDAO.class);

    @Override
    public boolean create(ZipCode zipCode) {
        String insertQuery = "insert into zip_codes (code, city_id) values(?, ?)";
        try (PreparedStatement st = getConnection().prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
            st.setInt(1, zipCode.getCode());
            st.setLong(2, zipCode.getCityId());
            if (st.executeUpdate()>0) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    long generatedId = rs.getLong(1);
                    logger.info("Zip code created with ID: " + generatedId);
                    return true;
                }
            } else {
                logger.warn("Failed to create zip code.");
                return false;
            }
        } catch (SQLException e) {
            logger.error("Error occurred while creating zip code.", e);
        }
        return false;
    }

    @Override
    public ZipCode getById(long id) {
        ZipCode zipCode = new ZipCode();
        String selectQuery = "select * from zip_codes where id = ?";
        try (PreparedStatement st = getConnection().prepareStatement(selectQuery)) {
            st.setLong(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()){
                zipCode = mapResultSetToObject(rs);
            }
        } catch (SQLException e) {
            logger.error("Error occurred while retrieving zip code.", e);
        }
        return zipCode;
    }

    @Override
    public List<ZipCode> getAll() {
        List<ZipCode> allZipCodes = new ArrayList<>();
        try (PreparedStatement st = getConnection().prepareStatement("select * from zip_codes");
            ResultSet rs = st.executeQuery()) {
            while (rs.next()){
                ZipCode zip = mapResultSetToObject(rs);
                allZipCodes.add(zip);
            }
        }catch (SQLException e){
            logger.error("Error occurred while retrieving all zip codes.", e);
        }
        return allZipCodes;
    }

    @Override
    public boolean update(ZipCode zipCode) {
        String updateQuery =  "update zip_codes set code = ?, city_id = ? where id = ?";
        try (PreparedStatement st = getConnection().prepareStatement(updateQuery)) {
            st.setInt(1, zipCode.getCode());
            st.setLong(2, zipCode.getCityId());
            st.setLong(3, zipCode.getId());
            int updatedRows = st.executeUpdate();
            return updatedRows > 0;
        } catch (SQLException e) {
            logger.error("Error occurred while updating zip code.", e);
        } return false;
    }

    @Override
    public boolean delete(long id) {
        String deleteQuery = "delete from zip_codes where id = ?";
        try ( PreparedStatement st = getConnection().prepareStatement(deleteQuery)){
            st.setLong(1,id);
            int deletedRows = st.executeUpdate();
            return  deletedRows > 0;
        } catch (SQLException e) {
            logger.error("Error occurred while deleting zip code.", e);
        } return false;
    }

    private ZipCode mapResultSetToObject(ResultSet rs) throws SQLException {
        long id = rs.getLong("id");
        int code = rs.getInt("code");
        long cityId = rs.getLong("city_id");
        return new ZipCode(id, code, cityId);
    }
}
