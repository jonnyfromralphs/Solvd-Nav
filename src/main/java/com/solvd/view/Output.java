package com.solvd.view;

import com.solvd.model.graph.RoadNetworkGraph;
import com.solvd.model.graph.Vertex;
import com.solvd.view.routeprinter.CarRoutePrinter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class Output {
    private static final Logger LOGGER = LogManager.getLogger(Output.class);

    public static void printWelcomeScreen() {
        LOGGER.info("  ______   __    __                      __    __                      __                       __                         \n" +
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
        LOGGER.info("===============================");
        LOGGER.info("1. Find the fastest route");
        LOGGER.info("2. Find the shortest route");
        LOGGER.info("3. Add an address to the map");
        LOGGER.info("4. Exit program");
        LOGGER.info("\nPlease pick an option: ");
    }

    public static void printStartingPointScreen() {
        LOGGER.info("Please enter your starting point (address or name of location): ");
    }

    public static void printDestinationScreen() {
        LOGGER.info("Please enter your destination (address or name of location): ");
    }

    public static void printTransportationModeScreen() {
        LOGGER.info("Will you be driving or using public transportation?\n");
        LOGGER.info("1. Car");
        LOGGER.info("2. Public Transportation");
        LOGGER.info("3. Go back");
        LOGGER.info("\nPlease pick an option:");
    }

    public static void enterRoadName() {
        LOGGER.info("Please enter the name of the road: ");
    }

    public static void enterHouseNumberScreen() {
        LOGGER.info("Please enter the house number for the new address: ");
    }

    public static void enterStreetNameScreen() {
        LOGGER.info("Please enter the street name for the new address: ");
    }

    public static void enterZipCodeScreen() {
        LOGGER.info("Please enter the zip code for the new address: ");
    }

    public static void enterCityScreen() {
        LOGGER.info("Please enter the city for the new address: ");
    }

    public static void enterSpeedLimit() {
        LOGGER.info("Please enter the speed limit for the road in miles: ");
    }

    public static void enterAnotherStop() {
        LOGGER.info("Would you like to enter another stop? (Y/N): ");
    }

    public static void enterNameOfAddress() {
        LOGGER.info("Please enter the name of the location: ");
    }

    public static void enterConnectingAddress() {
        LOGGER.info("Please enter the name of the connecting address: ");
    }

    public static void printAddresses(RoadNetworkGraph roadNetworkGraph) {
        List<Vertex> addresses = roadNetworkGraph.getVertexList().stream().filter(vertex -> vertex.getFrequency() == 0).toList();
        addresses.forEach(address -> LOGGER.info(address.getName()));
        LOGGER.info("");
    }

    public static void printErrorMessage(Exception e) {
        LOGGER.info(e.getMessage());
    }
}
