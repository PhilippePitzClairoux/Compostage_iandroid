package com.compostage;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
//import android.widget.ArrayAdapter;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class AddMeasure extends AppCompatActivity {

    private Spinner bed;
    private Spinner zone;
    private String[] bedList;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.add_measure);

        bed= (Spinner)findViewById(R.id.bed);
        zone= (Spinner) findViewById(R.id.zone);

        db_query_engine db = new db_query_engine(this);

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

        SimpleCursorAdapter adapterZ = new SimpleCursorAdapter(this,android.R.layout.simple_spinner_item,cursor, column,spinner,0);
        adapterZ.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        zone.setAdapter(adapterZ);

    }

}
