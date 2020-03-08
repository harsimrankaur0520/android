package com.example.signup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import io.paperdb.Paper;

public class MainActivity extends AppCompatActivity {
    Button btnvolunteer , btnrequester;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnvolunteer = findViewById(R.id.btnvolunteer);
        btnrequester = findViewById(R.id.btnrequester);
        btnvolunteer.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                        Intent intent = new Intent(".Volunteer");
                        //startActivity(intent);
                        startActivity(new Intent(getApplicationContext(),Volunteer.class) );


                    }
                }
        );
        btnrequester.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                        Intent intent = new Intent(".Requester");
                        startActivity(new Intent(getApplicationContext(), Requester.class));

                    }
                }
        );
//        OnClickButtonListener();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Toast.makeText(this, getString(R.string.backPressed), Toast.LENGTH_SHORT).show();
    }

    //    public void OnClickButtonListener(){
//
//
//
//
//
////        btnlogout.setOnClickListener(
////                new View.OnClickListener() {
////                    @Override
////                    public void onClick(View view) {
////                        Paper.book().destroy();
////                        Intent intent = new Intent(".loginForm");
////                        startActivity(new Intent(getApplicationContext(), launcher.class));
////
////                    }
////                }
////        );
//
//    }
}
