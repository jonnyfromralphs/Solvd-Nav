package com.solvd.db.mysql.mapper;

import com.solvd.model.Address;
import com.solvd.model.Road;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
@Mapper
public interface AddressMapper {

   Address getAddressById(long id);

   Address getAddressByName(String landmarkName);

   List<Address> getAllAddresses();

   boolean createAddress(Address address);

   List<Road> getRoadsForAddress(long roadId);
}
