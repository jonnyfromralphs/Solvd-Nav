package com.solvd;

import com.solvd.db.mysql.mapper.AddressMapper;
import com.solvd.db.mysql.mapper.BusStopMapper;
import com.solvd.db.mysql.mapper.RoadMapper;
import com.solvd.db.mysql.mapperImpl.AddressMapperImpl;
import com.solvd.db.mysql.mapperImpl.BusStopMapperImpl;
import com.solvd.db.mysql.mapperImpl.RoadMapperImpl;
import com.solvd.db.utils.ConnectionPool;
import com.solvd.db.utils.MyBatisUtil;
import com.solvd.model.BusStop;
import com.solvd.model.graph.RoadNetworkGraph;
import com.solvd.model.Address;
import com.solvd.model.Road;
import com.solvd.service.AddressService;
import com.solvd.service.graphservice.GraphServiceImpl;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class LoadDBTest {
    public static void main( String[] args) {
        ConnectionPool.loadPropertyConfigFile();
        SqlSessionFactory sqlSessionFactory = MyBatisUtil.getSqlSessionFactory();
        AddressService addressService = new AddressService(new AddressMapperImpl(sqlSessionFactory));
//        AddressMapper addressMapper = new AddressMapperImpl(sqlSessionFactory);
//        List<Address> addresses = addressMapper.getAllAddresses();
////        addresses.forEach(a -> System.out.println(a));
//        RoadMapper roadMapper = new RoadMapperImpl(sqlSessionFactory);
//        List<Road> roads = roadMapper.getAllRoads();
//        roads.forEach(r -> System.out.println(r));
//
        GraphServiceImpl graphService = new GraphServiceImpl();
        RoadNetworkGraph graph = graphService.loadGraphFromDatabase();
//        BusStopMapper busStopMapper = new BusStopMapperImpl(sqlSessionFactory);
//        List<BusStop> busStops = busStopMapper.getAllBusStops();
//        busStops.forEach(b -> System.out.println(b));
    }
}
