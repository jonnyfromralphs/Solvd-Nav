package com.solvd.model;

public class ZipCode {
    private long id;
    private int code;
    private long cityId;

    public ZipCode(long id, int code, long cityId) {
        this.id = id;
        this.code = code;
        this.cityId = cityId;
    }

    public ZipCode(){}

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

    public long getCityId() {
        return cityId;
    }

    public void setCity(long cityId) {
        this.cityId = cityId;
    }

    @Override
    public String toString() {
        return "ZipCode{" +
                "id=" + id +
                ", code=" + code +
                ", city id=" + cityId +
                '}';
    }
}
