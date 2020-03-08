package com.example.signup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.signup.Prevalent.Prevalent;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import io.paperdb.Paper;

public class launcher extends AppCompatActivity {

    private Button joinNowButton, loginButton;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        joinNowButton=(Button) findViewById(R.id.launcher_join_now_button);
        loginButton=(Button) findViewById(R.id.launcher_loginbutton);

        Paper.init(this);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),loginForm.class) );
            }
        });

        joinNowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),SignupForm.class) );
            }
        });


        /* this code will check details from firebase and automatically check whether the user is
        loggedin or not from firebase.... this code is more efficient as the user login once ,
        didn't see the launcher page agin and again...
         */

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();

        if(user != null){
            finish();
            startActivity(new Intent(launcher.this, MainActivity.class));

        }








        /*
        if remember me is checked , then automatically providing details and checking authorization
         */


//        String UserEmailKey=Paper.book().read(Prevalent.UserEmailKey);
//        String UserPasswordKey=Paper.book().read(Prevalent.UserPasswordKey);
//
//        if(UserEmailKey !=""  && UserPasswordKey !=""){
//
//            if(!TextUtils.isEmpty(UserEmailKey)  && !TextUtils.isEmpty(UserPasswordKey)){
//                AllowAccess(UserEmailKey,UserPasswordKey);
//            }
//        }

    }

//    private void AllowAccess(String userEmailKey, String userPasswordKey) {
//
//        firebaseAuth = FirebaseAuth.getInstance();
//
//        firebaseAuth.signInWithEmailAndPassword(userEmailKey, userPasswordKey)
//                .addOnCompleteListener(launcher.this, new OnCompleteListener<AuthResult>() {
//                            @Override
//                            public void onComplete(@NonNull Task<AuthResult> task) {
//                                if (task.isSuccessful()) {
//
//                                    startActivity(new Intent(getApplicationContext(),MainActivity.class) );
//                                    Toast.makeText(launcher.this, "Already Logged in", Toast.LENGTH_SHORT).show();
//
//                                } else {
//                                    Toast.makeText(launcher.this, "Login Failed or User not Available", Toast.LENGTH_SHORT).show();
//
//                                }
//                            }
//                        }
//
//                );
//
//    }
}
