package com.example.thequizapp;

import java.util.ArrayList;
import java.util.List;


public class GalleryImageCollection {

    public static List<ImageItem> imageList = new ArrayList<>();

    static {
        // Adding ImageItem objects to the imageList
        imageList.add(new ImageItem(1, R.drawable.antelope, "Antelope"));
        imageList.add(new ImageItem(2, R.drawable.cat, "Cat"));
        imageList.add(new ImageItem(3, R.drawable.dog, "Dog"));
        imageList.add(new ImageItem(4, R.drawable.fox, "Fox"));
        imageList.add(new ImageItem(5, R.drawable.giraffe, "Giraffe"));
        imageList.add(new ImageItem(6, R.drawable.horse, "Horse"));
        imageList.add(new ImageItem(7, R.drawable.lion, "Lion"));
        imageList.add(new ImageItem(8, R.drawable.eagle, "Eagle"));
        imageList.add(new ImageItem(9, R.drawable.cow, "Cow"));
        imageList.add(new ImageItem(10, R.drawable.lynx, "Lynx"));
        imageList.add(new ImageItem(11, R.drawable.penguin, "Penguin"));
        imageList.add(new ImageItem(12, R.drawable.shark, "Shark"));
        imageList.add(new ImageItem(13, R.drawable.moose, "Moose"));
        imageList.add(new ImageItem(14, R.drawable.tiger, "Tiger"));
        imageList.add(new ImageItem(15, R.drawable.wolf, "Wolf"));
        imageList.add(new ImageItem(16, R.drawable.spider, "Spider"));
        imageList.add(new ImageItem(17, R.drawable.jaguar, "Jaguar"));
        imageList.add(new ImageItem(18, R.drawable.butterfly, "Butterfly"));
        imageList.add(new ImageItem(19, R.drawable.panda, "Panda"));
        imageList.add(new ImageItem(20, R.drawable.zebra, "Zebra"));
        imageList.add(new ImageItem(21, R.drawable.bat, "Bat"));
        imageList.add(new ImageItem(22, R.drawable.fish, "Fish"));
    }
}