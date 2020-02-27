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
    TextView valuepoints ;
    int sum;

    GridView gridView;


    FirebaseDatabase firebaseDatabase;
    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_value_point);

        txt=(TextView)findViewById(R.id.yourVP_txt);
        valuepoints = (TextView)findViewById(R.id.vp);
        gridView = (GridView)findViewById(R.id.grid_view_vp);  // casting gridview
        gridView.setAdapter(new gridview_adapter(this));


        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        databaseReference = firebaseDatabase.getReference("Time");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                time pts = dataSnapshot.getValue(time.class);

                int currentPoints=Integer.parseInt(valuepoints.getText().toString().trim());

                System.out.println("currentPoints" + currentPoints);
                //currentPoints=Integer.parseInt(valuepoints.getText().toString());

                int recentpts = pts.getValuepts();
                System.out.println("recentpts" + recentpts);
                int newPoint=currentPoints+recentpts;
                pts.setValuepts(newPoint);


                System.out.println(newPoint + " newPoint");

                valuepoints.setText(String.valueOf(pts.getValuepts()));
                //pts.setValuepts(String.valueOf(pts.getValuepts()));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ValuePoint.this,databaseError.getCode(),Toast.LENGTH_SHORT).show();


            }
        });

    }
}
