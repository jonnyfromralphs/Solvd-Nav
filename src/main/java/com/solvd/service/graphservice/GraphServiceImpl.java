package com.solvd.service.graphservice;

import com.solvd.graph.RoadNetworkGraph;
import com.solvd.model.Edge;
import com.solvd.model.Vertex;

import java.util.ArrayList;
import java.util.List;

public class GraphServiceImpl implements GraphService {
    private RoadNetworkGraph graph;

    public GraphServiceImpl(RoadNetworkGraph graph) {
        this.graph = graph;
    }

    @Override
    public void loadGraphFromDatabase() {
        loadVerticesFromDatabase();
        loadEdgesFromDatabase();
        loadBusStopsFromDatabase();
    }

    // This method could be used for loading a list of vertices
    @Override
    public void loadVerticesFromDatabase() {

        // The below line should be like this List<Address> vertexDataList = new ArrayList<>(); Since i dont have an aaddress. its giving me error.
        List<String> vertexDataList = new ArrayList<>(); //  Retrieve edge data using myBatis. please use an appropriate data structure
        // i just used arrayList just for a demo.



        for (String vertexData : vertexDataList) {
            Vertex vertex = null; // create vertex object here using the vertexData
            graph.addVertex(vertex);
        }
    }


    // This method could be used for loading the list of edges of our graph
    @Override
    public void loadEdgesFromDatabase() {
        // List<Road> edgedataList = new ArrayList<>();
        List<String> edgeDataList = new ArrayList<>(); // Retrieve edge data using myBatis .

        // Create and add edges to the graph
        for (String edgeData : edgeDataList) {
            Vertex startVertex; // assign the start address of the road for our project
            Vertex endVertex; // end address of the road
            double weight; // assign the speed of the road
            Edge edge = null; // Create an Edge object using edgedata in this format new Edge(Vertex edgeStart, Vertex edgeEnd, double speed, String roadName)
            graph.addEdge(edge);
        }
    }

    @Override
    public void loadBusStopsFromDatabase() {
        // List<Address> busStopList = new ArrayList<>();
        List<String> busStopList = new ArrayList<>(); // Retrieve edge data using myBatis .
        for (String stopData : busStopList) {
            double latitude;
            double longitude;
            String name;
            int frequency;
            Vertex busStop = null; // create a vertex object using the stopData. I have added two constructors in vertex.
            graph.addBusStop(busStop);
        }

    }


}
