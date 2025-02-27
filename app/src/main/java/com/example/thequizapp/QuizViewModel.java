package com.example.thequizapp;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuizViewModel extends ViewModel {
        private int totalAnswers = 0;
        private int correctAnswers = 0;
        private MutableLiveData<ImageItem> correctAnswer = new MutableLiveData<>();
        private MutableLiveData<List<ImageItem>> answerOptions = new MutableLiveData<>();
        private MutableLiveData<String> selectedAnswer = new MutableLiveData<>();
        private MutableLiveData<Integer> totalAnswersLiveData = new MutableLiveData<>();
        private MutableLiveData<Integer> correctAnswersLiveData = new MutableLiveData<>();

        public QuizViewModel() {
            totalAnswersLiveData.setValue(totalAnswers);
            correctAnswersLiveData.setValue(correctAnswers);
        }

        public void setupNewQuestion() {
            correctAnswer.setValue(null);
            selectedAnswer.setValue(null);
            if (GalleryImageCollection.imageList == null || GalleryImageCollection.imageList.size() < 3) {
                return;
            }
            List<ImageItem> selectedAnimals = getRandomQuizAnimals(GalleryImageCollection.imageList);
            correctAnswer.setValue(selectedAnimals.get(0));
            Collections.shuffle(selectedAnimals);
            answerOptions.setValue(selectedAnimals);
        }

        private List<ImageItem> getRandomQuizAnimals(List<ImageItem> imageNameList) {
            List<ImageItem> animals = new ArrayList<>(imageNameList);
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

        public MutableLiveData<ImageItem> getCorrectAnswer() {
            return correctAnswer;
        }

        public MutableLiveData<List<ImageItem>> getAnswerOptions() {
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
