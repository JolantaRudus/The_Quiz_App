package com.example.thequizapp;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Quiz extends AppCompatActivity {
    private int totalAnswers = 0;
    private int correctAnswers = 0;
    private Button answerButton1, answerButton2, answerButton3;
    private TextView allAnswersTextView, correctAnswersTextView;
    private ImageView image;
    private ImageView[] checkIcons, crossIcons;
    private ImageItem correctAnswer;

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
        // Initialize UI elements
        setupUI();

        // Button listeners
        setupListeners();

        //Load the first question for quiz
        setupNewQuestion();
    }
    private void setupUI() {
        answerButton1 = findViewById(R.id.quiz_button_answer1);
        answerButton2 = findViewById(R.id.quiz_button_answer2);
        answerButton3 = findViewById(R.id.quiz_button_answer3);

        image = findViewById(R.id.quiz_picture);
        checkIcons = new ImageView[]{
                findViewById(R.id.quiz_check1),
                findViewById(R.id.quiz_check2),
                findViewById(R.id.quiz_check3)
        };
        crossIcons = new ImageView[]{
                findViewById(R.id.quiz_cross1),
                findViewById(R.id.quiz_cross2),
                findViewById(R.id.quiz_cross3)
        };

        allAnswersTextView = findViewById(R.id.quiz_all_answers);
        correctAnswersTextView = findViewById(R.id.quiz_correct_answers);
    }
    private void setupListeners() {
        // Set up listeners for finish and next question buttons
        findViewById(R.id.quiz_finish).setOnClickListener(v -> finishQuiz());
        findViewById(R.id.quiz_next).setOnClickListener(v -> setupNewQuestion());
    }
    private void finishQuiz() {
        Toast.makeText(Quiz.this, "Finished with score " + correctAnswers + "/" + totalAnswers + "!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(Quiz.this, Gallery.class);
        startActivity(intent);
    }
    private void setupNewQuestion() {
        // Ensure there are enough images to create a quiz question
        if (GalleryImageCollection.imageList == null) {
            Toast.makeText(this, "Not enough images for the quiz!", Toast.LENGTH_SHORT).show();
            return;
        }
        // Reset UI for new question
        resetQuizAnswerButtonsUI();
        // Select random 3 animals for the question
        List<ImageItem> selectedAnimals = getRandomQuizAnimals(GalleryImageCollection.imageList);
        correctAnswer = selectedAnimals.get(0);

        // Load the image from URI or drawable resource
        String imageUri = correctAnswer.getImageUri();
        if (imageUri != null && !imageUri.isEmpty()) {
            image.setImageURI(Uri.parse(imageUri));
        }
        else {
            image.setImageResource(correctAnswer.getImageDrawable());
        }

        // Assign answer choices randomly
        Collections.shuffle(selectedAnimals);
        answerButton1.setText(selectedAnimals.get(0).getTitle());
        answerButton2.setText(selectedAnimals.get(1).getTitle());
        answerButton3.setText(selectedAnimals.get(2).getTitle());
    }
    private void resetQuizAnswerButtonsUI() {
        // Reset answer button colors and enable them
        int defaultColor = getResources().getColor(R.color.purple_500, getTheme());
        Button[] buttons = {answerButton1, answerButton2, answerButton3};
        for (Button button : buttons) {
            button.setBackgroundColor(defaultColor);
            button.setEnabled(true);
        }

        // Hide all check and cross icons
        for (ImageView icon : checkIcons) icon.setVisibility(View.INVISIBLE);
        for (ImageView icon : crossIcons) icon.setVisibility(View.INVISIBLE);
    }

    private List<ImageItem> getRandomQuizAnimals(List<ImageItem> imageNameList) {
        // Pick the first 3 images for quiz options from a shuffled list
        List<ImageItem> animals = new ArrayList<>(imageNameList);
        Collections.shuffle(animals);
        return animals.subList(0, 3);
    }

    public void answerClick(View v) {
        Button clickedButton = (Button) v;
        String correctTitle = correctAnswer.getTitle();
        Button[] buttons = {answerButton1, answerButton2, answerButton3};

        // Increase total answer count and update total answer count UI
        totalAnswers++;
        allAnswersTextView.setText(String.valueOf(totalAnswers));

        // Check if selected answer is correct
        boolean isCorrect = clickedButton.getText().equals(correctTitle);
        clickedButton.setBackgroundColor(isCorrect ? Color.GREEN : Color.RED);

        if (isCorrect) {
            // Increase correct answer count and update correct answer count UI
            correctAnswers++;
            correctAnswersTextView.setText(String.valueOf(correctAnswers));
        } else {
            // Mark correct and incorrect answers visually with checks and crosses
            for (int i = 0; i < buttons.length; i++) {
                if (buttons[i].getText().toString().equals(correctTitle)) {
                    checkIcons[i].setVisibility(View.VISIBLE);
                    buttons[i].setEnabled(false);
                } else {
                    crossIcons[i].setVisibility(View.VISIBLE);
                    buttons[i].setEnabled(false);
                }
            }
        }

        // Disable all answer buttons and make them gray
        int disabledGray = getResources().getColor(R.color.gray, getTheme());
        for (Button button : buttons) {
            button.setEnabled(false);
            if (button != clickedButton) {
                button.setBackgroundColor(disabledGray);
            }
        }
    }
}