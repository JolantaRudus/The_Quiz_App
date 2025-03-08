package com.example.thequizapp;


import android.app.Application;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;



public class QuizGalleryTest {

    @Rule
    public ActivityScenarioRule<Gallery> activityScenarioRule =
            new ActivityScenarioRule<>(Gallery.class);

    private QuizAppDatabase database;
    private QuizAppRepository repository;

    @Before
    public void setUp() {
        Application context = (Application) InstrumentationRegistry.getInstrumentation().getTargetContext().getApplicationContext();
        database = QuizAppDatabase.getInstance(context);
        repository = new QuizAppRepository(context);
    }

    @Test
    public void testNumberOfImagesInGalleryAfterAdding() {

    }

    @Test
    public void testNumberOfImagesInGalleryAfterDeleting() {

    }
}