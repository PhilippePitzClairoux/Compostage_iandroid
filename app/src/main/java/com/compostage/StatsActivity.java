package com.compostage;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class StatsActivity extends AppCompatActivity {

    ListView listView;

    public StatsActivity(){
        //Ajout manuel de Zone
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

        zone.add(z);

        z = new Zone(2,"Zone B");
        zone.add(z);
        z = new Zone(1,"Zone C");
        zone.add(z);

    }

    public ArrayList<Zone> zone = new ArrayList<>();

    //String zone[] = {"Zone A", "Zone B", "Zone C"};
    String bed[] = {"Bed A", "Bed B", "Bed C"};
    String temp[] = {"32C", "22C", "45C"};
    String humidity[] = {"50%", "25%", "33%"};
    String ph[] = {"7", "2", "9"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stats);

        listView = findViewById(R.id.listView);

        MyAdapter adapter = new MyAdapter(this, zone, bed, temp, humidity, ph);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    Toast.makeText(StatsActivity.this, "aa", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    class MyAdapter extends ArrayAdapter<String>{
        Context context;
        ArrayList<Zone> rZone;
        String rBed[];
        String rTemp[];
        String rHumidity[];
        String rPh[];
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

        MyAdapter (Context c, ArrayList<Zone> zone, String bed[], String temp[], String humidity[], String ph[]){
            super(c,R.layout.row, R.id.zone, bed);
            this.context = c;
            this.rZone = new ArrayList<>(zone);
            this.rBed = bed;
            this.rTemp = temp;
            this.rHumidity = humidity;
            this.rPh = ph;
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

            zone.setText(rZone.get(position).getName());
            bed.setText(rBed[position]);
            temp.setText(rTemp[position]);
            hum.setText(rHumidity[position]);
            ph.setText(rPh[position]);
            space.setBackgroundColor(Color.parseColor(phColor[Integer.parseInt(rPh[position])]));

            return row;
        }
    }
}
