package com.example.thequizapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Gallery extends AppCompatActivity {

    private RecyclerView galleryRecyclerView;
    private GalleryAdapter galleryAdapter;
    private QuizAppViewModel viewModel;
    private List<QuizAppEntity> imageList = new ArrayList<>();
    private Button sortButton, quizButton, addPictureButton;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_gallery);

        initializeUI();
        setupViewModel();
        setupRecyclerView();
        setupObservers();
        setupListeners();
    }

    private void initializeUI() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        galleryRecyclerView = findViewById(R.id.galleryRecyclerView);
        sortButton = findViewById(R.id.sortButton);
        quizButton = findViewById(R.id.quizButton);
        addPictureButton = findViewById(R.id.addImageButton);
    }

    private void setupViewModel() {
        viewModel = new ViewModelProvider(this).get(QuizAppViewModel.class);
        viewModel.populateDatabase(this);
    }

    private void setupRecyclerView() {
        galleryAdapter = new GalleryAdapter(this, imageList, viewModel);
        galleryRecyclerView.setAdapter(galleryAdapter);
        galleryRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setupObservers() {
        viewModel.getAllImages().observe(this, images -> {
            imageList.clear();
            imageList.addAll(images);
            galleryAdapter.notifyDataSetChanged();
        });
    }

    // Set up button click listeners to test :P
    // Set up sorting button click listener to sort the list
    private void setupListeners() {
        final boolean[] isSorted = {true};

        sortButton.setOnClickListener(v -> {
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

        quizButton.setOnClickListener(v -> {
            if (imageList.size() >= 3) {
                Log.d("Quiz", "Finish button clicked");
                Intent intent = new Intent(Gallery.this, Quiz.class);
                startActivity(intent);
            } else
                Toast.makeText(this, "Not enough images for the quiz!", Toast.LENGTH_SHORT).show();
        });
        addPictureButton.setOnClickListener(v -> {
            Log.d("NewPicture", "Open add picture");
            Intent intent = new Intent(Gallery.this, New_picture.class);
            startActivity(intent);
        });
    }
}