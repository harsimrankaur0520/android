package com.example.signup;

public class Model {
    private String title, description;
    private String img;

    public Model(){

    }

    public Model(String title, String description, String img) {
        this.title = title;
        this.description = description;
        this.img = img;
    }


    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getImg() {
        return img;
    }
}