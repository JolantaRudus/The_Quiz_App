package com.example.thequizapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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

    private List<ImageItem> imageList;
    private Context context;

    public GalleryAdapter(Context context, List<ImageItem> imageList) {
        this.context = context; // Initialize the context
        this.imageList = imageList;
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
        ImageItem currentItem = imageList.get(position);
        holder.imageView.setImageResource(currentItem.getImageResId());
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
        builder.setNegativeButton("Cancel", null);
        builder.show();
    }

    //Method to delete the image from the list
    private void deleteImage(int position) {
        imageList.remove(position);
        notifyItemRemoved(position);
    }
}
