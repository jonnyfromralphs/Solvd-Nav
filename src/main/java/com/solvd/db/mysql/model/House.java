package com.solvd.db.mysql.model;

public class House {
    private long id;
    private String houseNumber;
    private Street street;

    public House(long id, String houseNumber, Street street){
        this.id = id;
        this.houseNumber = houseNumber;
        this.street = street;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public Street getStreet() {
        return street;
    }

    public void setStreet(Street street) {
        this.street = street;
    }

    @Override
    public String toString() {
        return "House{" +
                "id=" + id +
                ", houseNumber='" + houseNumber + '\'' +
                ", street=" + street +
                '}';
    }
}
