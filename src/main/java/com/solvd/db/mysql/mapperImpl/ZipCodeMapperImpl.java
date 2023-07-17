package com.solvd.db.mysql.mapperImpl;

import com.solvd.db.mysql.mapper.ZipCodeMapper;
import com.solvd.model.ZipCode;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import java.util.List;

public class ZipCodeMapperImpl implements ZipCodeMapper {
    private final SqlSessionFactory sqlSessionFactory;

    public ZipCodeMapperImpl(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public ZipCode getZipCodeById(long id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            ZipCodeMapper mapper = session.getMapper(ZipCodeMapper.class);
            return mapper.getZipCodeById(id);
        }
    }

    @Override
    public ZipCode getZipCodeByCode(int code) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            ZipCodeMapper mapper = session.getMapper(ZipCodeMapper.class);
            return mapper.getZipCodeByCode(code);
        }
    }

    @Override
    public List<ZipCode> getAllZipCodes() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            ZipCodeMapper mapper = session.getMapper(ZipCodeMapper.class);
            return mapper.getAllZipCodes();
        }
    }
    @Override
    public boolean createZipCode(ZipCode zipCode) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            ZipCodeMapper mapper = session.getMapper(ZipCodeMapper.class);
            return mapper.createZipCode(zipCode);
        }
    }
    @Override
    public boolean updateZipCode(ZipCode zipCode) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            ZipCodeMapper mapper = session.getMapper(ZipCodeMapper.class);
            return mapper.updateZipCode(zipCode);
        }
    }
    @Override
    public boolean deleteZipCode(long id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            ZipCodeMapper mapper = session.getMapper(ZipCodeMapper.class);
            return mapper.deleteZipCode(id);
        }
    }
}
