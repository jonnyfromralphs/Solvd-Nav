package com.solvd.model;

import java.util.Objects;

public class Vertex {
    private String id;
    private double latitude;
    private double longitude;

    public Vertex(String id, double latitude, double longitude) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    @Override
    public String toString() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertex vertex = (Vertex) o;
        return Double.compare(vertex.latitude, latitude) == 0 && Double.compare(vertex.longitude, longitude) == 0 && Objects.equals(id, vertex.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, latitude, longitude);
    }
}
