package com.example.thequizapp;

public class ImageItem {

    private int id;
    private Integer imageDrawable;
    private String title;
    private String imageUri;

    // Constructor for drawable resources
    public ImageItem(int id, int imageResId, String title) {
        this.id = id;
        this.imageDrawable = imageResId;
        this.title = title;
        this.imageUri = null;
    }

    // Constructor for images from URI
    public ImageItem(int id, String imageUri, String name) {
        this.id = id;
        this.imageUri = imageUri;
        this.title = name;
        this.imageDrawable = null;
    }
    public int getId() {
        return id;
    }
    public String getImageUri() {
        return imageUri;
    }
    public Integer getImageDrawable() {
        return imageDrawable;
    }

    public String getTitle() {
        return title;
    }
}