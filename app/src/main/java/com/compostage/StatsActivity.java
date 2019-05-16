package com.compostage;

import android.content.Context;

import android.content.Intent;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import android.widget.Spinner;
import android.widget.TextView;


import java.util.ArrayList;

public class StatsActivity extends AppCompatActivity {

    ListView listView;

    public void clear(){
        zone.clear();
        bed.clear();
        listSpinner.clear();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.navigation_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.navMenu:
            {
                Intent in = new Intent(getBaseContext(), MainActivity.class);
                startActivity(in);
                this.finish();
                break;
            }
            case R.id.navStats:
            {
                Intent in = new Intent(getBaseContext(), StatsActivity.class);
                startActivity(in);
                this.finish();
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    public void loadZone(){

        /*
        //ajouter la DB au telephone
        db_query_engine engine = new db_query_engine(this);
        insertData insert = new insertData(engine,this);
        insert.insert();
        */

        //Ajout manuel de Zone
        ////////////////////////////////////////////////////////////////////////////////



        /*
        //ajouter la DB au telephone
        db_query_engine engine = new db_query_engine(this);
        insertData insert = new insertData(engine,this);
        insert.insert();
        */

        //Ajout manuel de Zone
        ////////////////////////////////////////////////////////////////////////////////
        SensorMeasure measureTemp = new SensorMeasure(32f);
        SensorMeasure measureHum = new SensorMeasure(50f);
        SensorMeasure measurePh = new SensorMeasure(7f);

        RaspSensor raspSensorTemp = new RaspSensor(1,measureTemp);
        RaspSensor raspSensorHum = new RaspSensor(1,measureHum);
        RaspSensor raspSensorPh = new RaspSensor(1,measurePh);

        RaspberryPi raspberryPi = new RaspberryPi(1,1);

        raspberryPi.addSensor(raspSensorTemp);
        raspberryPi.addSensor(raspSensorHum);
        raspberryPi.addSensor(raspSensorPh);

        Zone z = new Zone(1,"Zone A");

        z.setRaspberryPi(raspberryPi);

        z.setBedName("Bed W");
        zone.add(z);

        measureTemp = new SensorMeasure(44f);
        measureHum = new SensorMeasure(22f);
        measurePh = new SensorMeasure(2f);

        raspSensorTemp = new RaspSensor(1,measureTemp);
        raspSensorHum = new RaspSensor(1,measureHum);
        raspSensorPh = new RaspSensor(1,measurePh);

        raspberryPi = new RaspberryPi(1,1);

        raspberryPi.addSensor(raspSensorTemp);
        raspberryPi.addSensor(raspSensorHum);
        raspberryPi.addSensor(raspSensorPh);

        z = new Zone(1,"Zone A");

        z.setRaspberryPi(raspberryPi);
        z.setBedName("Bed F");
        zone.add(z);

        //z = new Zone(2,"Zone B");
        zone.add(z);
        //z = new Zone(1,"Zone C");
        zone.add(z);
        ///////////////////////////////////////////////////////////////////////////////////
        // ^ a modifier pour load la BD
    }

    public void setBeds(){
        for (int i = 0; i < zone.size(); i++)
        {
            bed.add(zone.get(i).getBedName());
        }
    }

    public void filterBeds(String str){
        for (int i = 0; i < bed.size(); i++)
        {
            if(bed.get(i).equals(str)) {
                bed.remove(bed.get(i));
                zone.remove(i);
                i--;
            }
        }
    }

    public void loadSpinner(){
        spinner = (Spinner)findViewById(R.id.spinner);
        listSpinner.add("None");
        for (int i = 0; i < bed.size(); i++) {
            if(!listSpinner.contains(bed.get(i)))
                listSpinner.add(bed.get(i));
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,listSpinner);
        spinner.setAdapter(adapter);
/*
        spinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
*/
    }

    private ArrayList<Zone> zone = new ArrayList<>();
    private ArrayList<String> bed = new ArrayList<>();

    private Spinner spinner;
    ArrayList<String> listSpinner = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stats);

        loadZone();
        setBeds();
        loadSpinner();

        listView = findViewById(R.id.listView);

        MyAdapter adapter = new MyAdapter(this, zone, bed);
        listView.setAdapter(adapter);

    }

    class MyAdapter extends ArrayAdapter<String>{
        Context context;
        ArrayList<Zone> rZone;

        ArrayList<String> rBed;

        String phColor[] = {
                "#ffffff",
                "#aa0000",
                "#ff2322",
                "#f96352",
                "#c15613",
                "#f99e3b",
                "#ffe500",
                "#3bb200",
                "#5dccae",
                "#32abd6",
                "#a23bd6",
                "#4a78db",
                "#141fba",
                "#701ca0",
                "#63007a"};


        MyAdapter (Context c, ArrayList<Zone> zone, ArrayList<String> bed){
            super(c,R.layout.row, R.id.zone, bed);
            this.context = c;
            this.rZone = new ArrayList<>(zone);
            this.rBed = new ArrayList<>(bed);

        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.row, parent, false);
            TextView zone = row.findViewById(R.id.zone);
            TextView bed = row.findViewById(R.id.bed);
            TextView temp = row.findViewById(R.id.temp);
            TextView hum = row.findViewById(R.id.humidity);
            TextView ph = row.findViewById(R.id.ph);
            TextView space = row.findViewById(R.id.space);

            TextView raspId = row.findViewById(R.id.raspId);
            TextView userId = row.findViewById(R.id.userId);

            zone.setText(rZone.get(position).getName());
            bed.setText(rZone.get(position).getBedName());
            temp.setText(String.valueOf(rZone.get(position).getTemp()));
            hum.setText(String.valueOf(rZone.get(position).getHum()));
            ph.setText(String.valueOf(rZone.get(position).getPh()));
            space.setBackgroundColor(Color.parseColor(phColor[Math.round(rZone.get(position).getPh())]));
            raspId.setText(String.valueOf(rZone.get(position).getRaspberryPi().getId()));
            userId.setText(String.valueOf(rZone.get(position).getRaspberryPi().getUserId()));


            return row;
        }
    }
}
