package com.example.signup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.signup.Prevalent.Prevalent;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.rey.material.widget.CheckBox;

import io.paperdb.Paper;

public class loginForm extends AppCompatActivity {

    EditText txtEmail, txtPassword;
    Button btn_login,btn_register;
    private FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;
    private TextView forgetPassword;
    Button go_to_home;

    private CheckBox chkBoxRememberMe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_form);

        txtEmail = (EditText) findViewById(R.id.email);
        txtPassword = (EditText) findViewById(R.id.password);
        btn_login = (Button) findViewById(R.id.login);
        forgetPassword = (TextView) findViewById(R.id.forgot_password_link);
        // btn_register = (Button) findViewById(R.id.register);

        go_to_home=(Button)findViewById(R.id.goToHome);

        Paper.init(this);

        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);


        Paper.init(this);



        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = txtEmail.getText().toString().trim() ;
                String password = txtPassword.getText().toString().trim();


                if(TextUtils.isEmpty(email)){
                    Toast.makeText(loginForm.this, "Please Enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    Toast.makeText(loginForm.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(password.length()<6)
                {
                    Toast.makeText(loginForm.this, "Password too short", Toast.LENGTH_SHORT).show();
                }


                // AllowAccessToAccount(email,password);

                progressDialog.setMessage("logging to your account");
                progressDialog.show();

                firebaseAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(loginForm.this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            progressDialog.dismiss();
                                            //Toast.makeText(loginForm.this, "Login Successfull", Toast.LENGTH_SHORT).show();
                                            checkEmailVerification();
                                        } else {
                                            progressDialog.dismiss();
                                            Toast.makeText(loginForm.this, "Login Failed or User not Available", Toast.LENGTH_SHORT).show();

                                        }
                                    }
                                }
                        );
            }
        });


        forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(loginForm.this, Password.class));

            }
        });

        go_to_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(loginForm.this,SignupForm.class));
            }
        });

    }

    private void checkEmailVerification(){
        FirebaseUser firebaseUser = firebaseAuth.getInstance().getCurrentUser();
        Boolean emailflag = firebaseUser.isEmailVerified();
        startActivity(new Intent(loginForm.this,MainActivity.class));

//        if(emailflag){
//            finish();
//            startActivity(new Intent(loginForm.this,MainActivity.class));
//        }else{
//            Toast.makeText(this, "verify you Email", Toast.LENGTH_SHORT).show();
//            firebaseAuth.signOut();
//        }
    }





}
