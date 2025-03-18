package com.example.thequizapp;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class Quiz extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        // Load the QuizFragment when the activity starts
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainer, new QuizFragment())
                    .commit();
        }
    }
}
