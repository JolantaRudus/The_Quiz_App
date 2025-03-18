package com.example.thequizapp;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.lifecycle.ViewModelProvider;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

@RunWith(AndroidJUnit4.class)
public class QuizScoreTest {

    @Rule
    public ActivityScenarioRule<Quiz> activityScenarioRule =
            new ActivityScenarioRule<>(Quiz.class);

    @Test
    public void testCorrectAnswerIncreasesCorrectAnswers() {
        final String[] correctAnswer = new String[1];
        final int[] initialCorrectAnswers = new int[1];
        final int[] initialTotalAnswers = new int[1];

        activityScenarioRule.getScenario().onActivity(activity -> {
            QuizViewModel viewModel = new ViewModelProvider(activity).get(QuizViewModel.class);

            // Get correct answer and initial scores from ViewModel
            if (viewModel.getCorrectAnswer().getValue() != null) {
                correctAnswer[0] = viewModel.getCorrectAnswer().getValue().getTitle();
            }

            initialCorrectAnswers[0] = viewModel.getCorrectAnswersLiveData().getValue();
            initialTotalAnswers[0] = viewModel.getTotalAnswersLiveData().getValue();
        });

        // Make sure there is a correct answer to click on
        if (correctAnswer[0] != null) {
            // Find the button with the correct answer and click on it
            onView(withText(correctAnswer[0])).perform(click());

            // Check that "correct answers" has increased by 1
            onView(withId(R.id.quizCorrectAnswers)).check(matches(withText(String.valueOf(initialCorrectAnswers[0] + 1))));

            // Check that "total answers" has increased by 1
            onView(withId(R.id.quizAllAnswers)).check(matches(withText(String.valueOf(initialTotalAnswers[0] + 1))));
        }
    }

    @Test
    public void testWrongAnswerDoesNotIncreaseCorrectAnswers() {
        final String[] wrongAnswer = new String[1];
        final int[] initialCorrectAnswers = new int[1];
        final int[] initialTotalAnswers = new int[1];

        activityScenarioRule.getScenario().onActivity(activity -> {
            QuizViewModel viewModel = new ViewModelProvider(activity).get(QuizViewModel.class);

            // Get the answer options from the ViewModel, both correct and wrong answers
            List<QuizAppEntity> answerOptions = viewModel.getAnswerOptions().getValue();
            if (answerOptions != null && answerOptions.size() >= 2) {
                // Pick a wrong answer, one of the other options except the correct answer
                for (QuizAppEntity answer : answerOptions) {
                    if (!answer.getTitle().equals(viewModel.getCorrectAnswer().getValue().getTitle())) {
                        wrongAnswer[0] = answer.getTitle();
                        break;
                    }
                }
            }

            initialCorrectAnswers[0] = viewModel.getCorrectAnswersLiveData().getValue();
            initialTotalAnswers[0] = viewModel.getTotalAnswersLiveData().getValue();
        });

        // Make sure there is a wrong answer to click on
        if (wrongAnswer[0] != null) {
            // Find the button with the wrong answer and click on it
            onView(withText(wrongAnswer[0])).perform(click());

            // Check that "correct answers" has not increased, this should be unchanged
            onView(withId(R.id.quizCorrectAnswers)).check(matches(withText(String.valueOf(initialCorrectAnswers[0]))));

            // Check that "total answers" has increased by 1
            onView(withId(R.id.quizAllAnswers)).check(matches(withText(String.valueOf(initialTotalAnswers[0] + 1))));
        }
    }
}