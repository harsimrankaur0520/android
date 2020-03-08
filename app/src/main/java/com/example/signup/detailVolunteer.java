package com.example.signup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class detailVolunteer extends AppCompatActivity  {

    TextView Description ;
    EditText spinnertext;
    ImageView Image;
    Button book, selectActivity;
    int txtdata;
    DatabaseReference databaseReference;
    int sum ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_volunteer);


        Description = (TextView)findViewById(R.id.txtDescription);
        Image= (ImageView)findViewById(R.id.ivImage);
        book = (Button)findViewById(R.id.btnbookSlot);
        //spinnertext = (EditText)findViewById(R.id.spinner_txt);
        selectActivity = (Button)findViewById(R.id.Selectactivity);



        Bundle mbundle = getIntent().getExtras();

        if(mbundle!=null){
            Description.setText(mbundle.getString("Description"));
            //Image.setImageResource(mbundle.getInt("Image"));

            Glide.with(this)
                    .load(mbundle.getString("Image"))
                    .into(Image);




        };
        selectActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Volunteer_activityList.class) );

            }
        });

        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                        Toast.makeText(detailVolunteer.this, "you have booked a slot", Toast.LENGTH_SHORT).show();

                }


        });



    }

//    private void sendUserTime(){
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference myref = database.getReference("Time");
//        time time = new time(txtdata);
//
//        time.setValuepts(txtdata);
//        myref.setValue(time);
    //}



}

