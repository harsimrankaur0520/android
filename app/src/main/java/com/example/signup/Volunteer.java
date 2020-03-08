package com.example.signup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import io.paperdb.Paper;


import io.paperdb.Paper;

public class Volunteer extends AppCompatActivity  implements
        NavigationView.OnNavigationItemSelectedListener {
    RecyclerView mRecyclerView;
    Adapter myAdapter;
    List<Model> myList;
    Model mModel;
    EditText txt_Search;
    FirebaseAuth firebaseAuth;
    NavigationView navigationView;

    private DatabaseReference databaseReference;
     ValueEventListener eventListener;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer);
        getSupportActionBar().setTitle("Volunteer");

        mDrawerLayout=(DrawerLayout)findViewById(R.id.drawer_layout);
        navigationView = (NavigationView)findViewById(R.id.navigationView);

        navigationView.setNavigationItemSelectedListener(this);

        mToggle=new ActionBarDrawerToggle(this,mDrawerLayout,R.string.open,R.string.close);
        //mDrawerLayout.addDrawerListener(mToggle);
        mDrawerLayout.setDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        firebaseAuth = FirebaseAuth.getInstance();


        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(Volunteer.this, 1);
        mRecyclerView.setLayoutManager(gridLayoutManager);

        txt_Search = (EditText)findViewById(R.id.searchText);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading Information...");

        myList = new ArrayList<>();

        myAdapter = new Adapter(Volunteer.this,myList);
        mRecyclerView.setAdapter(myAdapter);

        databaseReference= FirebaseDatabase.getInstance().getReference("NGO");
        progressDialog.show();
        eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                myList.clear();
                for(DataSnapshot itemSnapshot: dataSnapshot.getChildren()){
                  mModel = itemSnapshot.getValue(Model.class);

                    myList.add(mModel);
                }
                myAdapter.notifyDataSetChanged();
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                progressDialog.dismiss();
            }
        });


        txt_Search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());



            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem item){
        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void filter(String text) {

        ArrayList<Model> filterList = new ArrayList<>();
        for (Model item: myList){
            if(item.getTitle().toLowerCase().contains(text.toLowerCase())){

                filterList.add(item);
            }

        }

        myAdapter.filteredList(filterList);
    }


    public void btn_uploadngo(View view) {
        startActivity(new Intent(this,uploadNgoList.class));
    }

    @Override
    public boolean onNavigationItemSelected( MenuItem item) {
        int id = item.getItemId();

        switch (id){

            case R.id.nav_home:
                goToHome();
                break;

            case R.id.nav_profile:
                goToProfile();
                break;

            case R.id.nav_valuePoints:
                goToValuepts();
                break;

            case R.id.nav_feedback:
                goToFeedback();
                break;

            case R.id.nav_Logout:
                logout();
                break;

            case R.id.nav_settings:
                goToSetting();
                break;


        }


        return true;
    }

    private void goToValuepts() {
        Intent intent = new Intent(getApplicationContext(), ValuePoint.class);
        startActivity(intent);
    }

    private void goToProfile() {
        Intent intent = new Intent(getApplicationContext(), Profile.class);
        startActivity(intent);
    }

    private void goToHome() {
        Intent intent = new Intent(getApplicationContext(), Volunteer.class);
        startActivity(intent);
    }

    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Paper.book().destroy();
        Intent intent = new Intent(getApplicationContext(), loginForm.class);
        startActivity(intent);
        finish();

    }

    private void sendToLogin() {



    }

    private void goToFeedback() {
        Intent intent = new Intent(getApplicationContext(), feedbackForm.class);
        startActivity(intent);
    }


    private void goToSetting() {
        Intent intent = new Intent(getApplicationContext(), settingLanguage.class);
        startActivity(intent);
    }
}

