package com.compostage;

public class RaspSensor {
    public RaspSensor(){
        this.setType("");
        this.setState("");
        this.setAcquisitionDate("");
        this.setSerial("");
        this.setMeasure(null);
    }

    public RaspSensor(int id){
        this.setId(id);
        this.setType("");
        this.setState("");
        this.setAcquisitionDate("");
        this.setSerial("");
        this.setMeasure(null);
    }

    public RaspSensor(int id, SensorMeasure measure){
        this.setId(id);
        this.setType("");
        this.setState("");
        this.setAcquisitionDate("");
        this.setSerial("");
        this.setMeasure(measure);
    }

    public RaspSensor(int id, String type, String state, String acquisitionDate, String serial, SensorMeasure measure){
        this.setId(id);
        this.setType(type);
        this.setState(state);
        this.setAcquisitionDate(acquisitionDate);
        this.setSerial(serial);
        this.setMeasure(measure);
    }

    private int id;
    private String type;
    private String state;
    private String acquisitionDate;
    private String serial;
    private SensorMeasure measure;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAcquisitionDate() {
        return acquisitionDate;
    }

    public void setAcquisitionDate(String acquisitionDate) {
        this.acquisitionDate = acquisitionDate;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public SensorMeasure getMeasure() {
        return measure;
    }

    public void setMeasure(SensorMeasure measure) {
        this.measure = measure;
    }
}
