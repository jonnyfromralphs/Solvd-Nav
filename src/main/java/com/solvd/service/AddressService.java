package com.solvd.service;

import com.solvd.db.mysql.mapper.AddressMapper;
import com.solvd.model.Address;
import java.util.List;

public class AddressService {
    private AddressMapper addressMapper;

    public AddressService(AddressMapper addressMapper) {
        this.addressMapper = addressMapper;
    }

    public boolean create(Address address) {
        return addressMapper.createAddress(address);
    }

    public Address getByName(String landmarkname) {
        return addressMapper.getAddressByName(landmarkname);
    }

    public List<Address> getAll() {
        return addressMapper.getAllAddresses();
    }
}
