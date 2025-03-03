package com.example.thequizapp;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {QuizAppEntity.class}, version = 2, exportSchema = false)
public abstract class QuizAppDatabase extends RoomDatabase {

    private static volatile QuizAppDatabase INSTANCE;

    public abstract QuizAppDAO quizAppDAO(); // Implementasjon av DAO-interfacet
    public static synchronized QuizAppDatabase getInstance(Context context) { // Singleton pattern to avoid creating multiple instances of the database
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    QuizAppDatabase.class, "quiz_database")
                    .addMigrations(MIGRATION_1_2)
                    .fallbackToDestructiveMigration() // Handles migration issues, database changes automatic
                    .build();
        }
        return INSTANCE;
    }

    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE gallery ADD COLUMN imageDrawable INTEGER");
        }
    };
}
