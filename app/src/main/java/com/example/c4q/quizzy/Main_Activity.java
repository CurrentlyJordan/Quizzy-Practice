package com.example.c4q.quizzy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by jordansmith on 9/28/16.
 */

public class Main_Activity extends AppCompatActivity implements View.OnClickListener{

    EditText userNameView;
    Button nextButton;
    public static final String USER_NAME = "username";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.greet_page);

        userNameView = (EditText) findViewById(R.id.user_name);
        nextButton = (Button) findViewById(R.id.next_btn);
        nextButton.setOnClickListener(this);


    }


    @Override
    public void onClick(View view) {

        Intent intent = new Intent(this,QuizActivity.class);
        intent.putExtra(USER_NAME,userNameView.getText().toString());
        startActivity(intent);

    }
}