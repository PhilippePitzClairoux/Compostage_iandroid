package com.compostage;

public class SensorMeasure {
    public SensorMeasure(){
        this.setValue(null);
        this.setTimeStamp("");
    }

    public SensorMeasure(float value){
        this.setValue(value);
        this.setTimeStamp("");
    }

    public SensorMeasure(float value, String timeStamp){
        this.setValue(value);
        this.setTimeStamp(timeStamp);
    }

    public SensorMeasure(int id, float value, String timeStamp){
        this.setId(id);
        this.setValue(value);
        this.setTimeStamp(timeStamp);
    }

    private int id;
    private Float value;
    private String timeStamp;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
}
