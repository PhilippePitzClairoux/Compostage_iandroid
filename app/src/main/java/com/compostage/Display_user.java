package com.compostage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.compostage.Display_Emp.User;


public class Display_user extends AppCompatActivity {

    public static final User test = new User();

    EditText usernamedisp = findViewById(R.id.usernameTxt);
    EditText typedisp = findViewById(R.id.typeTxt);
    EditText passdisp = findViewById(R.id.passTxt);
    EditText maildisp = findViewById(R.id.mailTxt);
    EditText questiondisp = findViewById(R.id.questionTxt);
    EditText answerdisp = findViewById(R.id.answerTxt);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display__emp);

        test.createNewUser("Admin", 3, "qwerty123",
                "adm1@gmail.com", "What is your pet's name ?", "Rex");

        usernamedisp.setText(test.getUserName());
        typedisp.setText(test.getUsertType());
        passdisp.setText(test.getPassword());
        maildisp.setText(test.getEmail());
        questiondisp.setText(test.getQuestion());
        answerdisp.setText(test.getAnswer());

    }

    //Change activity on click
    public void ModifyOnClick(View v) {
        startActivity(new Intent(Display_user.this, Update_User.class));
    }
}