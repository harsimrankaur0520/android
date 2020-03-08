package com.example.signup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class Password extends AppCompatActivity {

    private EditText passwordEmail;
    private Button resetpassword;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);


        passwordEmail = (EditText)findViewById(R.id.et_passowrdEmail);
        resetpassword= (Button)findViewById(R.id.btn_resetpassword);
        firebaseAuth = FirebaseAuth.getInstance();


        resetpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String useremail = passwordEmail.getText().toString().trim();


                if(useremail.equals("")){
                    Toast.makeText(Password.this,"Please enter your registererd email ID", Toast.LENGTH_SHORT).show();

                }else{
                    firebaseAuth.sendPasswordResetEmail(useremail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(Password.this,"Password reser email semt!", Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(Password.this, loginForm.class));
                            }else{
                                Toast.makeText(Password.this,"Error in sending password reset email!", Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
                }


            }
        });
    }
}
