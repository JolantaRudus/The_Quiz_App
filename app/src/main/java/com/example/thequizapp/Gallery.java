package com.example.thequizapp;

import static com.example.thequizapp.GalleryImageCollection.imageList;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Gallery extends AppCompatActivity {

    private RecyclerView galleryRecyclerView;
    private LinearLayout buttonsLayout;
    private GalleryAdapter galleryAdapter;
    private QuizAppViewModel viewModel;
    private List<QuizAppEntity> imageList = new ArrayList<>();

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

            QuizAppViewModel viewModel = new ViewModelProvider(this).get(QuizAppViewModel.class);
            viewModel.populateDatabase(this);

            return insets;
        });


        // Initialize ViewModel
        viewModel = new ViewModelProvider(this).get(QuizAppViewModel.class);

        //Find the RecyclerView by its ID and the buttons layout by its ID
        galleryRecyclerView = findViewById(R.id.galleryRecyclerView);
        buttonsLayout = findViewById(R.id.bottomBar);

        //Find the buttons by their ID
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);

        // Create and set the adapter for the RecyclerView
        galleryAdapter = new GalleryAdapter(this, imageList, viewModel); // Create adapter
        galleryRecyclerView.setAdapter(galleryAdapter); // Set adapter
        galleryRecyclerView.setLayoutManager(new LinearLayoutManager(this)); // Set layout manager

        // Observe the LiveData from the ViewModel
        viewModel.getAllImages().observe(this, images -> {
            imageList.clear();
            imageList.addAll(images);
            galleryAdapter.notifyDataSetChanged();
        });

        // Set up button click listeners to test :P
        // Set up sorting button click listener to sort the list
        final boolean[] isSorted = {true};

        button1.setOnClickListener(v -> {
            if (isSorted[0]) {
                // Sort in ascending order (A-Z)
                imageList.sort(Comparator.comparing(QuizAppEntity::getTitle, String.CASE_INSENSITIVE_ORDER));
                Toast.makeText(this, "Sorted A-Z", Toast.LENGTH_SHORT).show();
            } else {
                // Sort in descending order (Z-A)
                Collections.sort(imageList, (item1, item2) -> item2.getTitle().compareTo(item1.getTitle()));
                Toast.makeText(this, "Sorted Z-A", Toast.LENGTH_SHORT).show();
            }
            // Toggle the sorting order
            isSorted[0] = !isSorted[0];

            galleryAdapter.notifyDataSetChanged(); // Notify the adapter about the changes
            // Takes the user to the top of the RecyclerView
            galleryRecyclerView.scrollToPosition(0);
        });

        button2.setOnClickListener(v -> {
            if (imageList.size() >= 3) {
                Log.d("Quiz", "Finish button clicked");
                Intent intent = new Intent(Gallery.this, Quiz.class);
                startActivity(intent);
            } else
                Toast.makeText(this, "Not enough images for the quiz!", Toast.LENGTH_SHORT).show();
        });
        button3.setOnClickListener(v -> {
            Log.d("NewPicture", "Open add picture");
            Intent intent = new Intent(Gallery.this, New_picture.class);
            startActivity(intent);
        });
    }
}