package com.compostage;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.compostage.Data.HttpRequester;
import com.compostage.Data.JsonParser;
import com.compostage.Data.User;
import com.compostage.Data.UserType;
import com.compostage.Exceptions.InvalidServerQuery;

import org.json.JSONException;

import java.io.IOException;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Button login = findViewById(R.id.login);

        findViewById(R.id.username).requestFocus();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = ((EditText)findViewById(R.id.username)).getText().toString();
                String password = ((EditText)findViewById(R.id.password)).getText().toString();

                String url = String.format(ServerQueries.GET_USER_INFO, username);


                if (!username.isEmpty() && !password.isEmpty()) {
                    try {

                        JsonParser ans = new JsonParser(new HttpRequester(url).processRequest());
                        url = String.format(ServerQueries.VALIDATE_PASSWORD, password, ans.getField("user_password"));

                        String passValidation = new HttpRequester(url).processRequest();
                        JsonParser passwordIsValid = new JsonParser(passValidation);

                        if (passwordIsValid.getField("isValid").equals("true")) {

                            Toast.makeText(MainActivity.this, "WELCOME!",
                                    Toast.LENGTH_LONG).show();
                            User user = new User(username);
                            user.fetch_data(); //pass this object to the next window

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

                        e.printStackTrace();

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

    public void invalid_username() {
        ((EditText) findViewById(R.id.username)).getText().clear();
        findViewById(R.id.username).requestFocus();
        Toast.makeText(MainActivity.this, "Invalid username",
                Toast.LENGTH_LONG).show();
    }

}
