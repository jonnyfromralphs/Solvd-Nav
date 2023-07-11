package com.solvd.view;

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
                "Would you like to start the program? (Y/N): ");
    }

    public static void printMainMenu() {
        System.out.println("===============================");
        System.out.println("1. Find the fastest route");
        System.out.println("2. Find the shortest route");
        System.out.println("3. Add a road to the map");
        System.out.println("4. Add an address to the map");
        System.out.println("5. Exit program");
        System.out.println("\nPlease pick an option: ");
    }

    public static void printStartingPointScreen() {
        System.out.println("Please enter your starting point: ");
    }

    public static void printDestinationScreen() {
        System.out.println("Please enter your destination: ");
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

    public static void enterAddressScreen() {
        System.out.println("Please enter the full address of the location: ");
    }

    public static void printRoute() {
        //Placeholder to print results of algorithm
    }

    public static void printErrorMessage(Exception e) {
        System.out.println(e.getMessage());
    }
}
