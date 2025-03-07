package com.example.thequizapp;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.widget.Button;

import androidx.lifecycle.ViewModelProvider;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.thequizapp.Quiz;
import com.example.thequizapp.QuizViewModel;
import com.example.thequizapp.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class QuizScoreTest {

    @Rule
    public ActivityScenarioRule<Quiz> activityScenarioRule =
            new ActivityScenarioRule<>(Quiz.class);

    @Test
    public void testCorrectAnswerIncreasesScore() {
        final String[] correctAnswer = new String[1];
        final int[] initialCorrectAnswers = new int[1];
        final int[] initialTotalAnswers = new int[1];

        activityScenarioRule.getScenario().onActivity(activity -> {
            QuizViewModel viewModel = new ViewModelProvider(activity).get(QuizViewModel.class);

            // Henter riktig svar fra ViewModel
            if (viewModel.getCorrectAnswer().getValue() != null) {
                correctAnswer[0] = viewModel.getCorrectAnswer().getValue().getTitle();
            }

            // Henter initial poengsum
            initialCorrectAnswers[0] = Integer.parseInt(((Button) activity.findViewById(R.id.quiz_correct_answers)).getText().toString());
            initialTotalAnswers[0] = Integer.parseInt(((Button) activity.findViewById(R.id.quiz_all_answers)).getText().toString());
        });

        // Finn knappen som har riktig svar og klikk på den
        onView(withText(correctAnswer[0])).perform(click());

        // Sjekker at "correct answers" har økt med 1
        onView(withId(R.id.quiz_correct_answers)).check(matches(withText(String.valueOf(initialCorrectAnswers[0] + 1))));

        // Sjekker at "all answers" har økt med 1
        onView(withId(R.id.quiz_all_answers)).check(matches(withText(String.valueOf(initialTotalAnswers[0] + 1))));
    }
}
