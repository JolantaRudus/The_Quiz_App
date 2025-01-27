package com.example.thequizapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class New_picture extends AppCompatActivity {


    private Button cancelButton, addButton;
    //addPhoto, to let the user choose from the media gallery is MANDATORY
    //takePicture is optional
    private ImageButton takePictureButton, addPhotoButton;
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

        //takes user back to start
        cancelButton = findViewById(R.id.pictureCancel);
        cancelButton.setOnClickListener(v -> {
            Log.d("New_picture", "Cancel button clicked");
            Intent intent = new Intent(New_picture.this, Gallery.class);
            startActivity(intent);
        });

        //gives user option to add a picture from the image gallery.
        addPhotoButton = findViewById(R.id.addPhoto);
        addPhotoButton.setOnClickListener(v ->{
            Log.d("New_picture", "addPhoto button clicked");
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            startActivity(intent);
        });

       /* Button takePicture = findViewById(R.id.takePicture);
        takePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("takePicture", "Picture taken");
                startActivity();
            }
        })*/

    }
}