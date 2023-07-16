package com.solvd;

import com.solvd.db.mysql.mapper.*;
import com.solvd.db.mysql.mapperImpl.*;
import com.solvd.db.utils.ConnectionPool;
import com.solvd.db.utils.MyBatisUtil;
import com.solvd.model.*;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );

        ConnectionPool.loadPropertyConfigFile();
        SqlSessionFactory sqlSessionFactory = MyBatisUtil.getSqlSessionFactory();
        AddressMapper addressMapper = new AddressMapperImpl(sqlSessionFactory);
        long addressId = 3;
        Address address = addressMapper.getAddressById(addressId);
        System.out.println(address);
        List<Address> addresses = addressMapper.getAllAddresses();
        for (Address address1 : addresses) {
            System.out.println(address1);
        }
        System.out.println("================================================================================");

        BusStopMapper busStopMapper = new BusStopMapperImpl(sqlSessionFactory);
        List<BusStop> busStops = busStopMapper.getAllBusStops();
        for (BusStop busStop : busStops) {
            System.out.println(busStop);
        }
        System.out.println("================================================================================");

        RoadMapper roadMapper = new RoadMapperImpl(sqlSessionFactory);
        long roadId = 3;
        Road road = roadMapper.getRoadById(roadId);
        System.out.println(road);
        List<Road> roads = roadMapper.getAllRoads();
        for (Road road1 : roads) {
            System.out.println(road1);
        }
        System.out.println("================================================================================");

        CityMapper cityMapper = new CityMapperImpl(sqlSessionFactory);
        long cityId = 1;
        City city = cityMapper.getCityById(cityId);
        System.out.println(city);
        System.out.println("================================================================================");

        StreetMapper streetMapper = new StreetMapperImpl(sqlSessionFactory);
        long streetId = 3;
        Street street = streetMapper.getStreetById(streetId);
        System.out.println(street);
        List<Street> streets = streetMapper.getAllStreets();
        for (Street street1 : streets) {
            System.out.println(street1);
        }
        System.out.println("================================================================================");

        ZipCodeMapper zipCodeMapper = new ZipCodeMapperImpl(sqlSessionFactory);
        long zipCodeId = 3;
        ZipCode zipCode = zipCodeMapper.getZipCodeById(zipCodeId);
        System.out.println(zipCode);
        List<ZipCode> zipCodes = zipCodeMapper.getAllZipCodes();
        for (ZipCode zip : zipCodes) {
            System.out.println(zip);
        }
        System.out.println("================================================================================");
    }
}
