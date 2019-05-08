package com.compostage;

public class Zone {
    public Zone(){

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
}
