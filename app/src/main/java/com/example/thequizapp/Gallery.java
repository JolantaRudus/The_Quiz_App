package com.example.thequizapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.thequizapp.ImageItem;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Gallery extends AppCompatActivity {

    private RecyclerView galleryRecyclerView;
    private LinearLayout buttonsLayout;

    // Buttons
    private Button button1;
    private Button button2;
    private Button button3;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_gallery);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Find the RecyclerView by its ID and the buttons layout by its ID
        galleryRecyclerView = findViewById(R.id.galleryRecyclerView);
        buttonsLayout = findViewById(R.id.bottomBar);

        //Find the buttons by their ID
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);


        // List of image items objects with their corresponding image resource IDs and titles
        List<ImageItem> imageList = new ArrayList<>();
        imageList.add(new ImageItem(1, R.drawable.antilope, "Antelope"));
        //imageList.add(new ImageItem(2, R.drawable.cat, "Cat"));
        imageList.add(new ImageItem(3, R.drawable.dog,"Dog"));
        imageList.add(new ImageItem(4, R.drawable.fox, "Fox"));
        imageList.add(new ImageItem(5, R.drawable.giraffe, "Giraffe"));
        imageList.add(new ImageItem(6, R.drawable.horse, "Horse"));
        imageList.add(new ImageItem(7, R.drawable.lion, "Lion"));
        imageList.add(new ImageItem(8, R.drawable.eagle, "eagle"));
        //imageList.add(new ImageItem(9, R.drawable.cow, "Cow"));
        imageList.add(new ImageItem(10, R.drawable.lynx, "Lynx"));
        imageList.add(new ImageItem(11, R.drawable.penguin,"Penguin"));
        imageList.add(new ImageItem(12, R.drawable.shark, "Shark"));
        imageList.add(new ImageItem(13, R.drawable.moose, "Moose"));
        imageList.add(new ImageItem(14, R.drawable.tiger, "Tiger"));
        imageList.add(new ImageItem(15, R.drawable.wolf, "Wolf"));

        // Create and set the adapter for the RecyclerView
        GalleryAdapter galleryAdapter = new GalleryAdapter(imageList); // Create adapter
        galleryRecyclerView.setAdapter(galleryAdapter); // Set adapter
        galleryRecyclerView.setLayoutManager(new LinearLayoutManager(this)); // Set layout manager

        // Set up button click listeners to test :P
        button1.setOnClickListener(v -> Toast.makeText(Gallery.this, "Sort clicked", Toast.LENGTH_SHORT).show());
        button2.setOnClickListener(v -> {
            Log.d("Quiz", "Finish button clicked");
            Intent intent = new Intent(Gallery.this, Quiz.class);
            startActivity(intent);
        });
        button3.setOnClickListener(v -> {
            Log.d("NewPicture", "Open add picture");
            Intent intent = new Intent(Gallery.this, New_picture.class);
            startActivity(intent);
        });
        /*
         button2.setOnClickListener(v -> Toast.makeText(Gallery.this, "Quiz clicked", Toast.LENGTH_SHORT).show());
         */
    }
}