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

--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

# Documentation for Quiz Gallery Test

## Purpose:
This test is created to verify the functionality of adding and deleting pictures in the `Gallery` activity. The test will check the following actions:
1. Add a new picture and ensure that the total count of images in the gallery increases by one.
2. Delete an image from the gallery and ensure that the total count of images in the gallery decreases by one.

## Expected Behavior:
- **Adding a picture**: When a user adds a new picture, the total number of images in the gallery should increase by one.
- **Deleting a picture**: When a user deletes an image, the total number of images in the gallery should decrease by one.

## Steps in the Test:

### Test 1: Add a New Picture
1. Launch the `Gallery` activity.
2. Check the initial number of images in the gallery.
3. Simulate the process of adding a new picture by:
    - Selecting a picture from the resources folder.
    - Entering a title for the image.
    - Saving the image to the gallery.
4. Verify that the total count of images in the gallery has increased by one.

### Test 2: Delete a Picture
1. Launch the `Gallery` activity.
2. Check the initial number of images in the gallery.
3. Select the third image in the gallery and click the delete button.
4. Verify that the total count of images in the gallery has decreased by one.

## Expected Results:
- **Test 1 (Add Picture)**: After adding a new picture, the total number of images in the gallery should increase by one.
- **Test 2 (Delete Picture)**: After deleting an image, the total number of images in the gallery should decrease by one.