package com.example.thequizapp;

import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.SmallTest;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;




import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class QuizButtonTest {

    @Rule
    public ActivityScenarioRule<Gallery> activityRule = new ActivityScenarioRule<> (Gallery.class);

    @Before
    public void setUp() throws Exception {
        //looking over intents to check if the right intents are being used
        Intents.init();
    }

    @Test
    public void testQuizButton() {

        //Click button
        onView(withId(R.id.quizButton)).perform(click());

        //checks if activity starts
        intended(hasComponent(Quiz.class.getName()));
    }
}
