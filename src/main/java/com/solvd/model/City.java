package com.solvd.model;

import java.util.ArrayList;
import java.util.List;

public class City {
    private long id;
    private String name;
    private List<ZipCode> zipCodes;

    public City() {}

    public City(long id, String name){
        this.id = id;
        this.name = name;
        zipCodes = new ArrayList<>();
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

    public List<ZipCode> getZipCodes() {
        return zipCodes;
    }

    public void setZipCodes(List<ZipCode> zipCodes) {
        this.zipCodes = zipCodes;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                 '}';
    }
}
