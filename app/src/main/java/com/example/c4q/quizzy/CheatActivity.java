package com.example.c4q.quizzy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class CheatActivity extends QuizActivity {
    private Question [] copyOfQuestions;
    private Question currentquestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Button cheatbutton = (Button) findViewById(R.id.cheat_button);
       cheatbutton.setVisibility(View.GONE);
        copyOfQuestions = super.questionBank;


        TextView questionTV = (TextView)findViewById(R.id.question_text_view);

        Intent intent;
        intent = getIntent();
        String myQuestion = intent.getExtras().getString("CURRENT QUESTION");
        boolean myAnswer = intent.getExtras().getBoolean("CURRENT ANSWER");
        int currentIndex = intent.getExtras().getInt("CURRENT INDEX");
        String stringAnswer = "The answer is " + myAnswer;
        Toast.makeText(this, stringAnswer, Toast.LENGTH_SHORT).show();



        questionTV.setText(copyOfQuestions[currentIndex].getTextResId());
    }
}
