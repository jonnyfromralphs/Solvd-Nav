package com.solvd.model;

public class BusStop {
    private String busStopName;
    private double latitude;
    private double longitude;
    private int frequency;

    public BusStop(String busStopName, double latitude, double longitude, int frequency) {
        this.busStopName = busStopName;
        this.latitude = latitude;
        this.longitude = longitude;
        this.frequency = frequency;

    }

    public String getBusStopName() {
        return busStopName;
    }

    public void setBusStopName(String busStopName) {
        this.busStopName = busStopName;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }


}
