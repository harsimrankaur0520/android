package com.example.signup;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class frag_VolunteerList extends Fragment {

    View v;
    FloatingActionButton btn_upload;

    private RecyclerView mRecyclerView;
    private DatabaseReference databaseReference;
    private ValueEventListener eventListener;
    ProgressDialog progressDialog; //= new ProgressDialog(getContext());

    Model_VolunteerList mModel;
    private List<Model_VolunteerList> model_volunteerLst = new ArrayList<>();
    RecyclerViewAdapter recyclerAdapter =new RecyclerViewAdapter(getContext(),model_volunteerLst);


    public frag_VolunteerList() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.fragment_frag__volunteer_list,container,false);





        return v;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btn_upload = (FloatingActionButton) v.findViewById(R.id.Button);
        btn_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), uploadVolList.class);
                startActivity(i);
            }
        });

        mRecyclerView=(RecyclerView)v.findViewById(R.id.volunteerList_recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(recyclerAdapter);


        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading information....");
        databaseReference= FirebaseDatabase.getInstance().getReference("VOLUNTEER");
        progressDialog.show();
        eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                model_volunteerLst.clear();
                for(DataSnapshot itemSnapshot: dataSnapshot.getChildren()){
                    //mModel = itemSnapshot.getValue(Model_VolunteerList.class);

                    //model_volunteerLst.add(mModel);
                }
                recyclerAdapter.notifyDataSetChanged();
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                progressDialog.dismiss();
            }
        });


    }

}





