package com.solvd.db.mysql.mapper;

import com.solvd.model.Address;
import com.solvd.model.Road;
import java.util.List;

public interface AddressMapper {

   Address getAddressById(long id);

   List<Address> getAllAddresses();

   boolean createAddress(Address address);

   boolean updateAddress(Address address);

   boolean deleteAddress(long id);

   List<Road> getRoadsForAddress(long roadId);
}
