package com.solvd.view;

import com.solvd.model.Address;
import com.solvd.model.graph.RoadNetworkGraph;
import com.solvd.model.graph.Vertex;

import java.util.List;

public class Output {
    public static void printWelcomeScreen() {
        System.out.println("  ______   __    __                      __    __                      __                       __                         \n" +
                " /      \\ |  \\  |  \\                    |  \\  |  \\                    |  \\                     |  \\                        \n" +
                "|  $$$$$$\\ \\$$ _| $$_    __    __       | $$\\ | $$  ______  __     __  \\$$  ______    ______  _| $$_     ______    ______  \n" +
                "| $$   \\$$|  \\|   $$ \\  |  \\  |  \\      | $$$\\| $$ |      \\|  \\   /  \\|  \\ /      \\  |      \\|   $$ \\   /      \\  /      \\ \n" +
                "| $$      | $$ \\$$$$$$  | $$  | $$      | $$$$\\ $$  \\$$$$$$\\\\$$\\ /  $$| $$|  $$$$$$\\  \\$$$$$$\\\\$$$$$$  |  $$$$$$\\|  $$$$$$\\\n" +
                "| $$   __ | $$  | $$ __ | $$  | $$      | $$\\$$ $$ /      $$ \\$$\\  $$ | $$| $$  | $$ /      $$ | $$ __ | $$  | $$| $$   \\$$\n" +
                "| $$__/  \\| $$  | $$|  \\| $$__/ $$      | $$ \\$$$$|  $$$$$$$  \\$$ $$  | $$| $$__| $$|  $$$$$$$ | $$|  \\| $$__/ $$| $$      \n" +
                " \\$$    $$| $$   \\$$  $$ \\$$    $$      | $$  \\$$$ \\$$    $$   \\$$$   | $$ \\$$    $$ \\$$    $$  \\$$  $$ \\$$    $$| $$      \n" +
                "  \\$$$$$$  \\$$    \\$$$$  _\\$$$$$$$       \\$$   \\$$  \\$$$$$$$    \\$     \\$$ _\\$$$$$$$  \\$$$$$$$   \\$$$$   \\$$$$$$  \\$$      \n" +
                "                        |  \\__| $$                                        |  \\__| $$                                       \n" +
                "                         \\$$    $$                                         \\$$    $$                                       \n" +
                "                          \\$$$$$$                                           \\$$$$$$                                        \n\n" +
                "A simple command line interface to find the shortest and fastest routes around Los Angeles.\n\n" +
                "Would you like to start the program? (Y/N): ");
    }

    public static void printMainMenu() {
        System.out.println("===============================");
        System.out.println("1. Find the fastest route");
        System.out.println("2. Find the shortest route");
        System.out.println("3. Add an address to the map");
        System.out.println("4. Exit program");
        System.out.println("\nPlease pick an option: ");
    }

    public static void printStartingPointScreen() {
        System.out.println("Please enter your starting point (address or name of location): ");
    }

    public static void printDestinationScreen() {
        System.out.println("Please enter your destination (address or name of location): ");
    }

    public static void printTransportationModeScreen() {
        System.out.println("Will you be driving or using public transportation?\n");
        System.out.println("1. Car");
        System.out.println("2. Public Transportation");
        System.out.println("3. Go back");
        System.out.print("\nPlease pick an option:");
    }

    public static void enterLongitudeScreen() {
        System.out.println("Please enter the longitude value for the address: ");
    }

    public static void enterLatitudeScreen() {
        System.out.println("Please enter the latitude value for the address: ");
    }

    public static void enterRoadName() {
        System.out.println("Please enter the name of the road: ");
    }

    public static void enterHouseNumberScreen() {
        System.out.println("Please enter the house number for the new address: ");
    }

    public static void enterStreetNameScreen() {
        System.out.println("Please enter the street name for the new address: ");
    }

    public static void enterZipCodeScreen() {
        System.out.println("Please enter the zip code for the new address: ");
    }

    public static void enterCityScreen() {
        System.out.println("Please enter the city for the new address: ");
    }

    public static void enterSpeedLimit() {
        System.out.println("Please enter the speed limit for the road in miles: ");
    }

    public static void enterAnotherStop() {
        System.out.println("Would you like to enter another stop? (Y/N): ");
    }

    public static void enterNameOfAddress() {
        System.out.println("Please enter the name of the location: ");
    }

    public static void enterConnectingAddress() {
        System.out.println("Please enter the name of the connecting address: ");
    }

    public static void printAddresses(RoadNetworkGraph roadNetworkGraph) {
        List<Vertex> addresses = roadNetworkGraph.getVertexList().stream().filter(vertex -> vertex.getFrequency() == 0).toList();
        addresses.forEach(address -> System.out.println(address.getName()));
        System.out.println();
    }

    public static void printErrorMessage(Exception e) {
        System.out.println(e.getMessage());
    }
}
