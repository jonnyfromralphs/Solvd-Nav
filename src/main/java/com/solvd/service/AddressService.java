package com.solvd.service;

import com.solvd.db.mysql.mapper.AddressMapper;
import com.solvd.model.Address;
import com.solvd.model.Road;
import java.util.List;

public class AddressService {
    private AddressMapper addressMapper;

    public AddressService(AddressMapper addressMapper) {
        this.addressMapper = addressMapper;
    }

    public boolean create(Address address) {
        return addressMapper.createAddress(address);
    }

    public Address getById(long id) {
        return addressMapper.getAddressById(id);
    }

    public List<Address> getAll() {
        return addressMapper.getAllAddresses();
    }

    public boolean update(Address address) {
        return addressMapper.updateAddress(address);
    }

    public boolean delete(long id) {
        return addressMapper.deleteAddress(id);
    }

    public List<Road> getRoadsForAddress(long addressId) {
        return addressMapper.getRoadsForAddress(addressId);
    }
}
