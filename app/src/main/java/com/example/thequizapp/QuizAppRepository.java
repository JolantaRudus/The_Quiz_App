package com.example.thequizapp;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class QuizAppRepository {

    private static QuizAppDAO quizAppDAO;
    private LiveData<List<QuizAppEntity>> allImages;
    private Executor excutorService;

    public QuizAppRepository(Application application) {

        //Get access to the database through the database class
        QuizAppDatabase database = QuizAppDatabase.getInstance(application);
        quizAppDAO = database.quizAppDAO();
        allImages = quizAppDAO.getAllImages(); // Get all data from the database
        excutorService = Executors.newSingleThreadExecutor();
    }

    public LiveData<List<QuizAppEntity>> getAllImages() {
        return quizAppDAO.getAllImages(); //LiveData that is observed in the ViewModel
    }

    public void insert(QuizAppEntity imageItem) {
        Log.d("Database", "Inserting imageItem: " + imageItem.getTitle()); // Just to see what is being inserted
        QuizAppEntity newImage = new QuizAppEntity(imageItem.getImageUri(), imageItem.getTitle());
        excutorService.execute(() -> quizAppDAO.insert(newImage));
    }

    public void update(QuizAppEntity image) {
        quizAppDAO.update(image);
    }

    public void delete(QuizAppEntity image) {
        Log.d("Database", "Deleting image with ID: " + image.getId()); // Just to see what is being deleted
        new Thread(() -> quizAppDAO.delete(image)).start();
    }

    public LiveData<QuizAppEntity> getImageById(int id) {
        return quizAppDAO.getById(id);
    }

    private int getImageCountFromDatabase() {
        return quizAppDAO.getRowCount();
    }

    public void populateDatabase(Context context) {
        excutorService.execute(() -> {
            if (this.getImageCountFromDatabase() == 0) { // Check if database is empty
                for (QuizAppEntity item : GalleryImageCollection.imageList) {
                    String imageUri = "android.resource://" + context.getPackageName() + "/" + item.getImageDrawable();
                    QuizAppEntity imageEntity = new QuizAppEntity(imageUri, item.getTitle());
                    quizAppDAO.insert(imageEntity);
                }
            }
        });
    }

    public void deleteAll() {
        excutorService.execute(() -> quizAppDAO.deleteAll());
    }

}