package com.solvd.db.mysql.model;

public class ZipCode {
    private long id;
    private int code;
    private City city;

    public ZipCode(long id, int code, City city) {
        this.id = id;
        this.code = code;
        this.city = city;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "ZipCode{" +
                "id=" + id +
                ", code=" + code +
                ", city=" + city +
                '}';
    }
}
