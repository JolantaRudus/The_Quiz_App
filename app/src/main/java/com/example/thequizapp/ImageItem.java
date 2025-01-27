package com.example.thequizapp;

public class ImageItem {

    private int id;
    private int imageResId;
    private String title;

    public ImageItem(int id, int imageResId, String title) {
        this.id = id;
        this.imageResId = imageResId;
        this.title = title;
    }


    public int getId() {
        return id;
    }

    public int getImageResId() {
        return imageResId;
    }

    public String getTitle() {
        return title;
    }
}