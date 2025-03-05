package com.example.thequizapp;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class QuizAppViewModel extends AndroidViewModel {

    private QuizAppRepository repository;
    private LiveData<List<QuizAppEntity>> allImages;

    public QuizAppViewModel(Application application) {
        super(application);
        repository = new QuizAppRepository(application);
        allImages = repository.getAllImages();
    }

    public LiveData<List<QuizAppEntity>> getAllImages() {
        return repository.getAllImages(); // Make the images available to the UI
    }

    public void insert(QuizAppEntity image) {
        repository.insert(image);
    }

    public void populateDatabase(Context context) {
        repository.populateDatabase(context);
    }
    public void deleteAll() {
        repository.deleteAll();
    }

    public void deleteImage(QuizAppEntity image) {
        repository.delete(image);
    }
}
