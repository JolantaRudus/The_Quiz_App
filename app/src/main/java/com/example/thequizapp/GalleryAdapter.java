package com.example.thequizapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder> {

    private List<ImageItem> imageList;

    public GalleryAdapter(List<ImageItem> imageList) {
        this.imageList = imageList;
    }

    public static class GalleryViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView titleTextView;

        public GalleryViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.cardImageView);
            titleTextView = itemView.findViewById(R.id.cardTitleTextView);
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
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }
}
