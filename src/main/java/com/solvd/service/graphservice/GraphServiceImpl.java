package com.solvd.service.graphservice;

import com.solvd.db.mysql.mapper.AddressMapper;
import com.solvd.db.mysql.mapper.BusStopMapper;
import com.solvd.db.mysql.mapper.RoadMapper;
import com.solvd.db.mysql.mapperImpl.AddressMapperImpl;
import com.solvd.db.mysql.mapperImpl.BusStopMapperImpl;
import com.solvd.db.mysql.mapperImpl.RoadMapperImpl;
import com.solvd.db.utils.MyBatisUtil;
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
    public RoadNetworkGraph loadGraphFromDatabase() {
        List<Address> addresses = addressService.getAll();
        List<BusStop> busStops = busStopService.getAll();
        graph = new RoadNetworkGraph(addresses.size() + busStops.size());
        loadVerticesFromDatabase(addresses);
        loadBusStopsFromDatabase(busStops);
        loadEdgesFromDatabase();
        return graph;
    }

    // This method could be used for loading a list of vertices
    @Override
    public void loadVerticesFromDatabase(List<Address> addresses) {
        // The below line should be like this List<Address> vertexDataList = new ArrayList<>(); Since i dont have an aaddress. its giving me error.
        //  Retrieve edge data using myBatis. please use an appropriate data structure
        // i just used arrayList just for a demo.
        for (Address address : addresses) {
            Vertex vertex = new Vertex(address.toString(), address.getLatitude(), address.getLongitude()); // create vertex object here using the vertexData
            graph.addVertex(vertex);
        }
    }


    // This method could be used for loading the list of edges of our graph
    @Override
    public void loadEdgesFromDatabase() {
        // List<Road> edgedataList = new ArrayList<>();
        List<Road> addressRoads = roadService.getAllRoadsForAddresses();
        List<Road> busRoads = roadService.getAllRoadsForBusStops(); // Retrieve edge data using myBatis .

        // Create and add edges to the graph
        for (Road road : addressRoads) {
            Vertex startVertex = new Vertex(road.getStartAddress().getLandmarkName(), road.getStartAddress().getLatitude(), road.getStartAddress().getLongitude()); // assign the start address of the road for our project
            Vertex endVertex = new Vertex(road.getEndAddress().getLandmarkName(), road.getEndAddress().getLatitude(), road.getEndAddress().getLongitude()); // end address of the road
            double weight = road.getSpeedLimit(); // assign the speed of the road
            Edge edge = new Edge(startVertex, endVertex, weight, road.getName()); // Create an Edge object using edgedata in this format new Edge(Vertex edgeStart, Vertex edgeEnd, double speed, String roadName)
            graph.addEdge(edge);
        }

        for (Road road : busRoads) {
            Vertex startVertex = new Vertex(road.getBusStopStartAddress().getName(), road.getBusStopStartAddress().getLatitude(), road.getBusStopStartAddress().getLongitude()); // assign the start address of the road for our project
            Vertex endVertex = new Vertex(road.getEndAddress().getLandmarkName(), road.getEndAddress().getLatitude(), road.getEndAddress().getLongitude()); // end address of the road
            double weight = road.getSpeedLimit(); // assign the speed of the road
            Edge edge = new Edge(startVertex, endVertex, weight, road.getName()); // Create an Edge object using edgedata in this format new Edge(Vertex edgeStart, Vertex edgeEnd, double speed, String roadName)
            graph.addEdge(edge);
        }
    }

    @Override
    public void loadBusStopsFromDatabase(List<BusStop> busStops) {
        // List<Address> busStopList = new ArrayList<>();
        // Retrieve edge data using myBatis .
        for (BusStop busStop : busStops) {
            double latitude = busStop.getLatitude();
            double longitude = busStop.getLongitude();
            String name = busStop.getName();
            int frequency = busStop.getFrequency();
            Vertex vertex = new Vertex(name, latitude, longitude, frequency); // create a vertex object using the stopData. I have added two constructors in vertex.
            graph.addBusStop(vertex);
        }
    }

}
