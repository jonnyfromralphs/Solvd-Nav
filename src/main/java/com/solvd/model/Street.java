package com.solvd.model;

import java.util.ArrayList;
import java.util.List;

public class Street {
    private long id;
    private String name;
    private List<Address> addresses;

    public Street() {
        addresses = new ArrayList<>();
    }

    public Street(long id, String name) {
        this.id = id;
        this.name = name;
        addresses = new ArrayList<>();
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

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    @Override
    public String toString() {
        return "Street{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", addresses=" + addresses +
                '}';
    }
}
