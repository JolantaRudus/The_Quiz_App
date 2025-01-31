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
    private Boolean addedAnimalPhoto = false;
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
            //addedAnimalPhoto = true;
        });


       /* takePictureButton.setOnClickListener(v ->  {

            Log.d("takePicture", "Picture taken");
        });*/

       /* addToGalleryButton = findViewById(R.id.addButton);
        addToGalleryButton.setOnClickListener(v -> {
            if(animalName.getText() != null && addedAnimalPhoto) {
                //add animal name and photo to array/db
                Log.d("New_picture", "added name and photo to array");
                //return to gallery Screen
                Log.d("New_picture", "Return to gallery screen");
                Intent intent = new Intent(New_picture.this, Gallery.class);
                startActivity(intent);
            }
        });*/
    }

    private final ActivityResultLauncher<Intent> pickImageLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                    Uri selectedImageUri = result.getData().getData();
                    if (selectedImageUri != null) {
                        selectedImage.setImageURI(selectedImageUri);
                        takePictureButton.setVisibility(View.GONE);
                        addPhotoButton.setVisibility(View.GONE);
                    }
                }
            });
}