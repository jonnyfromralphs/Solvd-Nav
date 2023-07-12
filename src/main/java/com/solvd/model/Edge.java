package com.solvd.model;

import com.solvd.model.Vertex;

public class Edge {
    private Vertex start;
    private Vertex end;
    private double speed;

    private String roadName;

    public Edge(Vertex edgeStart, Vertex edgeEnd, double speed, String roadName) {
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