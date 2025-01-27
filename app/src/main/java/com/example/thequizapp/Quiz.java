package com.example.thequizapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Quiz extends AppCompatActivity {
    private int totalAnswers = 0;
    private int correctAnswers = 0;
    private Button finishButton, nextButton, answerButton1, answerButton2, answerButton3;
    private TextView allAnswersTextView, correctAnswersTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_quiz);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        finishButton = findViewById(R.id.quiz_finish);
        nextButton = findViewById(R.id.quiz_next);
        answerButton1 = findViewById(R.id.quiz_button_answer1);
        answerButton2 = findViewById(R.id.quiz_button_answer2);
        answerButton3 = findViewById(R.id.quiz_button_answer3);

        allAnswersTextView = findViewById(R.id.quiz_all_answers);
        correctAnswersTextView = findViewById(R.id.quiz_correct_answers);

        finishButton.setOnClickListener(v -> {
            Log.d("Quiz", "Finish button clicked");
            Toast.makeText(Quiz.this, "Finished with score" + correctAnswers + "/" + totalAnswers + "!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Quiz.this, Gallery.class);
            startActivity(intent);
        });

        nextButton.setOnClickListener(v -> {
            //TODO: add changing picture and answers
            //TODO: add other answers emoticons
            //resetting quiz
            int purple = getResources().getColor(R.color.purple_500, getTheme());

            answerButton1.setBackgroundColor(purple);
            answerButton2.setBackgroundColor(purple);
            answerButton3.setBackgroundColor(purple);

            answerButton1.setEnabled(true);
            answerButton2.setEnabled(true);
            answerButton3.setEnabled(true);
        });
    }

    private boolean isCorrectAnswer(String answer) {
        String realCorrectAnswer = "Answer1";
        //TODO: need to change it to get correct answer from picture - name pair
        return realCorrectAnswer.equals(answer);
    }

    public void answerClick(View v) {
        Button clickedButton = (Button) v;
        String answer = clickedButton.getText().toString();
        totalAnswers++;
        allAnswersTextView.setText(String.valueOf(totalAnswers));

        if (isCorrectAnswer(answer)) {
            clickedButton.setBackgroundColor(Color.GREEN);
            correctAnswers++;
            correctAnswersTextView.setText(String.valueOf(correctAnswers));

        } else {
            clickedButton.setBackgroundColor(Color.RED);
        }

        answerButton1.setEnabled(false);
        answerButton2.setEnabled(false);
        answerButton3.setEnabled(false);
    }
}