package com.solvd.model.graph;

public class Edge {
    private long id;
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

    public Vertex getEnd() {
        return end;
    }

    public double getSpeed() {
        return speed;
    }

    public String getRoadName() {
        return roadName;
    }

    @Override
    public String toString() {
        return " take road "+roadName+" -> ";
    }
}
