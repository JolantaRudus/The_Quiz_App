package com.example.thequizapp;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface QuizAppDAO {
    @Insert
    void insert(QuizAppEntity quizAppEntity);

    @Update
    void update(QuizAppEntity quizAppEntity);

    @Delete
    void delete(QuizAppEntity quizAppEntity);

    @Query("SELECT * FROM gallery")
    LiveData<List<QuizAppEntity>> getAllImages();

    @Query("SELECT * FROM gallery WHERE id = :id")
    LiveData<QuizAppEntity> getById(int id);

    @Query("SELECT COUNT(*) FROM gallery")
    int getRowCount();
    //check if animal already exists
    @Query("SELECT COUNT(*) FROM gallery WHERE title = :animalName")
    int getAnimalCount(String animalName);

    @Query("DELETE FROM gallery")//use if need to empty the database
    void deleteAll();
}
