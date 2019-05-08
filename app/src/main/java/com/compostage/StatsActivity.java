package com.compostage;

import android.content.Context;
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

public class StatsActivity extends AppCompatActivity {

    ListView listView;
    String zone[] = {"test1", "test2", "test3"};
    String bed[] = {"test1 Description", "test2 Description", "test3 Description"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stats);

        listView = findViewById(R.id.listView);

        MyAdapter adapter = new MyAdapter(this, zone, bed);
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
        String rZone[];
        String rBed[];

        MyAdapter (Context c, String zone[], String bed[]){
            super(c,R.layout.row, R.id.zone, bed);
            this.context = c;
            this.rZone = zone;
            this.rBed = bed;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.row, parent, false);
            TextView zone = row.findViewById(R.id.zone);
            TextView bed = row.findViewById(R.id.bed);

            zone.setText(rZone[position]);
            bed.setText(rBed[position]);

            return row;
        }
    }
}
