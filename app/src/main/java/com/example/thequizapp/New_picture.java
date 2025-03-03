package com.example.thequizapp;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class New_picture extends AppCompatActivity {

    private Button cancelButton, takePictureButton, addPhotoButton, addToGalleryButton;
    private ImageView selectedImageView;
    private EditText animalName;
    private Uri selectedImageUri;
    boolean animalExists = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_new_picture);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Buttons
        cancelButton = findViewById(R.id.pictureCancel);
        addPhotoButton = findViewById(R.id.addPhoto);
        takePictureButton = findViewById(R.id.takePicture);
        addToGalleryButton = findViewById(R.id.addButton);


        //Other elements
        animalName = findViewById(R.id.inputText);
        selectedImageView = findViewById(R.id.selectedImage);


        //Takes user back to start
        cancelButton.setOnClickListener(v -> {
            Log.d("New_picture", "Cancel button clicked");
            Intent intent = new Intent(New_picture.this, Gallery.class);
            startActivity(intent);
        });

        //Button functionality that gives user option to add a picture from the image gallery.
        addPhotoButton.setOnClickListener(v -> {
            Log.d("New_picture", "addPhoto button clicked");
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            pickImageLauncher.launch(intent);
        });

        //Button functionality that adds the picture to the gallery.
        addToGalleryButton.setOnClickListener(v -> {
            if (selectedImageUri != null && !animalName.getText().toString().trim().isEmpty()) {
                addToGallery(selectedImageUri.toString());
            } else {
                Log.d("New_picture", "No image or name provided!");
                Toast.makeText(New_picture.this, "No image or name provided!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Configures the ActivityResultLauncher to launch the image picker and handle the result.
    // When an image is selected, the callback receives the image URI and displays it in the ImageView.
    private final ActivityResultLauncher<Intent> pickImageLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                    selectedImageUri = result.getData().getData();
                    if (selectedImageUri != null) {
                        selectedImageView.setImageURI(selectedImageUri);
                        takePictureButton.setVisibility(View.GONE);
                        addPhotoButton.setVisibility(View.GONE);
                    }
                }
            });

    //adds image to gallery
    private void addToGallery(String imageUri) {
        String animal = animalName.getText().toString().trim();

        //Check if animal name exists in gallery
        animalExists = false;
        for (QuizAppEntity i : GalleryImageCollection.imageList) {
            if (i.getTitle().equalsIgnoreCase(animal)) {
                animalExists = true;
                break;
            }
        }

        //Show toast if animal name already exists
        if (animalExists) {
            Log.d("New_picture", "animal name already exists!");
            Toast.makeText(New_picture.this, "Animal is already added to the quiz!", Toast.LENGTH_SHORT).show();
            animalName.setText("");

        } else {
            //add animal to gallery
            String capitalizedAnimal = animal.substring(0, 1).toUpperCase() + animal.substring(1).toLowerCase();
            int newId = GalleryImageCollection.imageList.size() + 1;
            GalleryImageCollection.imageList.add(new QuizAppEntity(imageUri, capitalizedAnimal));
            Log.d("New_picture", "Added image to gallery: " + imageUri);
            Toast.makeText(New_picture.this, "Animal added to quiz gallery!", Toast.LENGTH_SHORT).show();

            // Return to gallery
            Intent intent = new Intent(New_picture.this, Gallery.class);
            startActivity(intent);
        }
    }
}