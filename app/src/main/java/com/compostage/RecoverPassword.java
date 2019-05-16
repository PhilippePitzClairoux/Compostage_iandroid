package com.compostage;

import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.compostage.Data.User;
import com.compostage.Exceptions.InvalidServerQuery;

import java.io.IOException;

public class RecoverPassword extends AppCompatActivity {

    private User user;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recover_password);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        final EditText password1 = findViewById(R.id.password1);
        final EditText password2 = findViewById(R.id.password2);
        final Button save = findViewById(R.id.save);

        TextView question = findViewById(R.id.question);
        EditText answer = findViewById(R.id.answer);
        final TextView error = findViewById(R.id.error);



        db_query_engine engine = new db_query_engine(this);
        this.user = new User(getIntent().getStringExtra("currentUser"), engine);

        try {

            this.user.fetch_data();

        } catch (InvalidServerQuery invalidServerQuery) {

        } catch (IOException e) {
            this.user.fetch_data_locally();
        }


        question.setText(user.getAuthquestion());


        answer.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                        actionId == EditorInfo.IME_ACTION_DONE ||
                        event != null && event.getAction() == KeyEvent.ACTION_DOWN &&
                                event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {

                    if (event == null || !event.isShiftPressed()) {

                        EditText answer = findViewById(R.id.answer);
                        String answerStr = answer.getText().toString();

                        if (user.test_auth_answer(answerStr)) {
                            password1.setEnabled(true);
                            password2.setEnabled(true);
                            save.setEnabled(true);

                            return true;
                        }
                    }
                }
                return false;
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String pass1 = password1.getText().toString();
                String pass2 = password2.getText().toString();

                if (!pass1.isEmpty() && !pass2.isEmpty()) {
                    if (pass1.equals(pass2) && pass1.length() > 4) {

                        user.setPassword(pass1);
                        user.update_data_locally();
//                        CustomNotificationManager.sendNotifications(RecoverPassword.this,
//                                "Important update!", "Password has been recovered!");
                        RecoverPassword.this.finishAndRemoveTask();

                    } else {

                        if (pass1.length() <= 4 || pass2.length() <= 4)
                            error.setText(R.string.pass_too_short);

                        if (!pass1.equals(pass2))
                            error.setText(getString(R.string.error_pass_no_match));
                    }
                } else {
                    error.setText(R.string.not_enough_pass);
                }

            }
        });


    }

}
