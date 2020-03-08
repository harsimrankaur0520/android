package com.example.signup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Calendar;

public class SignupForm extends AppCompatActivity {
    EditText txtEmail, txtPassword, txtConfirmPassword, txt_fullName;
    Button btn_register;
    RadioButton radioGenderMale, radioGenderFemale;
    private FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;
    String gender="";
    String fullName,email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_form);
        getSupportActionBar().setTitle("Signup Form");

        txtEmail = (EditText)findViewById(R.id.editEmail);
        txt_fullName = (EditText)findViewById(R.id.editFullName);
        txtPassword = (EditText)findViewById(R.id.editPassword);
        txtConfirmPassword = (EditText)findViewById(R.id.editConfirmPass);
        btn_register = (Button) findViewById(R.id.btnregister);
        radioGenderMale = (RadioButton)findViewById(R.id.male);
        radioGenderFemale = (RadioButton)findViewById(R.id.female);

        //databaseReference = FirebaseDatabase.getInstance().getReference("USERS");

        firebaseAuth = FirebaseAuth.getInstance();


        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                email = txtEmail.getText().toString().trim() ;
                String password = txtPassword.getText().toString().trim();
                String confirmpassword = txtConfirmPassword.getText().toString().trim();
                fullName = txt_fullName.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(SignupForm.this, "Please Enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(fullName)){
                    Toast.makeText(SignupForm.this, "Please Enter FullName", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(radioGenderMale.isChecked()){
                    gender="Male";
                }

                if(radioGenderFemale.isChecked()){
                    gender="Female";
                }

                if(TextUtils.isEmpty(password)){
                    Toast.makeText(SignupForm.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(confirmpassword)){
                    Toast.makeText(SignupForm.this, "Please Enter Confirm Password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(password.length()<6)
                {
                    Toast.makeText(SignupForm.this, "Password too short", Toast.LENGTH_SHORT).show();
                }


                if(password.equals(confirmpassword)){
                    firebaseAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(SignupForm.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        sendEmailVerification();
                                        //sendUserData();
                                        firebaseAuth.signOut();
                                        //Toast.makeText(SignupForm.this,"Successfully Registered, Verification mail sent! ",Toast.LENGTH_SHORT).show();
                                        finish();
                                        startActivity(new Intent(SignupForm.this, loginForm.class));

                                    } else {
                                        Toast.makeText(SignupForm.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                                    }

                                    // ...
                                }
                            });
                }

            }
        });
    }

    // code to send verification mail to gmail

    private void sendEmailVerification(){
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if(firebaseUser != null)
        {
            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        sendUserData();
                        Toast.makeText(SignupForm.this,"Registration Successfull!!. Please verify your email! ",Toast.LENGTH_SHORT).show();
                        firebaseAuth.signOut();
                        finish();
                        startActivity(new Intent(SignupForm.this, loginForm.class));
                    }else{
                        Toast.makeText(SignupForm.this,"verfication mail has'nt been sent! ",Toast.LENGTH_SHORT).show();

                    }

                }
            });
        }

    }
    private void sendUserData(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        String myCurrentDateTime = DateFormat.getDateTimeInstance()
                .format(Calendar.getInstance().getTime());
        DatabaseReference myref = database.getReference("USERS").child(myCurrentDateTime);

        //Model_Signup FullName = new Model_Signup(fullName);
        Model_Signup user = new Model_Signup(fullName,email);
        myref.setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

            }
        });
    }



}
