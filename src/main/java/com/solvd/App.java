package com.solvd;

import com.solvd.controller.FloydWarshallAlgorithm;
import com.solvd.model.graph.RoadNetworkGraph;
import com.solvd.model.graph.Edge;
import com.solvd.model.TransportationMethod;
import com.solvd.model.graph.Vertex;
import com.solvd.view.RoutePrinterService;
import com.solvd.view.routeprinter.CarRoutePrinter;
import com.solvd.view.routeprinter.PublicTransportationRoutePrinter;
import com.solvd.model.graph.Vertex;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main( String[] args ) {
        Vertex chaseBank = new Vertex("Chase Bank",32.951580208033775, -96.76833370243185);
        Vertex autoExpress= new Vertex("Auto Express", 32.850173567458576, -96.6816067183312);
        Vertex school = new Vertex("Spring creek elementary",32.74768305337416, -96.85402981367932);
        Vertex samsClub = new Vertex("Sam's Club", 32.84886649939048, -96.92083805330677);
        Vertex walmart = new Vertex("Walmart",32.89854398859451, -97.04032425907587);

        Vertex busStop1 = new Vertex("UTD Bus Station",32.952066328524616, -96.76904180718951, 15);
        Vertex busStop2 = new Vertex("MEDICAL DISTRICT @ MAPLE - N - MB",32.849776991592854, -96.6828619778309, 30);
        Vertex busStop3 = new Vertex("Texas Health Dallas",32.747150679443706, -96.85371866777521, 20);
        Vertex busStop4 = new Vertex("Mockingbird @ Roper - E - FS",32.844756320727335, -96.92748993164287, 10);
        Vertex busStop5 = new Vertex("Buckner at Blossom - S - NS",32.89837236744563, -97.0386506658473,15);

        Edge edge1 = new Edge(chaseBank, autoExpress,50, "Commerce St");
        Edge edge2 = new Edge(autoExpress, walmart, 85,  "I30 Highway");
        Edge edge3 = new Edge(chaseBank,school,50, "Robert way");
        Edge edge4 = new Edge(school,walmart,30,"My way");
        Edge edge5 = new Edge(chaseBank,samsClub,25," fire way");
        Edge edge6 = new Edge(samsClub,walmart,45,"bobby way");
        Edge edge7 = new Edge(samsClub,school,40,"jeffery way");
        Edge edge8 = new Edge(busStop1,chaseBank,15,"Connection road UTD");
        Edge edge9 = new Edge(busStop2, autoExpress, 15,"Connection road Medical");
        Edge edge10 = new Edge(busStop3, school, 15,"Connection road Texas");
        Edge edge11 = new Edge(busStop4, samsClub, 15,"Connection road Mocking");
        Edge edge12 = new Edge(busStop5, walmart, 15,"Connection road Bucker");
        Edge edge13 = new Edge(busStop1,busStop2, 40, "Highway 1");
        Edge edge14 = new Edge(busStop2, busStop3, 45, "Highway 2");
        Edge edge15 = new Edge(busStop3, busStop4, 50, "Highway 3");
        Edge edge16 = new Edge(busStop4, busStop5, 40, "Highway 4");
        Edge edge17 = new Edge(busStop5, busStop1, 50, "Highway 5");





        RoadNetworkGraph roadNetworkGraph = new RoadNetworkGraph(Vertex.getVertexCount());
        roadNetworkGraph.addVertex(chaseBank);
        roadNetworkGraph.addVertex(autoExpress);
        roadNetworkGraph.addVertex(school);
        roadNetworkGraph.addVertex(samsClub);
        roadNetworkGraph.addVertex(walmart);


        roadNetworkGraph.addBusStop(busStop1);
        roadNetworkGraph.addBusStop(busStop2);
        roadNetworkGraph.addBusStop(busStop3);
        roadNetworkGraph.addBusStop(busStop4);
        roadNetworkGraph.addBusStop(busStop5);


        roadNetworkGraph.addEdge(edge1);
        roadNetworkGraph.addEdge(edge2);
        roadNetworkGraph.addEdge(edge3);
        roadNetworkGraph.addEdge(edge4);
        roadNetworkGraph.addEdge(edge5);
        roadNetworkGraph.addEdge(edge6);
        roadNetworkGraph.addEdge(edge7);
        roadNetworkGraph.addEdge(edge8);
        roadNetworkGraph.addEdge(edge9);
        roadNetworkGraph.addEdge(edge10);
        roadNetworkGraph.addEdge(edge11);
        roadNetworkGraph.addEdge(edge12);



        FloydWarshallAlgorithm floydWarshallAlgorithm = new FloydWarshallAlgorithm(roadNetworkGraph) ;
        floydWarshallAlgorithm.calculateShortestAndFastestRoutes();


        CarRoutePrinter carRoutePrinter = new CarRoutePrinter(roadNetworkGraph, floydWarshallAlgorithm);
        PublicTransportationRoutePrinter publicTransportationRoutePrinter = new PublicTransportationRoutePrinter(roadNetworkGraph, floydWarshallAlgorithm);
        RoutePrinterService routePrinter = new RoutePrinterService(carRoutePrinter, publicTransportationRoutePrinter);

        routePrinter.printRoute(autoExpress,samsClub, TransportationMethod.CAR, true);
        routePrinter.printRoute(chaseBank,walmart, TransportationMethod.PUBLIC_TRANSPORTATION, false);






        Vertex park = new Vertex("Park", 32.850456, -96.751234);
        Vertex kohls = new Vertex("Kohls",33.10142551818354, -96.73499542725521);
        Edge edgeToPark1 = new Edge(autoExpress, park, 20, "Park Street 1");
        Edge edgeToPark2 = new Edge(park, samsClub, 10, "Park Street 2");
        List<Vertex> vertices = new ArrayList<>();
        vertices.add(park);
        vertices.add(kohls);
        List<Edge> edges = new ArrayList<>();
        edges.add(edgeToPark1);
        edges.add(edgeToPark2);
        roadNetworkGraph.addNewVertexAndUpdateRoutes(park, edges);
        FloydWarshallAlgorithm floydWarshallAlgorithm1 = new FloydWarshallAlgorithm(roadNetworkGraph);
        floydWarshallAlgorithm1.calculateShortestAndFastestRoutes();

        CarRoutePrinter carRoutePrinter1 = new CarRoutePrinter(roadNetworkGraph, floydWarshallAlgorithm1);
        PublicTransportationRoutePrinter publicTransportationRoutePrinter1 = new PublicTransportationRoutePrinter(roadNetworkGraph, floydWarshallAlgorithm1);
        RoutePrinterService routePrinter1 = new RoutePrinterService(carRoutePrinter1, publicTransportationRoutePrinter1);
        routePrinter1.printRoute(park,walmart, TransportationMethod.CAR, false);
        routePrinter1.printRoute(park,chaseBank, TransportationMethod.PUBLIC_TRANSPORTATION, true);




    }
}
