package com.example.signup;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class splashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Thread thread=new Thread(){

            public void run(){

                try{

                    sleep(1000);
                }catch (Exception e){

                    e.printStackTrace();
                }finally {

                    Intent mainIntent=new Intent(splashScreen.this,launcher.class);
                    startActivity(mainIntent);

                }

            }
        };

        thread.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
