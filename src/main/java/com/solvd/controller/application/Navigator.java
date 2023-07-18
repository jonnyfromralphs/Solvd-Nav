package com.solvd.controller.application;

import com.solvd.controller.FloydWarshallAlgorithm;
import com.solvd.db.mysql.mapperImpl.*;
import com.solvd.db.utils.MyBatisUtil;
import com.solvd.exception.InvalidChoiceException;
import com.solvd.model.*;
import com.solvd.exception.*;
import com.solvd.model.TransportationMethod;
import com.solvd.model.graph.RoadNetworkGraph;
import com.solvd.model.graph.Vertex;
import com.solvd.service.*;
import com.solvd.service.graphservice.GraphServiceImpl;
import com.solvd.view.Input;
import com.solvd.view.Output;
import com.solvd.view.RoutePrinterService;
import com.solvd.view.routeprinter.CarRoutePrinter;
import com.solvd.view.routeprinter.PublicTransportationRoutePrinter;
import org.apache.ibatis.session.SqlSessionFactory;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class Navigator {
    private String input;
    private boolean isProgramRunning = true;
    private RoadNetworkGraph roadNetworkGraph;
    private FloydWarshallAlgorithm floydWarshallAlgorithm;
    private RoutePrinterService routePrinterService;
    private CarRoutePrinter carRoutePrinter;
    private PublicTransportationRoutePrinter publicTransportationRoutePrinter;
    private Queue<AddressPair<String, String>> directionsQueue = new LinkedList<>();
    SqlSessionFactory sqlSessionFactory = MyBatisUtil.getSqlSessionFactory();
    AddressService addressService = new AddressService(new AddressMapperImpl(sqlSessionFactory));
    RoadService roadService = new RoadService(new RoadMapperImpl(sqlSessionFactory));
    BusStopService busStopService = new BusStopService(new BusStopMapperImpl(sqlSessionFactory));
    ZipCodeService zipCodeService = new ZipCodeService(new ZipCodeMapperImpl(sqlSessionFactory));
    StreetService streetService = new StreetService(new StreetMapperImpl(sqlSessionFactory));
    CityService cityService = new CityService(new CityMapperImpl(sqlSessionFactory));
    private GeocoderService geocoderService = new GeocoderService();

    public void run() throws InvalidGraphException, GraphCreationException{
        setupGraph();
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
        final String ADD_ADDRESS = "3";
        final String EXIT = "4";

        input = Input.getString();
        String startingAddress;
        String destinationAddress;
        TransportationMethod transportationMode;
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

                    directionsQueue.offer(new AddressPair<>(startingAddress, destinationAddress, transportationMode));
                    isAddingStop = addAnotherStop();

                    while (isAddingStop) {
                        startingAddress = destinationAddress;
                        destinationAddress = enterDestinationAddress();
                        transportationMode = transportationMode();
                        directionsQueue.offer(new AddressPair<>(startingAddress, destinationAddress, transportationMode));
                        isAddingStop = addAnotherStop();
                    }

                    while (!directionsQueue.isEmpty()) {
                        AddressPair<String, String> addressPair = directionsQueue.poll();
                        getRoute(addressPair.getStartingAddress(), addressPair.getDestinationAddress(), addressPair.transportationMode, true);
                    }
                    break;

                case SHORTEST_ROUTE:
                    startingAddress = enterStartingAddress();
                    destinationAddress = enterDestinationAddress();
                    transportationMode = transportationMode();
                    if (transportationMode == null) {
                        break;
                    }

                    directionsQueue.offer(new AddressPair<>(startingAddress, destinationAddress, transportationMode));

                    isAddingStop = addAnotherStop();
                    while (isAddingStop) {
                        startingAddress = destinationAddress;
                        destinationAddress = enterDestinationAddress();
                        transportationMode = transportationMode();
                        directionsQueue.offer(new AddressPair<>(startingAddress, destinationAddress, transportationMode));
                        isAddingStop = addAnotherStop();
                    }

                    while (!directionsQueue.isEmpty()) {
                        AddressPair<String, String> addressPair = directionsQueue.poll();
                        getRoute(addressPair.getStartingAddress(), addressPair.getDestinationAddress(), addressPair.transportationMode, false);
                    }

                    break;
                case ADD_ADDRESS:
                    addAddress();
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

    public void getRoute(String startingAddress, String destinationAddress, TransportationMethod transportationMode, boolean fastest) throws InvalidChoiceException, GraphDataMissingException, CarRoutePrinterException, NoRouteFoundException {
        Vertex start = roadNetworkGraph.getVertexList().stream().filter(v -> v.getName().contains(startingAddress)).findFirst().orElse(null);
        Vertex end = roadNetworkGraph.getVertexList().stream().filter(v -> v.getName().contains(destinationAddress)).findFirst().orElse(null);

        if (start == null || end == null) {
            throw new InvalidChoiceException();
        }
        routePrinterService.printRoute(start, end, transportationMode, fastest);
    }

    public TransportationMethod transportationMode() {
        Output.printTransportationModeScreen();
        input = Input.getString();

        final String CAR = "1";
        final String PUBLIC_TRANSPORTATION = "2";
        final String BACK = "3";

        try {
            return switch (input) {
                case CAR -> TransportationMethod.CAR;
                case PUBLIC_TRANSPORTATION -> TransportationMethod.PUBLIC_TRANSPORTATION;
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
        Output.printAddresses(roadNetworkGraph);
        Output.printStartingPointScreen();
        return Input.getString();
    }

    public String enterDestinationAddress() {
        Output.printAddresses(roadNetworkGraph);
        Output.printDestinationScreen();
        return Input.getString();
    }

    public String addRoad() {
        Output.enterRoadName();
        return Input.getString();
    }

    public void addAddress() throws IOException, InterruptedException, InvalidGraphException, GraphCreationException {
        Output.enterHouseNumberScreen();
        String houseNumber = Input.getString();

        Output.enterStreetNameScreen();
        String streetName = Input.getString();

        Output.enterCityScreen();
        String cityName = Input.getString();

        Output.enterZipCodeScreen();
        int code = Input.getInt();

        Output.enterNameOfAddress();
        String name = Input.getString();

        String fullAddress = houseNumber + " " + streetName + cityName + ", CA " + code;
        double longitude = geocoderService.parseLongitude(geocoderService.GeocodeSync(fullAddress));
        double latitude = geocoderService.parseLatitude(geocoderService.GeocodeSync(fullAddress));

        Street street = streetService.getByName(streetName);
        if (street == null) {
            streetService.create(new Street(0, streetName));
            street = streetService.getByName(streetName);
        }

        City city = cityService.getByName(cityName);
        if (city == null) {
            cityService.create(new City(0, cityName));
            city = cityService.getByName(cityName);
        }

        ZipCode zipCode = zipCodeService.getByCode(code);
        if (zipCode == null) {
            zipCodeService.create(new ZipCode(0, code, city.getId()));
            zipCode = zipCodeService.getByCode(code);
        }

        Address newAddress = new Address(0, houseNumber, street, city, "CA", zipCode, longitude, latitude, name);
        BusStop newBusStop = new BusStop(0,streetName + " Bus Stop", latitude + .003, longitude + .003, (int) (Math.random() * 40 + 10));
        addressService.create(newAddress);
        busStopService.create(newBusStop);

        Output.printAddresses(roadNetworkGraph);
        Output.enterConnectingAddress();

        String connectingAddressName = Input.getString();
        newAddress = addressService.getByName(name);
        Address existingAddress = addressService.getByName(connectingAddressName);

        String roadName = addRoad();
        int speedLimit = addSpeedLimit();

        Road newRoad = new Road(0, roadName, existingAddress, newAddress, speedLimit);
        roadService.create(newRoad);

        Address busAddress = new Address();
        busAddress.setId(busStopService.getByName(streetName + " Bus Stop").getId());
        Road newBusRoad = new Road(0, roadName, busAddress, newAddress, speedLimit);
        roadService.create(newBusRoad);

        setupGraph();
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

    public void setupGraph() throws GraphCreationException, InvalidGraphException {
        roadNetworkGraph = new GraphServiceImpl().loadGraphFromDatabase();
        floydWarshallAlgorithm = new FloydWarshallAlgorithm(roadNetworkGraph);
        floydWarshallAlgorithm.calculateShortestAndFastestRoutes();
        carRoutePrinter = new CarRoutePrinter(roadNetworkGraph, floydWarshallAlgorithm);
        publicTransportationRoutePrinter = new PublicTransportationRoutePrinter(roadNetworkGraph, floydWarshallAlgorithm);
        routePrinterService = new RoutePrinterService(carRoutePrinter, publicTransportationRoutePrinter);
    }

    class AddressPair<T, U> {
        private T startingAddress;
        private U destinationAddress;

        private TransportationMethod transportationMode;

        public AddressPair(T startingAddress, U destinationAddress, TransportationMethod transportationMode) {
            this.startingAddress = startingAddress;
            this.destinationAddress = destinationAddress;
            this.transportationMode = transportationMode;
        }

        public T getStartingAddress() {
            return startingAddress;
        }

        public U getDestinationAddress() {
            return destinationAddress;
        }
    }
}
