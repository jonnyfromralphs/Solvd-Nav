package com.solvd.view.routeprinter;

import com.solvd.controller.FloydWarshallAlgorithm;
import com.solvd.exception.GraphDataMissingException;
import com.solvd.exception.InvalidGraphException;
import com.solvd.model.graph.RoadNetworkGraph;
import com.solvd.model.graph.Vertex;
import com.solvd.view.RoutePrinting;

import java.time.LocalTime;
import java.time.ZoneId;
import java.util.List;
import java.util.ArrayList;

public class PublicTransportationRoutePrinter extends RoutePrinting implements RoutePrinterInterface {
    public PublicTransportationRoutePrinter(RoadNetworkGraph graph, FloydWarshallAlgorithm floydWarshall) throws InvalidGraphException {
        super(graph, floydWarshall);
    }

    @Override
    public void printShortestRoute(Vertex source, Vertex destination) throws GraphDataMissingException {
        Vertex nearestBusStopSource = graph.findNearestBusStop(source);
        Vertex nearestBusStopDestination = graph.findNearestBusStop(destination);
        System.out.println("Source: " + source + " (Nearest Bus Stop: " + nearestBusStopSource + ")");
        System.out.println("Destination: " + destination + " (Nearest Bus Stop: " + nearestBusStopDestination + ")");

        List<Vertex> path = new ArrayList<>();

        if (source != destination && nearestBusStopSource != nearestBusStopDestination) {
            int waitTimeMinutes = calculateWaitTime(source, destination);
            System.out.println("Wait Time at Bus Stop: " + waitTimeMinutes + " minutes");

            List<Vertex> sourceToBusStopPath = routeCalculator.calculateShortestPath(source, nearestBusStopSource);
            path.addAll(sourceToBusStopPath);

            List<Vertex> busStopToBusStopPath = routeCalculator.calculateShortestPath(nearestBusStopSource, nearestBusStopDestination);
            if (busStopToBusStopPath.size() > 1) {
                path.addAll(busStopToBusStopPath.subList(1, busStopToBusStopPath.size()));
            } else {
                path.addAll(busStopToBusStopPath);
            }

            List<Vertex> busStopToDestinationPath = routeCalculator.calculateShortestPath(nearestBusStopDestination, destination);
            if (busStopToDestinationPath.size() > 1) {
                path.addAll(busStopToDestinationPath.subList(1, busStopToDestinationPath.size()));
            } else {
                path.addAll(busStopToDestinationPath);
            }

            if (!path.isEmpty()) {
                System.out.println("Shortest Route with Bus Stops: " + formatPath(path) + "\n");
                System.out.println(formatPathWithDirectionsBus(path, source, destination, waitTimeMinutes));
            } else {
                System.out.println("No route found.");
            }
        } else {
            System.out.println("Source: " + source);
            System.out.println("Destination: " + destination);
            System.out.println("Shortest Route with Bus Stops: No route found since the source and destination are the same");
        }
    }

    private int calculateWaitTime(Vertex source, Vertex destination) throws GraphDataMissingException {
        ZoneId pacificTimeZone = ZoneId.of("America/Los_Angeles");
        LocalTime currentTime = LocalTime.now(pacificTimeZone);
        LocalTime firstBusTime = LocalTime.of(7, 0);
        int busIntervalMinutes = graph.findNearestBusStop(source).getFrequency();
        int currentTimeMinutes = currentTime.getHour() * 60 + currentTime.getMinute();
        int firstBusTimeMinutes = firstBusTime.getHour() * 60 + firstBusTime.getMinute();
        int minutesSinceFirstBus = currentTimeMinutes - firstBusTimeMinutes;
        int waitTimeMinutes = (busIntervalMinutes - (minutesSinceFirstBus % busIntervalMinutes)) % busIntervalMinutes;
        return waitTimeMinutes;
    }

    @Override
    public void printFastestRoute(Vertex source, Vertex destination) throws GraphDataMissingException {
        Vertex nearestBusStopSource = graph.findNearestBusStop(source);
        Vertex nearestBusStopDestination = graph.findNearestBusStop(destination);

        System.out.println();
        System.out.println("Source: " + source + " (Nearest Bus Stop to " + source + ": " + nearestBusStopSource + ")");
        System.out.println("Destination: " + destination + " (Nearest Bus Stop to " + destination + ": " + nearestBusStopDestination + ")");
        List<Vertex> path = new ArrayList<>();

        if (source != destination && nearestBusStopSource != nearestBusStopDestination) {
            int waitTimeMinutes = calculateWaitTime(source, destination);
            System.out.println("Wait Time at Bus Stop: " + waitTimeMinutes + " minutes");

            List<Vertex> sourceToBusStopPath = routeCalculator.calculateFastestPath(source, nearestBusStopSource);
            path.addAll(sourceToBusStopPath);
            List<Vertex> busStopToBusStopPath = routeCalculator.calculateFastestPath(nearestBusStopSource, nearestBusStopDestination);
            if (busStopToBusStopPath.size() > 1) {
                path.addAll(busStopToBusStopPath.subList(1, busStopToBusStopPath.size()));
            } else {
                path.addAll(busStopToBusStopPath);
            }
            List<Vertex> busStopToDestinationPath = routeCalculator.calculateFastestPath(nearestBusStopDestination, destination);
            if (busStopToDestinationPath.size() > 1) {
                path.addAll(busStopToDestinationPath.subList(1, busStopToDestinationPath.size()));
            } else {
                path.addAll(busStopToDestinationPath);
            }

            System.out.println("Fastest Route using Bus: " + formatPath(path) + "\n");
            System.out.println(formatPathWithDirectionsBus(path, source, destination, waitTimeMinutes));
            System.out.println();
        } else {
            System.out.println("Source: " + source);
            System.out.println("Destination: " + destination);
            System.out.print("Fastest Route using Bus: ");
            System.out.print("No route found since the source and destination are the same\n");
            System.out.println(" ");
        }
    }





}
