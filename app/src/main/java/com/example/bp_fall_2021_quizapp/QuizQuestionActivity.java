package com.example.bp_fall_2021_quizapp;

import android.content.Intent;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class QuizQuestionActivity extends AppCompatActivity {

    // UI components here
    private TextView ex, tv_progress;
    private RadioGroup Group;
    private RadioButton choice1;
    private RadioButton choice2;
    private RadioButton choice3;
    private RadioButton choice4;
    private ProgressBar progressbar;

    // other variables here
    private ArrayList <QuestionModel> questions;
    private String name;
    private int questionNum;
    private int score;
    private int size;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_question);

        // create arraylist of questions
        questions = new ArrayList<QuestionModel>(5);
        // get username intent from main activity screen
        name = getIntent().getExtras().getString("Username");
        // initialize views using findViewByID
        progressbar = findViewById(R.id.progressbar);
        Group = findViewById(R.id.group);
        ex = findViewById(R.id.ex);
        choice1 = findViewById(R.id.choice1);
        choice2 = findViewById(R.id.choice2);
        choice3 = findViewById(R.id.choice3);
        choice4 = findViewById(R.id.choice4);
        // use helper method to add question content to arraylist
        addQuestions(questions);
        // get total number of questions
        size = questions.size();
        // set progress bar
        progressbar.setProgress(0);
        // use helper method to proceed to next question
        questionNum = 0;
        score = 0;

        showNextQuestion();
    }

    /**
     * Method that adds questions to our questions arraylist, using the Question Model constructor
     */
    private void addQuestions(ArrayList questions){
        // question 1
        QuestionModel question1 = new QuestionModel("Which NBA player has won the most championships and how many did he win?", "Bill Russell - 11",
                "Michael Jordan - 11", "Larry Bird - 13", "LeBron James - 12", 0);
        QuestionModel question2 = new QuestionModel("Which NBA player has scored the most points in a single game and how many points did he score?","Jerry West - 223",
                "Wilt Chamberlain - 118", "Lebron James - 93", "Magic Johnson - 45",1);
        QuestionModel question3 = new QuestionModel("Which NBA player has the lowest career free throw percentage?","Hakeem Olajuwon",
                "Shaquille O’Neal", "Ben Wallace", "Brian Scalabrine",2);
        QuestionModel question4 = new QuestionModel("How tall was the shortest NBA player to win a dunk contest?","5’6’’",
                "5’7’’", "5’9’’", "5’10’’",0);
        QuestionModel question5 = new QuestionModel("Which NBA player ran the most in the past season?","Stephen Curry",
                "Fred Van Vleet", "Zach Lavine", "R.J. Barret",1);

        questions.add(question1);
        // question 2
        questions.add(question2);
        // question 3
        questions.add(question3);
        // question 4
        questions.add(question4);
        // question 5
        questions.add(question5);
    }

    /**
     * Check the answer when user clicks submit and move on to next question
     */
    public void submitQuestion(View view){
        // if no options have been selected, prompt user to select an answer
        if (!choice1.isChecked() && !choice2.isChecked() && !choice3.isChecked() && !choice4.isChecked()){
            Toast.makeText(getBaseContext(), "Please select option", Toast.LENGTH_SHORT).show();
        }
        // use helper methods to check the answer and show the next question
        checkAnswer();
        showNextQuestion();
    }

    /**
     * Display next question. If finished, move onto results screen
     */
    private void showNextQuestion(){

        // clear previous button selections
        Group.clearCheck();
        // if you haven't gone through all the questions yet
        if (questionNum < size) {

            progressbar.setProgress(100/ size * questionNum);
            questionNum++;
            // set the question & text to the next question
            ex.setText(questions.get(questionNum - 1).getQuestion());
            choice1.setText(questions.get(questionNum - 1).getOpt1());
            choice2.setText(questions.get(questionNum - 1).getOpt2());
            choice3.setText(questions.get(questionNum - 1).getOpt3());
            choice4.setText(questions.get(questionNum - 1).getOpt4());
            // increase question number

            // set progress bar

        }

        else{
            Intent intent = new Intent(this, ResultActivity.class);
            intent.putExtra("Username",name);
            intent.putExtra("Score", score);
            startActivity(intent);
            finish();

        }
        // if finished with quiz, start Results activity

    }

    /**
     * Checks the answer and increases score if correct
     */
    private void checkAnswer(){
        // see which answer they picked
        int correctchoice = 0;
        switch(questions.get(questionNum - 1).getCorrectAnsNum()){
            case 0:
                correctchoice = choice1.getId();
                break;
            case 1:
                correctchoice = choice2.getId();
                break;
            case 2:
                correctchoice = choice3.getId();
                break;
            case 3:
                correctchoice = choice4.getId();
                break;
        }
        // increase score if correct
        if(Group.getCheckedRadioButtonId() == correctchoice){
            score++;
        }
    }
}