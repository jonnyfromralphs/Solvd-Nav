package com.solvd.model;

public class Road {
    private long id;
    private String name;
    private long startAddress;
    private long endAddress;
    private int speedLimit;

    public Road() {}

    public Road(long id, String name, long startAddress, long endAddress, int speedLimit) {
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

    public long getStartAddress() {
        return startAddress;
    }

    public void setStartAddress(long startAddress) {
        this.startAddress = startAddress;
    }

    public long getEndAddress() {
        return endAddress;
    }

    public void setEndAddress(long endAddress) {
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
                ", startAddressId=" + startAddress +
                ", endAddressId=" + endAddress +
                ", speedLimit=" + speedLimit +
                '}';
    }
}
