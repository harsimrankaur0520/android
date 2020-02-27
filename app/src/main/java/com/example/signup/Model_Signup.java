package com.example.signup;

public class Model_Signup {
   public String fullname, email , phone;


    public Model_Signup()
    {

    }

    public Model_Signup(String fullname, String email, String phone) {

        this.fullname = fullname;
        this.email = email;
        this.phone = phone;
    }
    public Model_Signup(String fullname, String email) {

        this.fullname = fullname;
        this.email = email;
    }
//
   public Model_Signup(String fullname) {
        this.fullname = fullname;
   }


    public String getFullname() {return fullname; }

    public void setFullname(String fullname) { this.fullname = fullname; }

    public String getEmail() { return email; }

    public void setEmail(String email) {this.email = email; }

    public String getPhone() { return phone; }

    public void setPhone(String phone) { this.phone = phone; }
}
