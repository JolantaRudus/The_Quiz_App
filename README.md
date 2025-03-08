# Documentation for Button Click Test

## Purpose:
This test is created to verify that the "Quiz" button in the `Gallery` activity navigates to the `Quiz` activity. It also ensures that the correct intent is triggered to start the `Quiz` activity when the button is clicked.

## Expected Behavior:
When the "Quiz" button in the `Gallery` activity is clicked, the app should transition to the `Quiz` activity. The test will check that the correct intent is fired to launch the `Quiz` activity.

## Steps in the Test:
1. Launch the `Gallery` activity (main menu).
2. Simulate a click on the "Quiz" button.
3. Verify that the `Quiz` activity is launched by checking the fired intent.

## Test Result:
- **Pass**: This test passed (green) when run, confirming that the "Quiz" button correctly navigates to the `Quiz` activity.
- **Fail**: If this test were to fail, it would indicate that the app did not navigate to the `Quiz` activity as expected.

--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

# Documentation for Quiz Score Test

## Purpose:
This test is created to verify that the score in the quiz is updated correctly when answering questions. There are two tests in this case:
1. When the user selects the correct answer, the "correct answers" and "total answers" scores should both increase by one.
2. When the user selects the wrong answer, the "correct answers" score should **not** increase, while the "total answers" score should still increase by one.

## Expected Behavior:
- **For the correct answer**: When the user clicks the correct answer, the "correct answers" and "total answers" should both increase by one.
- **For the wrong answer**: When the user clicks the wrong answer, only the "total answers" should increase, but the "correct answers" should not.

## Steps in the Test:

### Test 1: Correct Answer Increases Correct Answers
1. Launch the `Quiz` activity.
2. Retrieve the correct answer and initial scores (correct and total answers).
3. Simulate a click on the correct answer button.
4. Verify that the "correct answers" score has increased by one.
5. Verify that the "total answers" score has increased by one.

### Test 2: Wrong Answer Does Not Increase Correct Answers
1. Launch the `Quiz` activity.
2. Retrieve the initial scores (correct and total answers).
3. Retrieve a wrong answer option.
4. Simulate a click on a wrong answer button.
5. Verify that the "correct answers" score has not increased.
6. Verify that the "total answers" score has increased by one.

## Expected Results:
- **Test 1 (Correct Answer)**: After selecting the correct answer, the "correct answers" and "total answers" should both increase by one.
- **Test 2 (Wrong Answer)**: After selecting the wrong answer, only the "total answers" should increase by one, and the "correct answers" should remain unchanged.
