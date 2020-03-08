package com.example.signup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class ValuePoint extends AppCompatActivity {
    TextView txt;
    TextView valuepoints;
    int newpts =0;

    GridView gridView;


    FirebaseDatabase firebaseDatabase;
    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_value_point);

        txt = (TextView) findViewById(R.id.yourVP_txt);
        valuepoints = (TextView) findViewById(R.id.vp);
        gridView = (GridView) findViewById(R.id.grid_view_vp);  // casting gridview
        gridView.setAdapter(new gridview_adapter(this));

//        time t = new time();
//        t.setCurrentpoints(valuepoints.getText().toString());
//        //valuepoints.setText(t.getValuepts());

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        databaseReference = firebaseDatabase.getReference("Time");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                time t = dataSnapshot.getValue(time.class);
                int amt = t.getValuepts();
                System.out.println("amt"+ amt);
                t.setCurrentpoints(valuepoints.getText().toString().trim());
                int currentpoints = Integer.parseInt(t.getCurrentpoints());
                System.out.println("current"+ currentpoints);


                 newpts  =currentpoints + amt;
                System.out.println("new"+ newpts);


                valuepoints.setText(String.valueOf(newpts));

                System.out.println("value"+ valuepoints);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
