package com.solvd.application;

import com.solvd.exception.InvalidChoiceException;
import com.solvd.view.Input;
import com.solvd.view.Output;

public class Navigator {
    private String input;
    private boolean isProgramRunning = true;
    public void run() {
        welcomeScreen();

        while (isProgramRunning) {

            mainMenu();
            isProgramRunning = false;
        }
    }

    public void welcomeScreen() {
        Output.printWelcomeScreen();
        input = Input.getString();
    }

    public void mainMenu() {
        Output.printMainMenu();

        final String FASTEST_ROUTE = "1";
        final String SHORTEST_ROUTE = "2";
        final String ADD_ROAD = "3";
        final String ADD_ADDRESS = "4";
        final String EXIT = "5";

        input = Input.getString();

        String startingAddress;
        String destinationAddress;
        String transportationMode;

        try {
            switch (input) {
                case FASTEST_ROUTE:
                    startingAddress = enterStartingAddress();
                    destinationAddress = enterDestinationAddress();
                    transportationMode = transportationMode();
                    System.out.println("Fastest route:");
                    break;
                case SHORTEST_ROUTE:
                    startingAddress = enterStartingAddress();
                    destinationAddress = enterDestinationAddress();
                    transportationMode = transportationMode();
                    System.out.println("Shortest route: ");
                    break;
                case ADD_ROAD:
                    String roadName = addRoad();
                    break;
                case ADD_ADDRESS:
                    String fullAddress = addAddress();
                    String longitude = enterLongitude();
                    String latitude = enterLatitude();
                    break;
                case EXIT:
                    isProgramRunning = false;
                    break;
                default:
                    throw new InvalidChoiceException();
            }
        } catch (Exception e) {
            Output.printErrorMessage(e);
            mainMenu();
        }

    }

    public String enterStartingAddress() {
        Output.printStartingPointScreen();
        return Input.getString();
    }

    public String enterDestinationAddress() {
        Output.printDestinationScreen();
        return Input.getString();
    }

    public String enterLongitude() {
        Output.enterLongitudeScreen();
        return Input.getString();
    }

    public String enterLatitude() {
        Output.enterLatitudeScreen();
        return Input.getString();
    }

    public String transportationMode() {
        Output.printTransportationModeScreen();
        input = Input.getString();

        final String CAR = "1";
        final String PUBLIC_TRANSPORTATION = "2";
        final String BACK = "3";

        try {
            switch (input) {
                case CAR:
                    return "Car";
                case PUBLIC_TRANSPORTATION:
                    return "Public Transportation";
                case BACK:
                    mainMenu();
                    break;
                default:
                    throw new InvalidChoiceException();
            }
        } catch (Exception e) {
            Output.printErrorMessage(e);
            transportationMode();
        }
        return null;
    }

    public String addRoad() {
        Output.enterRoadName();
        input = Input.getString();
        return input;
    }

    public String addAddress() {
        Output.enterAddressScreen();
        input = Input.getString();
        return input;
    }
}
