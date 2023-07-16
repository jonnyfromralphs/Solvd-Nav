package com.solvd.model.graph;

import java.util.concurrent.atomic.AtomicInteger;

public class Edge {
    private static final AtomicInteger ID_GENERATOR = new AtomicInteger(0);
    private long id;
    private Vertex start;
    private Vertex end;
    private double speed;

    private String roadName;

    public Edge(Vertex edgeStart, Vertex edgeEnd, double speed, String roadName) {
        this.id = ID_GENERATOR.incrementAndGet();
        this.start = edgeStart;
        this.end = edgeEnd;
        this.speed = speed;
        this.roadName = roadName;
    }

    public Vertex getStart() {
        return start;
    }

    public void setStart(Vertex start) {
        this.start = start;
    }

    public Vertex getEnd() {
        return end;
    }

    public void setEnd(Vertex end) {
        this.end = end;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }


    public String getRoadName() {
        return roadName;
    }

    public void setRoadName(String roadName) {
        this.roadName = roadName;
    }

    @Override
    public String toString() {
        return " take road "+roadName+" -> ";
    }
}
