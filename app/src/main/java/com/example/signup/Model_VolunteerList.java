package com.example.signup;

public class Model_VolunteerList {


    private  String fullName,phoneNo,photoImg;

    public Model_VolunteerList(){

    }

    public Model_VolunteerList(String fullName, String phoneNo) {
        this.fullName = fullName;
        this.phoneNo = phoneNo;
    }

    public Model_VolunteerList(String fullName, String phoneNo, String photoImg) {
        this.fullName = fullName;
        this.phoneNo = phoneNo;
        this.photoImg = photoImg;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getPhotoImg() {
        return photoImg;
    }

    public void setPhotoImg(String photoImg) {
        this.photoImg = photoImg;
    }
}
