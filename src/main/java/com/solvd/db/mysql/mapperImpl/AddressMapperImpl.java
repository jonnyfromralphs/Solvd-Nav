package com.solvd.db.mysql.mapperImpl;

import com.solvd.db.mysql.mapper.AddressMapper;
import com.solvd.model.Address;
import com.solvd.model.Road;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import java.util.List;

public class AddressMapperImpl implements AddressMapper {
    private final SqlSessionFactory sqlSessionFactory;

    public AddressMapperImpl(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public Address getAddressById(long id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            AddressMapper mapper = session.getMapper(AddressMapper.class);
            return mapper.getAddressById(id);
        }
    }

    @Override
    public Address getAddressByName(String landmarkName) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            AddressMapper mapper = session.getMapper(AddressMapper.class);
            return mapper.getAddressByName(landmarkName);
        }
    }

    @Override
    public List<Address> getAllAddresses() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            AddressMapper mapper = session.getMapper(AddressMapper.class);
            return mapper.getAllAddresses();
        }
    }
    @Override
    public boolean createAddress(Address address) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            AddressMapper mapper = session.getMapper(AddressMapper.class);
            return mapper.createAddress(address);
        }
    }
    @Override
    public boolean updateAddress(Address address) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            AddressMapper mapper = session.getMapper(AddressMapper.class);
            return mapper.updateAddress(address);
        }
    }
    @Override
    public boolean deleteAddress(long id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            AddressMapper mapper = session.getMapper(AddressMapper.class);
            return mapper.deleteAddress(id);
        }
    }
    @Override
    public List<Road> getRoadsForAddress(long roadId) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            AddressMapper mapper = session.getMapper(AddressMapper.class);
            return mapper.getRoadsForAddress(roadId);
        }
    }
}
