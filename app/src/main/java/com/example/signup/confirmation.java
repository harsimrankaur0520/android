package com.example.signup;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class confirmation extends AppCompatActivity {
    EditText checkin, checkout, totaltime;
    Button confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);


        checkin = (EditText) findViewById(R.id.et_checkin);
        checkout = (EditText) findViewById(R.id.et_checkout);
        totaltime = (EditText) findViewById(R.id.et_totaltime);

        confirm = (Button) findViewById(R.id.btn_confirmation);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                time t = new time();
                try{
                Integer vt = Integer.parseInt(totaltime.getText().toString());
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myref = database.getReference("Time");
               // time time = new time(vt);

                t.setValuepts(vt);
                myref.setValue(t);

                Toast.makeText(confirmation.this, "thank you for confirmation!!", Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    System.out.println(e);
                }
            }
        });



        }

    private void sendUserTime(){

    }
    }
