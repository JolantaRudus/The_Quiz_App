package com.example.thequizapp;

import java.util.ArrayList;
import java.util.List;


public class GalleryImageCollection {

    public static List<QuizAppEntity> imageList = new ArrayList<>();

    static {
        // Adding ImageItem objects to the imageList
        imageList.add(new QuizAppEntity(R.drawable.antelope, "Antelope"));
        imageList.add(new QuizAppEntity(R.drawable.cat, "Cat"));
        imageList.add(new QuizAppEntity(R.drawable.dog, "Dog"));
        imageList.add(new QuizAppEntity(R.drawable.fox, "Fox"));
        imageList.add(new QuizAppEntity(R.drawable.giraffe, "Giraffe"));
        imageList.add(new QuizAppEntity(R.drawable.horse, "Horse"));
        imageList.add(new QuizAppEntity(R.drawable.lion, "Lion"));
        imageList.add(new QuizAppEntity(R.drawable.eagle, "Eagle"));
        imageList.add(new QuizAppEntity(R.drawable.cow, "Cow"));
        imageList.add(new QuizAppEntity(R.drawable.lynx, "Lynx"));
        imageList.add(new QuizAppEntity(R.drawable.penguin, "Penguin"));
        imageList.add(new QuizAppEntity(R.drawable.shark, "Shark"));
        imageList.add(new QuizAppEntity(R.drawable.moose, "Moose"));
        imageList.add(new QuizAppEntity(R.drawable.tiger, "Tiger"));
        imageList.add(new QuizAppEntity(R.drawable.wolf, "Wolf"));
        imageList.add(new QuizAppEntity(R.drawable.spider, "Spider"));
        imageList.add(new QuizAppEntity(R.drawable.jaguar, "Jaguar"));
        imageList.add(new QuizAppEntity(R.drawable.butterfly, "Butterfly"));
        imageList.add(new QuizAppEntity(R.drawable.panda, "Panda"));
        imageList.add(new QuizAppEntity(R.drawable.zebra, "Zebra"));
        imageList.add(new QuizAppEntity(R.drawable.bat, "Bat"));
        imageList.add(new QuizAppEntity(R.drawable.fish, "Fish"));
    }

}