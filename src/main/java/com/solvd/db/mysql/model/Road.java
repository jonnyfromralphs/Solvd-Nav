package com.solvd.db.mysql.model;

public class Road {
    private long id;
    private String name;
    private Address startAddress;
    private Address endAddress;
    private int speedLimit;

    public Road(long id, String name, Address startAddress, Address endAddress, int speedLimit) {
        this.id = id;
        this.name = name;
        this.startAddress = startAddress;
        this.endAddress = endAddress;
        this.speedLimit = speedLimit;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getStartAddress() {
        return startAddress;
    }

    public void setStartAddress(Address startAddress) {
        this.startAddress = startAddress;
    }

    public Address getEndAddress() {
        return endAddress;
    }

    public void setEndAddress(Address endAddress) {
        this.endAddress = endAddress;
    }

    public int getSpeedLimit() {
        return speedLimit;
    }

    public void setSpeedLimit(int speedLimit) {
        this.speedLimit = speedLimit;
    }

    @Override
    public String toString() {
        return "Road{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", startAddress=" + startAddress +
                ", endAddress=" + endAddress +
                ", speedLimit=" + speedLimit +
                '}';
    }
}
