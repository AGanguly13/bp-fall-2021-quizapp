package com.example.bp_fall_2021_quizapp;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class ResultActivity extends AppCompatActivity {

    // UI component variables
    private TextView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // initialize UI components
        view = findViewById(R.id.View);


        // set username and score
        Bundle bundle = getIntent().getExtras();
        String name = bundle.getString("username");
        int score = bundle.getInt("score");
        int totalQuestions = bundle.getInt("totalQuestions");
        view.setText(name);
        view.setText("Your score is " + score + " out of " + totalQuestions);
    }

    /**
     * Restarts the quiz so you can play another round
     * @param view
     */
    public void restart(View view) {
        Intent intent = new Intent(ResultActivity.this, MainActivity.class);
        startActivity(intent);
    }
}