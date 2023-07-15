package com.solvd.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class Vertex {
    private static final AtomicInteger ID_GENERATOR = new AtomicInteger(0);
    private static int vertexCount = 0;
    private int id;
    private String name;
    private double latitude;
    private double longitude;
    private int frequency;
    List<Vertex> busStopList;


    public Vertex(String name, double latitude, double longitude) {
        this.id = ID_GENERATOR.incrementAndGet();
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        vertexCount++;
    }



    public Vertex(String name, double latitude, double longitude, int frequency) {
        this.id = ID_GENERATOR.incrementAndGet();
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
        return name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static int getVertexCount() {
        return vertexCount;
    }

    public static void setVertexCount(int vertexCount) {
        Vertex.vertexCount = vertexCount;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

}
