package com.solvd.db.mysql.model;

public class PublicTransportStop {
    private long id;
    private String name;
    private PublicTransportRoute route;
    private Street street;
    private TransportType transport;
    private int order;

    public PublicTransportStop(long id, String name, PublicTransportRoute route, Street street, TransportType transport, int order) {
        this.id = id;
        this.name = name;
        this.route = route;
        this.street = street;
        this.transport = transport;
        this.order = order;
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

    public PublicTransportRoute getRoute() {
        return route;
    }

    public void setRoute(PublicTransportRoute route) {
        this.route = route;
    }

    public Street getStreet() {
        return street;
    }

    public void setStreet(Street street) {
        this.street = street;
    }

    public TransportType getTransport() {
        return transport;
    }

    public void setTransport(TransportType transport) {
        this.transport = transport;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "PublicTransportStop{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", route=" + route +
                ", street=" + street +
                ", transport=" + transport +
                ", order=" + order +
                '}';
    }
}
