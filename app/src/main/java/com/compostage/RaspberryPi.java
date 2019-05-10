package com.compostage;

import android.hardware.Sensor;

import java.util.ArrayList;

public class RaspberryPi {

    public RaspberryPi(){
        this.setId(0);
        this.setUserId(0);
        this.type = "";
        this.acquisitionDate = "";
        this.capacity = "";
        this.arrSensor = new ArrayList<>();
    }

    public RaspberryPi(int id, int userId){
        this.setId(id);
        this.setUserId(userId);
        this.arrSensor = new ArrayList<>();
    }

    public RaspberryPi(int id, int userId, String type, String acqui, String capacity){
        this.setId(id);
        this.setUserId(userId);
        this.setType(type);
        this.setAcquisitionDate(acqui);
        this.setCapacity(capacity);
        this.arrSensor = new ArrayList<>();
    }

    public void addSensor(RaspSensor sensor){
        this.getArrSensor().add(sensor);
    }

    private int id;
    private int userId;
    private String type;
    private String acquisitionDate;
    private String capacity;
    private ArrayList<RaspSensor> arrSensor;

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

    public ArrayList<RaspSensor> getArrSensor() {
        return arrSensor;
    }

    public void setArrSensor(ArrayList<RaspSensor> arrSensor) {
        this.arrSensor = arrSensor;
    }
}
