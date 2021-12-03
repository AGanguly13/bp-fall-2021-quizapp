package com.example.bp_fall_2021_quizapp;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.AppCompatEditText;

public class MainActivity extends AppCompatActivity {

    // Put class variables up here
    // Best practice is to make them private (can only be accessed within the class, or using getters/setters)
    // Each UI component that you want to reference needs a variable

    private EditText nameInput;

    /**
     * Method used to start an activity. It's the first method to run when the
     * activity begins
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameInput = findViewById(R.id.nameInput);

        // obtain user's name using findViewById

    }

    /**
     * Get username here and open Quiz Questions
     *
     * @param view
     */
    public void startQuiz(View view) {
        // set name variable every time user clicks "start"
        String name = nameInput.getText().toString();
        // If the name field is empty, prompt user to enter name
        if (name.isEmpty()) {
            Toast.makeText(getBaseContext(), "Please enter name", Toast.LENGTH_SHORT).show();
        } else {
            // If user has entered name, begin quiz
            Intent intent = new Intent(this, QuizQuestionActivity.class);
            intent.putExtra("Username", name);
            startActivity(intent);
            finish(); // close current activity
        }

    }
}
