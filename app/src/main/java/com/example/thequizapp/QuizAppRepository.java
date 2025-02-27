package com.example.thequizapp;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import java.util.List;

public class QuizAppRepository {

    private QuizAppDAO quizAppDAO;
    private LiveData<List<QuizAppEntity>> allImages;

    // Constructor that takes a QuizAppDAO as a parameter
    public QuizAppRepository(Application application) {

        //Get access til the database through the database class
        QuizAppDatabase database = QuizAppDatabase.getInstance(application);
        quizAppDAO = database.quizAppDAO();
        allImages = quizAppDAO.getAll(); // Get all data from the database
    }

    // Method to get all images
    public LiveData<List<QuizAppEntity>> getAllImages() {
        return allImages; //LiveData that is observed in the ViewModel
    }

    // Method to insert an image to the database
    public void insert(QuizAppEntity image) {
        Log.d("Database", "Inserting image: " + image.getTitle()); // Just to see what is being inserted
        quizAppDAO.insert(image);
    }

    // Method for updating an image
    public void update(QuizAppEntity image) {
        quizAppDAO.update(image);
    }

    // Method for deleting an image
    public void delete(QuizAppEntity image) {
        Log.d("Database", "Deleting image with ID: " + image.getId()); // Just to see what is being deleted
        quizAppDAO.delete(image);
    }

    // Method to get an image by its ID
    public LiveData<QuizAppEntity> getImageById(int id) {
        return quizAppDAO.getById(id);
    }
}