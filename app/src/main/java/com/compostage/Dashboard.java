package com.compostage;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.compostage.Data.User;

public class Dashboard extends AppCompatActivity {

    private static final String WELCOME_MESSAGE = "Welcome %s!";
    private User user;
    private db_query_engine query_engine;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.navigation_menu, menu);

        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.navLogout:
            {
                Intent in = new Intent(getBaseContext(), MainActivity.class);
                startActivity(in);
                this.finish();
                return true;
            }
            case R.id.navStats:
            {
                Intent in = new Intent(getBaseContext(), StatsActivity.class);
                startActivity(in);
                return true;
            }case R.id.backToHome:
            {
                Dashboard.this.finishAndRemoveTask();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);

        this.query_engine = new db_query_engine(this);
        this.user = new User(getIntent().getStringExtra("currentUser"), this.query_engine);

        this.user.fetch_data_locally();

        TextView greetings_message = findViewById(R.id.welcome_message);
        greetings_message.setText(String.format(WELCOME_MESSAGE, user.getUsername()));
    }

}
