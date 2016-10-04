package com.example.c4q.quizzy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class QuizActivity extends AppCompatActivity implements View.OnClickListener {

    private Button trueButton;
    private Button falseButton;
    private Button nextButton;
    private Button prevButton;
    private TextView quizTakerName;
    private TextView questionTextView;
    private Button cheatButton;
    public final int CHEAT_REQUEST = 111;

   Question[] questionBank;
    private int currentIndex = 0;

    public void initializeQuestions() {
        questionBank = new Question[]{
                new Question(R.string.question_static, false),
                new Question(R.string.question_private_class, true),
                new Question(R.string.question_method, false),
                new Question(R.string.question_method_2, false),
                new Question(R.string.question_objects,true),
                new Question(R.string.question_method_3,true),
                new Question(R.string.question_objects_3,true)

        };
        updateQuestion();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);



        initializeViews(); //find view by id methods
        initializeQuestions(); // loads Question objects into array of questions called questionBank
        initializeListeners(); //sets onClickListeners for buttton views.

        Intent intent = getIntent();
        String name = intent.getStringExtra(Main_Activity.USER_NAME);
        quizTakerName.setText("quiztaker: " + name);
    }


    public void initializeViews() {
        trueButton = (Button) findViewById(R.id.true_btn);
        falseButton = (Button) findViewById(R.id.false_btn);
        questionTextView = (TextView) findViewById(R.id.question_text_view);
        nextButton = (Button) findViewById(R.id.next_btn);
        prevButton = (Button)findViewById(R.id.prev_btn);
        quizTakerName = (TextView) findViewById(R.id.quizzer_name);
        cheatButton = (Button) findViewById(R.id.cheat_button);
        resetButtonColors();
    }


    public void initializeListeners(){
        trueButton.setOnClickListener(this);
        falseButton.setOnClickListener(this);
        nextButton.setOnClickListener(this);
        prevButton.setOnClickListener(this);
        cheatButton.setOnClickListener(this);
    }


    public void resetButtonColors(){
        trueButton.setBackgroundResource(android.R.drawable.btn_default);
        falseButton.setBackgroundResource(android.R.drawable.btn_default);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //fixme - implement restartQuiz and add a way to save the quiz taker's score
        switch(item.getItemId()) {
            case R.id.restart_quiz_action:
                restartQuiz();
                break;
            case R.id.save_score:
                Toast.makeText(this, "No implementation found. Implement the restartQuiz method", Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }


    public void updateQuestion() {
        if(currentIndex >= 0 && currentIndex < questionBank.length ){
            Question currentQuestion = questionBank[currentIndex];
            int textResId = currentQuestion.getTextResId();
            questionTextView.setText(textResId);
        }else{
            currentIndex = 0;
            Toast.makeText(this, "Out of bounds Exception. Resetting index to Zero", Toast.LENGTH_SHORT).show();
        }
    }

    public void randomQuestion(){
        int count = 0;
        int questionNumber = questionBank.length;
        Random rand = new Random();
        int randNumber = rand.nextInt(questionNumber-1);
        if(count <= questionNumber){
            Question currentQuestion = questionBank[randNumber];
            int textResId = currentQuestion.getTextResId();
            questionTextView.setText(textResId);
        }
        else{ count = 0;}


    }


    //fixme
    public void restartQuiz() {
        Toast.makeText(this, "New Quiz", Toast.LENGTH_LONG).show();
        currentIndex = 0;
        initializeQuestions();
        updateQuestion();

    }

    public Question getCurrentQuestion() {
        return questionBank[currentIndex];
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == CHEAT_REQUEST){
            Toast.makeText(this, "you cheated!!!",Toast.LENGTH_SHORT).show();
        }
    }

    @Override  //overriding on click method of OnClickListener interface.
    public void onClick(View v) {

        resetButtonColors();
        switch (v.getId()) {
            case R.id.true_btn:
            case R.id.false_btn:
                Question question = getCurrentQuestion();
                if (question.isAnswerTrue()) {
                    falseButton.setBackgroundResource(R.color.red);
                    trueButton.setBackgroundResource(R.color.green);
                } else {
                    falseButton.setBackgroundResource(R.color.green);
                    trueButton.setBackgroundResource(R.color.red);
                }
                break;
            case R.id.prev_btn:
                currentIndex--;
                randomQuestion();
                break;
            case R.id.next_btn:
                currentIndex++;
                randomQuestion();
                break;
            case R.id.cheat_button:
                Toast.makeText(this,"Showing you the answer", Toast.LENGTH_LONG).show();
                Question currentQuestion = questionBank[currentIndex];
                Boolean currentAnswer = currentQuestion.isAnswerTrue();
                String questionStr= getResources().getString(currentQuestion.getTextResId());
                Intent intent = new Intent (this, CheatActivity.class);
                intent.putExtra("CURRENT QUESTION", questionStr );
                intent.putExtra("CURRENT ANSWER", currentAnswer);
                intent.putExtra("CURRENT INDEX", currentIndex);
                startActivityForResult(intent, CHEAT_REQUEST);
                break;
        }
    }
}
