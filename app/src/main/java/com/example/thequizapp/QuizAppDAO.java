package com.example.thequizapp;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.thequizapp.QuizAppEntity;

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
    LiveData<List<QuizAppEntity>> getAll();

    @Query("SELECT * FROM gallery WHERE id = :id")
    LiveData<QuizAppEntity> getById(int id);
}
