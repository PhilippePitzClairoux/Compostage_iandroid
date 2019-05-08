package com.compostage;

import android.hardware.Sensor;

import java.util.ArrayList;

public class Zone {
    public Zone(){
        this.raspberryPi = null;
        this.name = "";
    }

    public Zone(int id, String name){
        this.setId(id);
        this.setName(name);
    }

    public Zone(int id, int bedId, String name){
        this.setId(id);
        this.setBedId(bedId);
        this.setName(name);
    }

    @Override
    public String toString()
    {
        return this.getName();
    }

    private int id;
    private int bedId;
    private String name;

    private RaspberryPi raspberryPi;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBedId() {
        return bedId;
    }

    public void setBedId(int bedId) {
        this.bedId = bedId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RaspberryPi getRaspberryPi() {
        return raspberryPi;
    }

    public void setRaspberryPi(RaspberryPi raspberryPi) {
        this.raspberryPi = raspberryPi;
    }
}
