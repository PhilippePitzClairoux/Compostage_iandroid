package com.compostage;

import android.hardware.Sensor;

import java.util.ArrayList;

public class RaspberryPi {

    public RaspberryPi(){
        this.type = "";
        this.acquisitionDate = "";
        this.capacity = "";
        this.arrSensor = new ArrayList<>();
    }

    private int id;
    private int userId;
    private String type;
    private String acquisitionDate;
    private String capacity;
    private ArrayList<Sensor> arrSensor;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAcquisitionDate() {
        return acquisitionDate;
    }

    public void setAcquisitionDate(String acquisitionDate) {
        this.acquisitionDate = acquisitionDate;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public ArrayList<Sensor> getArrSensor() {
        return arrSensor;
    }

    public void setArrSensor(ArrayList<Sensor> arrSensor) {
        this.arrSensor = arrSensor;
    }
}