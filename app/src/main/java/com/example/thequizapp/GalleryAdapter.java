package com.example.thequizapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder> {

    private List<QuizAppEntity> imageList;
    private Context context;

    private QuizAppViewModel viewModel;


    public GalleryAdapter(Gallery context, List<QuizAppEntity> imageList, QuizAppViewModel viewModel) {
        this.context = context; // Initialize the context
        this.imageList = imageList;
        this.viewModel = viewModel;
    }

    public static class GalleryViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView titleTextView;
        public ImageButton deleteButton;

        public GalleryViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.cardImageView);
            titleTextView = itemView.findViewById(R.id.cardTitleTextView);
            deleteButton =itemView.findViewById(R.id.deleteButton);
        }
    }

    @NonNull
    @Override
    public GalleryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itenView = LayoutInflater.from(parent.getContext()).inflate(R.layout.gallary_item, parent, false);
        return new GalleryViewHolder(itenView);
    }

    @Override
    public void onBindViewHolder(@NonNull GalleryViewHolder holder, int position) {
        QuizAppEntity currentItem = imageList.get(position);
        // Load images based on whether it's a drawable or a gallery URI
        if (currentItem.getImageUri() != null && !currentItem.getImageUri().isEmpty()) {
            // Try to parse as a URI
            try {
                Uri imageUri = Uri.parse(currentItem.getImageUri());
                holder.imageView.setImageURI(imageUri);
            } catch (Exception e) {
                // If it's not a valid URI, try to load it as a drawable resource
                try {
                    int drawableId = Integer.parseInt(currentItem.getImageUri());
                    holder.imageView.setImageResource(drawableId);
                } catch (NumberFormatException nfe) {
                    // Handle the case where it's neither a URI nor a drawable ID
                    holder.imageView.setImageResource(R.drawable.ic_launcher_foreground);
                }
            }
        } else {
            // If imageUri is null or empty, set a placeholder image
            holder.imageView.setImageResource(R.drawable.ic_launcher_foreground);
        }
        holder.titleTextView.setText(currentItem.getTitle());
        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDeleteConfirmationDialog(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    //Method to show the delete image confirmation dialog
    private void showDeleteConfirmationDialog(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Delete Image");
        builder.setMessage("Are you sure you want to delete this image?");
        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteImage(position);
            }
        });
        builder.setNegativeButton("Cancel", (dialog, which) -> {
            // Do nothing, just dismiss the dialog
        });
        builder.show();
    }

    //Method to delete the image from the list
    private void deleteImage(int position) {
        QuizAppEntity imageToDelete = imageList.get(position);

        // Remove from database
        viewModel.deleteImage(imageToDelete);

        // Remove from list and update UI
        imageList.remove(position);
        notifyItemRemoved(position);
    }
}