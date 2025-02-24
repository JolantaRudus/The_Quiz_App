package com.example.thequizapp;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class QuizAppViewModel extends AndroidViewModel {

    private QuizAppRepository repository;
    private LiveData<List<QuizAppEntity>> allImages;

    public QuizAppViewModel(Application application) {
        super(application);
        repository = new QuizAppRepository(application);
        allImages = repository.getAllImages(); // Get all images from the repository
    }

    public LiveData<List<QuizAppEntity>> getAllImages() {
        return allImages; // Make the images available to the UI
    }

    public void insert(QuizAppEntity image) {
        repository.insert(image); // Lets the repository handle the insertion
    }
}
