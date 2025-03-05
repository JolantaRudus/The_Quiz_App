package com.example.thequizapp;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class QuizAppRepository {

    private static QuizAppDAO quizAppDAO;
    private LiveData<List<QuizAppEntity>> allImages;
    private Executor excutorService;

    // Constructor that takes a QuizAppDAO as a parameter
    public QuizAppRepository(Application application) {

        //Get access til the database through the database class
        QuizAppDatabase database = QuizAppDatabase.getInstance(application);
        quizAppDAO = database.quizAppDAO();
        allImages = quizAppDAO.getAll(); // Get all data from the database
        excutorService = Executors.newSingleThreadExecutor();
    }

    // Method to get all images
    public LiveData<List<QuizAppEntity>> getAllImages() {
        return allImages; //LiveData that is observed in the ViewModel
    }

    // Method to insert an imageItem to the database
    public void insert(QuizAppEntity imageItem) {
        Log.d("Database", "Inserting imageItem: " + imageItem.getTitle()); // Just to see what is being inserted
        QuizAppEntity newImage = new QuizAppEntity(imageItem.getImageUri(), imageItem.getTitle());
        excutorService.execute(() -> quizAppDAO.insert(newImage));
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

    public void populateDatabase(Context context) {
        excutorService.execute(() -> {
            if (quizAppDAO.getAll().getValue() == null || quizAppDAO.getAll().getValue().isEmpty()) {
                for (QuizAppEntity item : GalleryImageCollection.imageList) {
                    String imageUri = "android.resource://" + context.getPackageName() + "/" + item.getImageDrawable();
                    QuizAppEntity imageEntity = new QuizAppEntity(imageUri, item.getTitle());
                    quizAppDAO.insert(imageEntity);
                }
            }
        });
    }
}