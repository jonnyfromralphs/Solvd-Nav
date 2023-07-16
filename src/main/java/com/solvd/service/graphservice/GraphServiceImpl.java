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
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class GraphServiceImpl implements GraphService {
    private RoadNetworkGraph graph;
    SqlSessionFactory sqlSessionFactory = MyBatisUtil.getSqlSessionFactory();
    AddressMapper addressMapper = new AddressMapperImpl(sqlSessionFactory);
    RoadMapper roadMapper = new RoadMapperImpl(sqlSessionFactory);
    BusStopMapper busStopMapper = new BusStopMapperImpl(sqlSessionFactory);

    public GraphServiceImpl() { }

    @Override
    public RoadNetworkGraph loadGraphFromDatabase() {
        loadVerticesFromDatabase();
        loadEdgesFromDatabase();
        loadBusStopsFromDatabase();
        return graph;
    }

    // This method could be used for loading a list of vertices
    @Override
    public void loadVerticesFromDatabase() {
        List<Address> addresses = addressMapper.getAllAddresses();
        graph = new RoadNetworkGraph(addresses.size());
        // The below line should be like this List<Address> vertexDataList = new ArrayList<>(); Since i dont have an aaddress. its giving me error.
        //  Retrieve edge data using myBatis. please use an appropriate data structure
        // i just used arrayList just for a demo.
        for (Address address : addresses) {
            Vertex vertex = new Vertex(address.getLandmarkName(), address.getLatitude(), address.getLongitude()); // create vertex object here using the vertexData
            graph.addVertex(vertex);
        }
    }


    // This method could be used for loading the list of edges of our graph
    @Override
    public void loadEdgesFromDatabase() {
        // List<Road> edgedataList = new ArrayList<>();
        List<Road> roads = roadMapper.getAllRoads(); // Retrieve edge data using myBatis .

        // Create and add edges to the graph
        for (Road road : roads) {
            Vertex startVertex = new Vertex(road.getStartAddress().getLandmarkName(), road.getStartAddress().getLatitude(), road.getStartAddress().getLongitude()); // assign the start address of the road for our project
            Vertex endVertex = new Vertex(road.getEndAddress().getLandmarkName(), road.getEndAddress().getLatitude(), road.getEndAddress().getLongitude()); // end address of the road
            double weight = road.getSpeedLimit(); // assign the speed of the road
            Edge edge = new Edge(startVertex, endVertex, weight, road.getName()); // Create an Edge object using edgedata in this format new Edge(Vertex edgeStart, Vertex edgeEnd, double speed, String roadName)
            graph.addEdge(edge);
        }
    }

    @Override
    public void loadBusStopsFromDatabase() {
        // List<Address> busStopList = new ArrayList<>();
        List<BusStop> busStops = busStopMapper.getAllBusStops(); // Retrieve edge data using myBatis .
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
