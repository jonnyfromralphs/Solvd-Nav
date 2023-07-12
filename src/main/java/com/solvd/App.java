package com.solvd;

import com.solvd.controller.FloydWarshallAlgorithm;
import com.solvd.graph.RoadNetworkGraph;
import com.solvd.model.Edge;
import com.solvd.model.Vertex;


public class App {
    public static void main( String[] args ) {
        Vertex chaseBank = new Vertex("Chase Bank",32.951580208033775, -96.76833370243185);
        Vertex starBucks= new Vertex("StarBucks", 32.94988248914873, -96.76950403615635);
        Vertex school = new Vertex("Spring creek elementary",32.956388920456476, -96.77664256002228);
        Vertex dallasFireStation = new Vertex("Dallas Fire Station", 32.94979211860273, -96.7824118411453);
        Vertex walmart = new Vertex("Walmart Neighborhood Market",32.7600404, -96.6842455);

        Edge edge1 = new Edge(chaseBank, starBucks,20, "Commerce St");
        Edge edge2 = new Edge(starBucks, walmart, 15,  "I30 Highway");
        Edge edge3 = new Edge(chaseBank,school,40, "Robert way");
        Edge edge4 = new Edge(school,walmart,30,"My way");
        Edge edge5 = new Edge(chaseBank,dallasFireStation,30," fire way");
        Edge edge6 = new Edge(dallasFireStation,walmart,45,"bobby way");
        Edge edge7 = new Edge(dallasFireStation,school,35,"jeffery way");

        RoadNetworkGraph roadNetworkGraph = new RoadNetworkGraph(5);
        roadNetworkGraph.addVertex(chaseBank);
        roadNetworkGraph.addVertex(starBucks);
        roadNetworkGraph.addVertex(school);
        roadNetworkGraph.addVertex(dallasFireStation);
        roadNetworkGraph.addVertex(walmart);

        roadNetworkGraph.addEdge(edge1);
        roadNetworkGraph.addEdge(edge2);
        roadNetworkGraph.addEdge(edge3);
        roadNetworkGraph.addEdge(edge4);
        roadNetworkGraph.addEdge(edge5);
        roadNetworkGraph.addEdge(edge6);
        roadNetworkGraph.addEdge(edge7);

        FloydWarshallAlgorithm floydWarshallAlgorithm = new FloydWarshallAlgorithm(roadNetworkGraph) ;
        floydWarshallAlgorithm.calculateShortestAndFastestRoutes();



    }
}
