package com.example.thequizapp;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuizViewModel extends AndroidViewModel {
        private QuizAppRepository repository;
        private int totalAnswers = 0;
        private int correctAnswers = 0;
        private MutableLiveData<QuizAppEntity> correctAnswer = new MutableLiveData<>();
        private MutableLiveData<List<QuizAppEntity>> answerOptions = new MutableLiveData<>();
        private MutableLiveData<String> selectedAnswer = new MutableLiveData<>();
        private MutableLiveData<Integer> totalAnswersLiveData = new MutableLiveData<>();
        private MutableLiveData<Integer> correctAnswersLiveData = new MutableLiveData<>();

        public QuizViewModel(Application application) {
            super(application);
            repository = new QuizAppRepository(application);
            totalAnswersLiveData.setValue(totalAnswers);
            correctAnswersLiveData.setValue(correctAnswers);
        }

    public void setupNewQuestion() {
        correctAnswer.setValue(null);
        selectedAnswer.setValue(null);

        // Observe all images from the database repository
        repository.getAllImages().observeForever(new Observer<List<QuizAppEntity>>() {
            @Override
            public void onChanged(List<QuizAppEntity> allImages) {
                if (allImages != null && allImages.size() >= 3) {
                    List<QuizAppEntity> selectedImages = getRandomQuizAnimals(allImages);
                    correctAnswer.setValue(selectedImages.get(0));
                    Collections.shuffle(selectedImages);
                    answerOptions.setValue(selectedImages);
                }
            }
        });
    }

        private List<QuizAppEntity> getRandomQuizAnimals(List<QuizAppEntity> imageNameList) {
            List<QuizAppEntity> animals = new ArrayList<>(imageNameList);
            Collections.shuffle(animals);
            return animals.subList(0, 3);
        }

        public void answerClick(String answer) {
            totalAnswers++;
            totalAnswersLiveData.setValue(totalAnswers);
            selectedAnswer.setValue(answer);

            if (answer.equals(correctAnswer.getValue().getTitle())) {
                correctAnswers++;
                correctAnswersLiveData.setValue(correctAnswers);
            }
        }

        public MutableLiveData<QuizAppEntity> getCorrectAnswer() {
            return correctAnswer;
        }

        public MutableLiveData<List<QuizAppEntity>> getAnswerOptions() {
            return answerOptions;
        }

        public MutableLiveData<Integer> getTotalAnswersLiveData() {
            return totalAnswersLiveData;
        }

        public MutableLiveData<Integer> getCorrectAnswersLiveData() {
            return correctAnswersLiveData;
        }

        public MutableLiveData<String> getSelectedAnswer() {
            return selectedAnswer;
        }
    }
