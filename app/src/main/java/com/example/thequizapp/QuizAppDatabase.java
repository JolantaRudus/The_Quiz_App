package com.example.thequizapp;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {QuizAppEntity.class}, version = 1, exportSchema = false)
public abstract class QuizAppDatabase extends RoomDatabase {

    private static volatile QuizAppDatabase INSTANCE;

    public abstract QuizAppDAO quizAppDAO(); // Implementasjon av DAO-interfacet

    public static synchronized QuizAppDatabase getInstance(Context context) { // Singleton pattern to avoid creating multiple instances of the database
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    QuizAppDatabase.class, "quiz_database")
                    .fallbackToDestructiveMigration() // Handles migration issues, database changes automatic
                    .build();
        }
        return INSTANCE;
    }
}
