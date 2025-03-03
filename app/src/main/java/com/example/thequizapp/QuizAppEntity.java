package com.example.thequizapp;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "gallery")

public class QuizAppEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "title")
    public String title;

    @ColumnInfo(name = "imageUri")
    public String imageUri;

    @ColumnInfo(name = "imageDrawable")
    public Integer imageDrawable;

    //public QuizAppEntity() {} // Default constructor required for Room, vet ikke om denne er nødvendig

    public QuizAppEntity(String imageUri, String title) {
        this.title = title;
        this.imageUri = imageUri;
        this.imageDrawable = null;
    }

    public QuizAppEntity(int imageResId, String title) {
        this.title = title;
        this.imageDrawable = imageResId;
        this.imageUri = null;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public Integer getImageDrawable() {
        return imageDrawable;
    }
}
