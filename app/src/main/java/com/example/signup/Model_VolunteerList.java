package com.example.signup;

public class Model_VolunteerList {
    private String Name;
    private String Phone;
    private int Photo;

    public Model_VolunteerList(){

    }

    public Model_VolunteerList(String name, String phone, int photo) {
        Name = name;
        Phone = phone;
        Photo = photo;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public int getPhoto() {
        return Photo;
    }

    public void setPhoto(int photo) {
        Photo = photo;
    }
}
