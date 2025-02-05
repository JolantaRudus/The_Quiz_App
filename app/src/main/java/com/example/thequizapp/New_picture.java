package com.example.thequizapp;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.File;

public class New_picture extends AppCompatActivity {


    private Button cancelButton, takePictureButton, addPhotoButton, addToGalleryButton;
    //addPhoto, to let the user choose from the media gallery is MANDATORY
    //takePicture is optional
    private ImageView selectedImage;
    ActivityResultLauncher<Intent> resultLauncher;
    private Uri photoUri;
    private EditText animalName;
    boolean animalExists = false;
    private Uri selectedImageUri;
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

        //buttons
        cancelButton = findViewById(R.id.pictureCancel);
        addPhotoButton = findViewById(R.id.addPhoto);
        takePictureButton = findViewById(R.id.takePicture);


        //other elements
        animalName = findViewById(R.id.inputText);
        selectedImage = findViewById(R.id.selectedImage);


        //takes user back to start
        cancelButton.setOnClickListener(v -> {
            Log.d("New_picture", "Cancel button clicked");
            Intent intent = new Intent(New_picture.this, Gallery.class);
            startActivity(intent);
        });

        //gives user option to add a picture from the image gallery.
        addPhotoButton.setOnClickListener(v -> {
            Log.d("New_picture", "addPhoto button clicked");
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            pickImageLauncher.launch(intent);
        });


       /* takePictureButton.setOnClickListener(v ->  {

            Log.d("takePicture", "Picture taken");
        });*/

       addToGalleryButton = findViewById(R.id.addButton);
        addToGalleryButton.setOnClickListener(v -> {
            if (selectedImageUri != null && !animalName.getText().toString().trim().isEmpty()) {
                addToGallery(selectedImageUri.toString());
            } else {
                Log.d("New_picture", "No image or name provided!");
                Toast.makeText(New_picture.this, "No image or name provided!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private final ActivityResultLauncher<Intent> pickImageLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                    selectedImageUri = result.getData().getData();
                    if (selectedImageUri != null) {
                        selectedImage.setImageURI(selectedImageUri);
                        takePictureButton.setVisibility(View.GONE);
                        addPhotoButton.setVisibility(View.GONE);
                    }
                }
            });

    private void addToGallery(String imageUri) {
        String animal = animalName.getText().toString().trim();

        //Check if animalname exists
        animalExists = false;
        for (ImageItem i : GalleryImageCollection.imageList) {
            if (i.getTitle().equalsIgnoreCase(animal)) {
                animalExists = true;
                break;
            }
        }

        //Show toast animal name already exists
        if (animalExists) {
            Log.d("New_picture", "animal name already exists!");
            Toast.makeText(New_picture.this, "Animal is already added to the quiz!", Toast.LENGTH_SHORT).show();
            animalName.setText("");

        } else {

            String capitalizedAnimal = animal.substring(0, 1).toUpperCase() + animal.substring(1).toLowerCase();
            int newId = GalleryImageCollection.imageList.size() + 1;
            GalleryImageCollection.imageList.add(new ImageItem(newId, imageUri, capitalizedAnimal));
            Log.d("New_picture", "Added image to gallery: " + imageUri);
            Toast.makeText(New_picture.this, "Animal added to quiz gallery!", Toast.LENGTH_SHORT).show();

            // Return to gallery
            Intent intent = new Intent(New_picture.this, Gallery.class);
            startActivity(intent);
        }
    }
}