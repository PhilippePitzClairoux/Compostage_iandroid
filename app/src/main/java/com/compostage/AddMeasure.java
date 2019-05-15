package com.compostage;

import android.database.Cursor;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
//import android.widget.ArrayAdapter;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;


import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddMeasure extends AppCompatActivity {

    private Spinner bed;
    private Spinner zone;
    private Button send;
    private EditText temperature;
    private EditText ph;
    private EditText humidity;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.add_measure);

        bed= (Spinner)findViewById(R.id.bed);
        zone= (Spinner) findViewById(R.id.zone);
        send= (Button) findViewById(R.id.send);
        temperature= (EditText) findViewById(R.id.temperature);
        ph= (EditText) findViewById(R.id.ph);
        humidity= (EditText) findViewById(R.id.humidity);

        final db_query_engine db = new db_query_engine(this);

        insertData insertData = new insertData(db,this);
        insertData.insert();

         Cursor cursor = db.execution_with_return("SELECT * FROM bed");

        String[] column = new String[]{"bed_name"};
        int[] spinner = new int[]{android.R.id.text1};

        SimpleCursorAdapter adapterB = new SimpleCursorAdapter(this,android.R.layout.simple_spinner_item,cursor, column,spinner,0);

        adapterB.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        bed.setAdapter(adapterB);

        cursor = db.execution_with_return("SELECT * FROM zone");

        column = new String[]{"zone_name"};

        final SimpleCursorAdapter adapterZ = new SimpleCursorAdapter(this,android.R.layout.simple_spinner_item,cursor, column,spinner,0);
        adapterZ.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        zone.setAdapter(adapterZ);

        send.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){


                Cursor cursor = (Cursor)adapterZ.getItem(0);
                String zoneName = cursor.getString(cursor.getColumnIndex("zone_name"));

                String query = "SELECT sensor_id FROM sensor JOIN raspberry_pi ON sensor.raspberry_pi_id=raspberry_pi.raspberry_pi_id JOIN zone ON zone.zone_id=raspberry_pi.zone_id WHERE zone_name = '"
                        + zoneName+"'";

                 cursor = db.execution_with_return(query);

                int sensorId = Integer.parseInt(cursor.getString(cursor.getColumnIndex("sensor_id")));

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy hh-mm-ss");
                String date = simpleDateFormat.format(new Date());

                /*Toast.makeText(getApplicationContext(),
                        date, Toast.LENGTH_LONG)
                        .show();*/

                if(!(temperature.getText().toString()).equals("")||!(ph.getText().toString()).equals("")||!(humidity.getText().toString()).equals("")) {
                    query = "INSERT INTO measures (sensor_id, measure_timestamp) VALUES (" + sensorId + ",'" + date + "')";

                    db.execution(query);

                    if (!(temperature.getText().toString()).equals("")) {


                        float value = Float.parseFloat(temperature.getText().toString());
                        SensorMeasure measure = new SensorMeasure(value, date, "TEMPERATURE");


                        measure.addToDatabase(db,sensorId);
                    }

                    if (!(ph.getText().toString()).equals("")) {


                        float value = Float.parseFloat(ph.getText().toString());
                        SensorMeasure measure = new SensorMeasure(value, date, "PH");


                        measure.addToDatabase(db,sensorId);
                    }

                    if (!(humidity.getText().toString()).equals("")) {


                        float value = Float.parseFloat(humidity.getText().toString());
                        SensorMeasure measure = new SensorMeasure(value, date, "HUMIDITY");


                        measure.addToDatabase(db,sensorId);
                    }
                }
            }
        });
    }





}
