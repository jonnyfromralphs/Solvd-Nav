package com.solvd.model.graph;

import java.util.Objects;

public class Vertex {
    private static int vertexCount = 0;
    private int id;
    private String name;
    private double latitude;
    private double longitude;
    private int frequency;

    public Vertex(String name, double latitude, double longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        vertexCount++;
    }

    public Vertex(String name, double latitude, double longitude, int frequency) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.frequency = frequency;
        vertexCount++;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertex vertex = (Vertex) o;
        return Double.compare(vertex.latitude, latitude) == 0 && Double.compare(vertex.longitude, longitude) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, latitude, longitude);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFrequency() {
        return frequency;
    }
}
