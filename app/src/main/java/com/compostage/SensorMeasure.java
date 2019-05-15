package com.compostage;

import android.database.Cursor;
import android.widget.Toast;

public class SensorMeasure {
    public SensorMeasure(){
        this.setValue(null);
        this.setTimeStamp("");
    }

    public SensorMeasure(float value){
        this.setValue(value);
        this.setTimeStamp("");
    }

    public SensorMeasure(float value, String timeStamp, String type){
        this.setValue(value);
        this.setTimeStamp(timeStamp);
        this.setType(type);
    }

    public SensorMeasure(int id, float value, String timeStamp){
        this.setId(id);
        this.setValue(value);
        this.setTimeStamp(timeStamp);
    }

    private int id;
    private Float value;
    private String timeStamp;
    private String type;

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

    public String getType(){return type;}

    public void setType(String type){this.type=type;}

    public void addToDatabase(db_query_engine db, int sensorId){


        String query = "SELECT measure_id FROM measures WHERE sensor_id = "+sensorId+" AND measure_timestamp = '"+timeStamp+"'";

        Cursor cursor = db.execution_with_return(query);

        int id = Integer.parseInt(cursor.getString(cursor.getColumnIndex("measure_id")));

        query = "SELECT measure_type_id FROM measure_type WHERE measure_type_name = '"+type+"'";

        cursor = db.execution_with_return(query);

        int typeId=Integer.parseInt(cursor.getString(cursor.getColumnIndex("measure_type_id")));

        query = "INSERT INTO ta_measure_type (measure_id, measure_type_id, measure_value) VALUES ("+id+","+typeId+","+value+")";

        db.execution(query);
    }
}
