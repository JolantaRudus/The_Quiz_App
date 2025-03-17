package com.example.thequizapp;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;


//Fragment for displaying Quiz UI and handling user interactions.
//It observes the QuizViewModel for updates to quiz data.
public class QuizFragment extends Fragment {
        private QuizViewModel quizViewModel;
        private Button answerButton1, answerButton2, answerButton3;
        private TextView allAnswersTextView, correctAnswersTextView;
        private ImageView image;
        private ImageView[] checkIcons, crossIcons;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_quiz, container, false);
            quizViewModel = new ViewModelProvider(this).get(QuizViewModel.class);
            setupUI(view);
            setupObservers();
            // If no question is currently set, generate a new one
            if (quizViewModel.getCorrectAnswer().getValue() == null) {
                quizViewModel.setupNewQuestion();
            }
            return view;
        }

        //Initializes UI components and sets click listeners.
        private void setupUI(View view) {
            answerButton1 = view.findViewById(R.id.quiz_button_answer1);
            answerButton2 = view.findViewById(R.id.quiz_button_answer2);
            answerButton3 = view.findViewById(R.id.quiz_button_answer3);
            image = view.findViewById(R.id.quiz_picture);
            checkIcons = new ImageView[]{
                    view.findViewById(R.id.quiz_check1),
                    view.findViewById(R.id.quiz_check2),
                    view.findViewById(R.id.quiz_check3)
            };
            crossIcons = new ImageView[]{
                    view.findViewById(R.id.quiz_cross1),
                    view.findViewById(R.id.quiz_cross2),
                    view.findViewById(R.id.quiz_cross3)
            };
            allAnswersTextView = view.findViewById(R.id.quiz_all_answers);
            correctAnswersTextView = view.findViewById(R.id.quiz_correct_answers);

            // Set up answer button click listeners
            answerButton1.setOnClickListener(this::answerClick);
            answerButton2.setOnClickListener(this::answerClick);
            answerButton3.setOnClickListener(this::answerClick);

            // Set up navigation buttons
            view.findViewById(R.id.quiz_finish).setOnClickListener(v -> finishQuiz());
            view.findViewById(R.id.quiz_next).setOnClickListener(v -> quizViewModel.setupNewQuestion());
        }

        //Observes LiveData from ViewModel and updates UI
        private void setupObservers() {
            quizViewModel.getCorrectAnswer().observe(getViewLifecycleOwner(), correctAnswer -> {
                if (correctAnswer != null) {
                    String imageUri = correctAnswer.getImageUri();
                    if (imageUri != null && !imageUri.isEmpty()) {
                        image.setImageURI(Uri.parse(imageUri));
                    } else {
                        image.setImageResource(correctAnswer.getImageDrawable());
                    }
                }
            });

            quizViewModel.getAnswerOptions().observe(getViewLifecycleOwner(), options -> {
                if (options != null && options.size() == 3) {
                    answerButton1.setText(options.get(0).getTitle());
                    answerButton2.setText(options.get(1).getTitle());
                    answerButton3.setText(options.get(2).getTitle());
                    resetQuizAnswerButtonsUI();
                }
            });

            quizViewModel.getTotalAnswersLiveData().observe(getViewLifecycleOwner(), totalAnswers ->
                    allAnswersTextView.setText(String.valueOf(totalAnswers))
            );

            quizViewModel.getCorrectAnswersLiveData().observe(getViewLifecycleOwner(), correctAnswers ->
                    correctAnswersTextView.setText(String.valueOf(correctAnswers))
            );

            quizViewModel.getSelectedAnswer().observe(getViewLifecycleOwner(), selectedAnswer -> {
                if (selectedAnswer != null) {
                    updateUIAfterAnswer(selectedAnswer);
                }
            });
        }

        //Handles user answer selection
        private void answerClick(View v) {
            Button clickedButton = (Button) v;
            String selectedAnswer = clickedButton.getText().toString();
            quizViewModel.answerClick(selectedAnswer);
            updateUIAfterAnswer(selectedAnswer);
        }

        //Updates UI after an answer is selected
        private void updateUIAfterAnswer(String selectedAnswer) {
            Button[] buttons = {answerButton1, answerButton2, answerButton3};
            String correctTitle = quizViewModel.getCorrectAnswer().getValue().getTitle();
            int disabledColor = getResources().getColor(R.color.gray, getContext().getTheme());

            boolean isCorrect = selectedAnswer.equals(correctTitle);

            for (int i = 0; i < buttons.length; i++) {
                buttons[i].setBackgroundColor(disabledColor);
                if (buttons[i].getText().toString().equals(selectedAnswer)) {
                    buttons[i].setBackgroundColor(isCorrect ? Color.GREEN : Color.RED);
                }
                if (buttons[i].getText().toString().equals(correctTitle)) {
                    checkIcons[i].setVisibility(View.VISIBLE);
                    buttons[i].setEnabled(false);
                } else {
                    crossIcons[i].setVisibility(View.VISIBLE);
                    buttons[i].setEnabled(false);
                }
            }
        }

        //Resets UI for new question
        private void resetQuizAnswerButtonsUI() {
            int defaultColor = getResources().getColor(R.color.purple_500, getContext().getTheme());
            Button[] buttons = {answerButton1, answerButton2, answerButton3};
            for (Button button : buttons) {
                button.setBackgroundColor(defaultColor);
                button.setEnabled(true);
            }
            for (ImageView icon : checkIcons) icon.setVisibility(View.INVISIBLE);
            for (ImageView icon : crossIcons) icon.setVisibility(View.INVISIBLE);
        }

        private void finishQuiz() {
            Toast.makeText(getContext(), "Finished with score " +
                    quizViewModel.getCorrectAnswersLiveData().getValue() + "/" +
                    quizViewModel.getTotalAnswersLiveData().getValue() + "!", Toast.LENGTH_SHORT).show();
            requireActivity().finish();
        }
    }
