package com.example.c4q.quizzy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class LaunchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.launch);

        Intent intent;
        intent = new Intent(getApplicationContext(),QuizActivity.class);
        startActivity(intent);
        //new Intent(this,QuizActivity.class);
        //new Intent(splashActivity.this,QuizActivity.class);
        //new Intent(getContext,QuizActivity.class);
    }
}
