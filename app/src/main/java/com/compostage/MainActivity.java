package com.compostage;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.StrictMode;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.compostage.Data.HttpRequester;
import com.compostage.Data.JsonParser;
import com.compostage.Data.User;
import com.compostage.Exceptions.InvalidServerQuery;

import org.json.JSONException;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private User user;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        final db_query_engine query_engine = new db_query_engine(MainActivity.this);
        insertData createData = new insertData(query_engine, MainActivity.this);

        createData.insert();

        Button login = findViewById(R.id.login);
        Button forgotPassword = findViewById(R.id.forgot_pass);

        findViewById(R.id.username).requestFocus();

        login.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {

                String username = ((EditText)findViewById(R.id.username)).getText().toString();
                String password = ((EditText)findViewById(R.id.password)).getText().toString();
                user = new User(username, query_engine);
                String url;


                if (!username.isEmpty() && !password.isEmpty()) {
                    try {

                        user.fetch_data(); //pass this object to the next window
                        url = String.format(ServerQueries.VALIDATE_PASSWORD, password, user.getPassword());
                        JsonParser isValid = new JsonParser(new HttpRequester(url).processRequest());


                        if (isValid.getField("isValid").equals("true")) {

                            Toast.makeText(MainActivity.this, "WELCOME!",
                                    Toast.LENGTH_LONG).show();
                            loadDashboard();

                        } else {

                            invalid_password();
                            reset_password();

                        }

                    } catch (JSONException e) {

                        Toast.makeText(MainActivity.this, "Invalid username/password",
                                Toast.LENGTH_LONG).show();
                        reset_username();
                        reset_password();
                        findViewById(R.id.username).requestFocus();

                    } catch (IOException e) {

                        //if the connection fails, check locally
                        user.fetch_data_locally();
                        if (user.test_password(password)) {
                            query_engine.close();
                            loadDashboard();
                        } else {

                            invalid_password();
                            reset_password();
                        }

                    } catch (InvalidServerQuery invalidServerQuery) {
                        invalidServerQuery.printStackTrace();
                    }
                } else {

                    if (username.isEmpty()) {
                        invalid_username();
                        reset_username();
                    } else {
                        invalid_password();
                        reset_password();
                    }
                }
            }
        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LayoutInflater li = LayoutInflater.from(MainActivity.this);
                final View promptsView = li.inflate(R.layout.prompt, null);

                AlertDialog.Builder alertDialogBuilder =
                        new AlertDialog.Builder(MainActivity.this);

                alertDialogBuilder.setView(promptsView);

                final EditText userInput = (EditText)
                        promptsView.findViewById(R.id.editTextDialogUserInput);

                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("Ok",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                        EditText username = promptsView.findViewById(R.id.editTextDialogUserInput);
                                        User tmp = new User(username.getText().toString(), query_engine);

                                        try {

                                            tmp.fetch_data();
                                        } catch (InvalidServerQuery invalidServerQuery) {

                                        } catch (IOException e) {
                                            tmp.fetch_data_locally();
                                        }

                                        if (!tmp.is_loaded_properly()) {
                                            dialog.cancel();
                                        } else {

                                            Intent forgot_password = new Intent(MainActivity.this, RecoverPassword.class);
                                            forgot_password.putExtra("currentUser", tmp.getUsername());
                                            MainActivity.this.startActivity(forgot_password);
                                        }
                                    }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });
    }

    public void reset_username() {
        ((EditText) findViewById(R.id.username)).getText().clear();
    }

    public void reset_password() {
        ((EditText) findViewById(R.id.password)).getText().clear();
    }

    public void invalid_password() {
        ((EditText) findViewById(R.id.password)).getText().clear();
        findViewById(R.id.password).requestFocus();
        Toast.makeText(MainActivity.this, "Invalid password",
                Toast.LENGTH_LONG).show();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void loadDashboard() {
        Intent dashboard = new Intent(MainActivity.this, Dashboard.class);

        dashboard.putExtra("currentUser", this.user.getUsername());
        MainActivity.this.startActivity(dashboard);
    }

    public void invalid_username() {
        ((EditText) findViewById(R.id.username)).getText().clear();
        findViewById(R.id.username).requestFocus();
        Toast.makeText(MainActivity.this, "Invalid username",
                Toast.LENGTH_LONG).show();
    }

}
