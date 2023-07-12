package com.solvd.model;

public class Address {
    private int id;
    private int houseNumber;
    private String street;
    private String city;
    private String state;

    public Address(int houseNumber, String street, String city, String state) {
        this.id = id;
        this.houseNumber = houseNumber;
        this.street = street;
        this.city = city;
        this.state = state;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return id +","+ houseNumber + "," + street + "," + city + "," + state;
    }
}
