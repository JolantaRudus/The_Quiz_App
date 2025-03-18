package com.example.thequizapp;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.allOf;
import static org.junit.Assert.assertEquals;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import org.hamcrest.Matcher;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.intent.matcher.IntentMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class QuizGalleryTest {

    @Rule
    public ActivityScenarioRule<Gallery> activityRule = new ActivityScenarioRule<>(Gallery.class);

    @Before
    public void setUp() {
        Intents.init();
    }

    @After
    public void tearDown() {
        Intents.release();
    }

    @Test
    public void testAddPicture() {
        // Tell RecyclerView to count items before adding
        int initialCount = getRecyclerViewItemCount(R.id.galleryRecyclerView);

        // Stub intent to simulate selecting an image
        Intent resultData = new Intent();
        Uri imageUri = Uri.parse("android.resource://com.example.thequizapp/drawable/Shark");
        resultData.setData(imageUri);
        Instrumentation.ActivityResult result =
                new Instrumentation.ActivityResult(Activity.RESULT_OK, resultData);

        Intents.intending(IntentMatchers.hasAction(Intent.ACTION_PICK)).respondWith(result);

        // Click "New Picture"
        onView(withId(R.id.addImageButton)).perform(click());

        // Add title for animal
        onView(withId(R.id.inputText)).perform(typeText("Test Animal"));
        closeSoftKeyboard();

        // Click on the button to add picture from the gallery
        onView(withId(R.id.addPhoto)).perform(click());

        // Click "Add" to add to gallery
        onView(withId(R.id.addButton)).perform(click());

        // Check that a new image is added
        //Thread.sleep(1000);
        int newCount = getRecyclerViewItemCount(R.id.galleryRecyclerView);
        assertEquals("Image count should increase by 1", initialCount + 1, newCount);
    }

    private int getRecyclerViewItemCount(int recyclerViewId) {
        final int[] count = {0};
        activityRule.getScenario().onActivity(activity -> {
            RecyclerView recyclerView = activity.findViewById(recyclerViewId);
            count[0] = recyclerView.getAdapter().getItemCount();
        });
        return count[0];
    }

    @Test
    public void testDeletePicture() throws InterruptedException {
        int initialCount = getRecyclerViewItemCount(R.id.galleryRecyclerView);

        // Click the delete button inside the third item (index 2)
        onView(withId(R.id.galleryRecyclerView))
                .perform(actionOnItemAtPosition(2, clickChildViewWithId(R.id.deleteButton)));

        // Confirm the deletion in the alert dialog
        onView(withText("Delete")).perform(click());

        // Wait for the database update (replace with IdlingResource if possible)
        Thread.sleep(1000);

        // Check that the count has decreased
        int finalCount = getRecyclerViewItemCount(R.id.galleryRecyclerView);
        assertEquals("Image count should decrease by 1", initialCount - 1, finalCount);
    }


        public static ViewAction clickChildViewWithId(final int id) {
            return new ViewAction() {
                @Override
                public Matcher<View> getConstraints() {
                    return allOf(); // No constraints, apply to any view
                }

                @Override
                public String getDescription() {
                    return "Click on a child view with specified ID.";
                }

                @Override
                public void perform(UiController uiController, View view) {
                    View childView = view.findViewById(id);
                    if (childView != null) {
                        childView.performClick();
                    }
                }
            };
        }
    }