package com.compostage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.compostage.Display_Emp.User;

public class Update_User extends AppCompatActivity {

    public static final User test = new User();

    EditText usernamedisp = findViewById(R.id.usernameTxt);
    EditText typedisp = findViewById(R.id.typeTxt);
    EditText passdisp = findViewById(R.id.passTxt);
    EditText maildisp = findViewById(R.id.mailTxt);
    EditText questiondisp = findViewById(R.id.questionTxt);
    EditText answerdisp = findViewById(R.id.answerTxt);

    Button btnCancel = findViewById(R.id.btnCancel);
    Button btnSave = findViewById(R.id.btnSave);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update__user);

        //TODO Gets the current user's info instead of hard coded user
        usernamedisp.setText(test.getUserName());
        typedisp.setText(test.getUsertType());
        passdisp.setText(test.getPassword());
        maildisp.setText(test.getEmail());
        questiondisp.setText(test.getQuestion());
        answerdisp.setText(test.getAnswer());
    }

    //Sets the new info for the user
    public void SaveOnClick(View v){
        //Convert string into int
        String value = typedisp.getText().toString();
        int finalValue = Integer.parseInt(value);

        test.setUsername(usernamedisp.getText().toString());
        test.setUserType(finalValue);
        test.setPassword(passdisp.getText().toString());
        test.setEmail(maildisp.getText().toString());
        test.setQuestion(questiondisp.getText().toString());
        test.setAnswer(answerdisp.getText().toString());

        startActivity(new Intent(Update_User.this, Display_user.class));
    }

    //Change activity on click
    public void CancelOnClick(View v) {
        startActivity(new Intent(Update_User.this, Display_user.class));
    }
}
