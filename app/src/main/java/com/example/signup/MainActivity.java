package com.example.signup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import io.paperdb.Paper;

public class MainActivity extends AppCompatActivity {
    private  static Button btnvolunteer , btnrequester;
    private static Button btnlogout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle(" Status");
        OnClickButtonListener();
    }

    public void OnClickButtonListener(){
        btnvolunteer = (Button)findViewById(R.id.btnvolunteer);
        btnrequester = (Button)findViewById(R.id.btnrequester);

        btnlogout=(Button) findViewById(R.id.logout_button);

        btnvolunteer.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(".Volunteer");
                        //startActivity(intent);
                        startActivity(new Intent(getApplicationContext(),Volunteer.class) );


                    }
                }
        );
        btnrequester.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(".Requester");
                        startActivity(new Intent(getApplicationContext(), Requester.class));

                    }
                }
        );

        btnlogout.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Paper.book().destroy();
                        Intent intent = new Intent(".loginForm");
                        startActivity(new Intent(getApplicationContext(), launcher.class));

                    }
                }
        );

    }
}
