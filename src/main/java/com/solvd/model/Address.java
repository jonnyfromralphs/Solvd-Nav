package com.solvd.model;

public class Address {
    private long id;
    private String houseNumber;
    private Street street;
    private City city;
    private String stateName;
    private ZipCode zipCode;
    private double longitude;
    private double latitude;
    private String landmarkName;

    public Address(){}

    public Address(long id, String houseNumber, Street street, City city, String stateName, ZipCode zipCode,
                   double longitude, double latitude, String landmarkName) {
        this.id = id;
        this.houseNumber = houseNumber;
        this.street = street;
        this.city = city;
        this.stateName = stateName;
        this.zipCode = zipCode;
        this.longitude = longitude;
        this.latitude = latitude;
        this.landmarkName = landmarkName;
    }

    public Address(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public String getLandmarkName() {
        return landmarkName;
    }

    @Override
    public String toString() {
        return String.format("%s: %s %s %s, %s %s", landmarkName, houseNumber, street.getName(), city.getName(), stateName, zipCode.getCode());
    }
}
