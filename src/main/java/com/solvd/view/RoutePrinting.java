package com.solvd.view;

import com.solvd.controller.FloydWarshallAlgorithm;
import com.solvd.controller.RouteCalculator;
import com.solvd.exception.CarRoutePrinterException;
import com.solvd.exception.GraphDataMissingException;
import com.solvd.exception.InvalidGraphException;
import com.solvd.exception.NoRouteFoundException;
import com.solvd.model.graph.Edge;
import com.solvd.model.graph.RoadNetworkGraph;
import com.solvd.model.graph.Vertex;
import java.text.DecimalFormat;
import java.util.List;

public class RoutePrinting {
    protected static final DecimalFormat df = new DecimalFormat("0.00");
    protected RoadNetworkGraph graph;
    protected FloydWarshallAlgorithm floydWarshall;
    protected List<Edge> edgeList;
    protected RouteCalculator routeCalculator;
    protected static final double WALKING_SPEED = 3.0;

    public RoutePrinting(RoadNetworkGraph graph, FloydWarshallAlgorithm floydWarshall) throws InvalidGraphException {
        this.graph = graph;
        this.floydWarshall = floydWarshall;
        this.edgeList = graph.getEdgeList();
        this.routeCalculator = new RouteCalculator(graph);
    }

    protected String formatPath(List<Vertex> path) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < path.size(); i++) {
            sb.append(formatAddress(path.get(i).getName()));
            if (i != path.size() - 1) {
                sb.append(" -> ");
            }
        }
        return sb.toString();
    }

  protected String formatPathWithDirections(List<Vertex> path) {
      StringBuilder sb = new StringBuilder();
      double totalDistance = 0.0;
      double totalTime = 0.0;

      for (int i = 0; i < path.size() - 1; i++) {
          Vertex currentVertex = path.get(i);
          Vertex nextVertex = path.get(i + 1);
          Edge edge = findEdgeBetweenVertices(currentVertex, nextVertex);
          double distance = 0.0;
          double time = 0.0;
          if (edge != null) {
              distance = graph.getDistanceUsingHaversine(edge.getStart(), edge.getEnd());
              time = getTimeBetweenVertices(currentVertex, nextVertex);
          }

          sb.append("From ").append(currentVertex.getName()).append(" take ");
          if (edge != null) {
              sb.append(edge.getRoadName()).append(" and drive for ").append(df.format(distance)).append(" miles ").append("(").append(df.format(time)).append(" hours),");
          } else {
              sb.append("Unnamed Road,");
          }

          if (i == path.size() - 2) {
              sb.append(" to reach ").append(nextVertex.getName()).append(".");
          } else {
              sb.append(" to pass by ").append(nextVertex.getName()).append(",\n");
          }

          totalDistance += distance;
          totalTime += time;

          if (i != path.size() - 2) {
              sb.append("and then ");
          }
      }

      sb.append("\nTotal Distance: ").append(df.format(totalDistance)).append(" miles");
      sb.append("\nTotal Time: ").append(df.format(totalTime)).append(" hours");

      return sb.toString();
  }

    protected String formatPathWithDirectionsBus(List<Vertex> path, Vertex source, Vertex destination, int waitTime) throws GraphDataMissingException {
        StringBuilder sb = new StringBuilder();
        DecimalFormat df = new DecimalFormat("#0.00");

        double totalDistance = 0.0;
        double totalTime = 0.0;

        Vertex sourceBusStop = graph.findNearestBusStop(source);
        Vertex destinationBusStop = graph.findNearestBusStop(destination);

        double walkingDistanceToSource = graph.getDistanceUsingHaversine(source, sourceBusStop);
        double walkingTimeToSource = walkingDistanceToSource / WALKING_SPEED;
        double walkingDistanceFromDestination = graph.getDistanceUsingHaversine(destinationBusStop, destination);
        double walkingTimeFromDestination = walkingDistanceFromDestination / WALKING_SPEED;

        sb.append("From ").append(formatAddress(source.getName())).append(", walk to the nearest bus stop and board the next bus(")
                .append(df.format(walkingDistanceToSource)).append(" miles, ")
                .append(df.format(walkingTimeToSource)).append(" hours).\n");

        boolean passByNodes = false;

        for (int i = 0; i < path.size() - 1; i++) {
            Vertex currentVertex = path.get(i);
            Vertex nextVertex = path.get(i + 1);
            double distance = 0.0;
            double time = 0.0;

            if (currentVertex.equals(sourceBusStop)) {
                passByNodes = true;
                totalDistance += walkingDistanceToSource;
                totalTime += walkingTimeToSource;
            }

            if (passByNodes) {
                Edge edge = findEdgeBetweenVertices(currentVertex, nextVertex);

                if (edge != null) {
                    distance = graph.getDistanceUsingHaversine(edge.getStart(), edge.getEnd());
                    time = getTimeBetweenVertices(currentVertex, nextVertex);

                    sb.append("From ").append(formatAddress(currentVertex.getName())).append(", take ")
                            .append(edge.getRoadName()).append(" and drive for ")
                            .append(df.format(distance)).append(" miles (").append(df.format(time)).append(" hours).\n");


                    totalDistance += distance;
                    totalTime += time;
                }
            }

            if (nextVertex.equals(destinationBusStop) && i != path.size() - 2) {
                sb.append("Finally, walk from the bus stop ").append(destinationBusStop).append(" to ").append(formatAddress(destination.getName())).append(" (")
                        .append(df.format(walkingDistanceFromDestination)).append(" miles, ")
                        .append(df.format(walkingTimeFromDestination)).append(" hours).\n");

                totalDistance += walkingDistanceFromDestination;
                totalTime += walkingTimeFromDestination ;
                break;
            }
        }

        sb.append("Total Distance (Including walking distance): ").append(df.format(totalDistance)).append(" miles");
        sb.append("\nTotal Time (Including walking time & waiting time at Bus stop): ");
        totalTime += (waitTime / 60.0);

        if (totalTime < 1) {
            double totalMinutes = totalTime * 60;
            sb.append(df.format(totalMinutes)).append(" minutes");
        } else {
            sb.append(df.format(totalTime )).append(" hours");
        }
        return sb.toString();
    }

    private Edge findEdgeBetweenVertices(Vertex sourceVertex, Vertex destinationVertex) {
        for (Edge edge : edgeList) {
            if ((edge.getStart().equals(sourceVertex) && edge.getEnd().equals(destinationVertex))
                    || (edge.getStart().equals(destinationVertex) && edge.getEnd().equals(sourceVertex))) {
                return edge;
            }
        }
        return null;
    }

    public void printFastestRoute(Vertex source, Vertex destination) throws GraphDataMissingException, CarRoutePrinterException, NoRouteFoundException {
        double fastestTime = calculateTotalTime(routeCalculator.calculateFastestPath(source, destination));
        List<Vertex> fastestPath = routeCalculator.calculateFastestPath(source, destination);
        System.out.println("Fastest Route:");
        System.out.println("Path: " + formatPath(fastestPath));
        System.out.println("Total Time: " + fastestTime);
    }

    protected double calculateTotalTime(List<Vertex> path) {
        double totalTime = 0;
        for (int i = 0; i < path.size() - 1; i++) {
            Vertex currentVertex = path.get(i);
            Vertex nextVertex = path.get(i + 1);
            double time = getTimeBetweenVertices(currentVertex, nextVertex);
            totalTime += time;
        }
        return totalTime;
    }

    private double getTimeBetweenVertices(Vertex source, Vertex destination) {
        List<Edge> edgeList = graph.getEdgeList();
        double totalTime = 0.0;

        for (Edge edge : edgeList) {
            if ((edge.getStart().equals(source) && edge.getEnd().equals(destination))
                    || (edge.getStart().equals(destination) && edge.getEnd().equals(source))) {
                totalTime += graph.getDistanceUsingHaversine(edge.getStart(),edge.getEnd()) / edge.getSpeed();
            }
        }

        return totalTime;
    }

    private String formatAddress(String fullAddress) {
        int index = fullAddress.indexOf(":");
        return index > -1 ? fullAddress.substring(0, index) : fullAddress;
    }
}
