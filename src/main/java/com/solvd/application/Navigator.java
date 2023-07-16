package com.solvd.application;

import com.solvd.exception.InvalidChoiceException;
import com.solvd.service.GeocoderService;
import com.solvd.view.Input;
import com.solvd.view.Output;

public class Navigator {
    private String input;
    private boolean isProgramRunning = true;
    private GeocoderService geocoderService = new GeocoderService();
    public void run() {
        welcomeScreen();
        while (isProgramRunning) {
            mainMenu();
        }
    }

    public void welcomeScreen() {
        Output.printWelcomeScreen();
        while (true) {
            try {
                input = Input.getString();
                if (input.equalsIgnoreCase("Y")) {
                    break;
                } else if (input.equalsIgnoreCase("N")) {
                    isProgramRunning = false;
                    break;
                } else {
                    throw new InvalidChoiceException();
                }
            } catch (Exception e) {
                Output.printErrorMessage(e);
            }
        }

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
        boolean isAddingStop = false;

        try {
            switch (input) {
                case FASTEST_ROUTE:
                    startingAddress = enterStartingAddress();
                    destinationAddress = enterDestinationAddress();
                    transportationMode = transportationMode();

                    if (transportationMode == null) {
                        break;
                    }

                    isAddingStop = addAnotherStop();
                    while (isAddingStop) {
                        startingAddress = destinationAddress;
                        destinationAddress = enterDestinationAddress();
                        transportationMode = transportationMode();
                        isAddingStop = addAnotherStop();
                    }

                    System.out.println("Fastest route:");
                    break;

                case SHORTEST_ROUTE:
                    startingAddress = enterStartingAddress();
                    destinationAddress = enterDestinationAddress();
                    transportationMode = transportationMode();

                    if (transportationMode == null) {
                        break;
                    }

                    isAddingStop = addAnotherStop();
                    while (isAddingStop) {
                        startingAddress = destinationAddress;
                        destinationAddress = enterDestinationAddress();
                        transportationMode = transportationMode();
                        isAddingStop = addAnotherStop();
                    }

                    System.out.println("Shortest route:");
                    break;
                case ADD_ROAD:
                    String roadName = addRoad();
                    startingAddress = enterStartingAddress();
                    destinationAddress = enterDestinationAddress();
                    int speedLimit;
                    break;
                case ADD_ADDRESS:
                    String fullAddress = addAddress();
                    double longitude = geocoderService.parseLongitude(geocoderService.GeocodeSync(fullAddress));
                    double latitude = geocoderService.parseLatitude(geocoderService.GeocodeSync(fullAddress));
                    String name = enterName();
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

    public String transportationMode() {
        Output.printTransportationModeScreen();
        input = Input.getString();

        final String CAR = "1";
        final String PUBLIC_TRANSPORTATION = "2";
        final String BACK = "3";

        try {
            return switch (input) {
                case CAR -> "Car";
                case PUBLIC_TRANSPORTATION -> "Public Transportation";
                case BACK -> null;
                default -> throw new InvalidChoiceException();
            };
        } catch (Exception e) {
            Output.printErrorMessage(e);
            transportationMode();
        }
        return null;
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

    public String addRoad() {
        Output.enterRoadName();
        return Input.getString();
    }

    public String addAddress() {
        Output.enterAddressScreen();
        return Input.getString();
    }

    public String enterName() {
        Output.enterNameOfAddress();
        return Input.getString();
    }

    public int addSpeedLimit() {
        Output.enterSpeedLimit();
        return Input.getInt();
    }

    public boolean addAnotherStop() {
        Output.enterAnotherStop();
        while (true) {
            try {
                input = Input.getString();
                if (input.equalsIgnoreCase("Y")) {
                    return true;
                } else if (input.equalsIgnoreCase("N")) {
                    return false;
                } else {
                    throw new InvalidChoiceException();
                }
            } catch (Exception e) {
                Output.printErrorMessage(e);
            }
        }
    }
}
