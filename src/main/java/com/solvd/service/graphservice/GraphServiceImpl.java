package com.solvd.service.graphservice;

import com.solvd.db.mysql.mapperImpl.AddressMapperImpl;
import com.solvd.db.mysql.mapperImpl.BusStopMapperImpl;
import com.solvd.db.mysql.mapperImpl.RoadMapperImpl;
import com.solvd.db.utils.MyBatisUtil;
import com.solvd.exception.GraphCreationException;
import com.solvd.model.graph.Edge;
import com.solvd.model.graph.RoadNetworkGraph;
import com.solvd.model.*;
import com.solvd.model.graph.Vertex;
import com.solvd.service.AddressService;
import com.solvd.service.BusStopService;
import com.solvd.service.RoadService;
import org.apache.ibatis.session.SqlSessionFactory;
import java.util.List;

public class GraphServiceImpl implements GraphService {
    private RoadNetworkGraph graph;
    SqlSessionFactory sqlSessionFactory = MyBatisUtil.getSqlSessionFactory();
    AddressService addressService = new AddressService(new AddressMapperImpl(sqlSessionFactory));
    RoadService roadService = new RoadService(new RoadMapperImpl(sqlSessionFactory));
    BusStopService busStopService = new BusStopService(new BusStopMapperImpl(sqlSessionFactory));

    public GraphServiceImpl() { }

    @Override
    public RoadNetworkGraph loadGraphFromDatabase() throws GraphCreationException {
        List<Address> addresses = addressService.getAll();
        List<BusStop> busStops = busStopService.getAll();
        graph = new RoadNetworkGraph(addresses.size() + busStops.size());
        loadVerticesFromDatabase(addresses);
        loadBusStopsFromDatabase(busStops);
        loadEdgesFromDatabase();
        return graph;
    }

    @Override
    public void loadVerticesFromDatabase(List<Address> addresses) throws GraphCreationException {
        for (Address address : addresses) {
            Vertex vertex = new Vertex(address.toString(), address.getLatitude(), address.getLongitude());
            graph.addVertex(vertex);
        }
    }

    @Override
    public void loadEdgesFromDatabase() throws GraphCreationException {
        List<Road> addressRoads = roadService.getAllRoadsForAddresses();
        List<Road> busRoads = roadService.getAllRoadsForBusStops();
        for (Road road : addressRoads) {
            Vertex startVertex = new Vertex(road.getStartAddress().getLandmarkName(), road.getStartAddress().getLatitude(),
                    road.getStartAddress().getLongitude());
            Vertex endVertex = new Vertex(road.getEndAddress().getLandmarkName(), road.getEndAddress().getLatitude(),
                    road.getEndAddress().getLongitude());
            double weight = road.getSpeedLimit();
            Edge edge = new Edge(startVertex, endVertex, weight, road.getName());
            graph.addEdge(edge);
        }

        for (Road road : busRoads) {
            Vertex startVertex = new Vertex(road.getBusStopStartAddress().getName(), road.getBusStopStartAddress().getLatitude(),
                    road.getBusStopStartAddress().getLongitude());
            Vertex endVertex = new Vertex(road.getEndAddress().getLandmarkName(), road.getEndAddress().getLatitude(),
                    road.getEndAddress().getLongitude());
            double weight = road.getSpeedLimit();
            Edge edge = new Edge(startVertex, endVertex, weight, road.getName());
            graph.addEdge(edge);
        }
    }

    @Override
    public void loadBusStopsFromDatabase(List<BusStop> busStops) throws GraphCreationException {
        for (BusStop busStop : busStops) {
            double latitude = busStop.getLatitude();
            double longitude = busStop.getLongitude();
            String name = busStop.getName();
            int frequency = busStop.getFrequency();
            Vertex vertex = new Vertex(name, latitude, longitude, frequency);
            graph.addBusStop(vertex);
        }
    }
}
