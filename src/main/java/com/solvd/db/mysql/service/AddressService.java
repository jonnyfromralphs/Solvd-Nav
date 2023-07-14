package com.solvd.db.mysql.service;

import com.solvd.db.mysql.mapper.AddressMapper;
import com.solvd.db.utils.GenericDAO;
import com.solvd.model.Address;
import com.solvd.model.Road;
import java.util.List;

public class AddressService implements GenericDAO<Address> {
    private AddressMapper addressMapper;

    public AddressService(AddressMapper addressMapper) {
        this.addressMapper = addressMapper;
    }

    @Override
    public boolean create(Address address) {
        return addressMapper.createAddress(address);
    }

    @Override
    public Address getById(long id) {
        return addressMapper.getAddressById(id);
    }

    @Override
    public List<Address> getAll() {
        return addressMapper.getAllAddresses();
    }

    @Override
    public boolean update(Address address) {
        return addressMapper.updateAddress(address);
    }

    @Override
    public boolean delete(long id) {
        return addressMapper.deleteAddress(id);
    }

    public List<Road> getRoadsForAddress(long addressId) {
        return addressMapper.getRoadsForAddress(addressId);
    }
}
