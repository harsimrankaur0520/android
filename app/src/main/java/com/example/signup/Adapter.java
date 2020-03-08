package com.example.signup;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<MyHolder> {

    private  Context c;
    private List<Model> modelList;
    CardView mcardView;
    private int lastPosition = -1;

    public Adapter(Context c, List<Model> modelList) {
        this.c = c;
        this.modelList = modelList;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyHolder holder, int position) {
        holder.mTitle.setText(modelList.get(position).getTitle());
        holder.mDes.setText(modelList.get(position).getDescription());
       // holder.mImageView.setImageResource(modelList.get(position).getImg());

        Glide.with(c)
                .load(modelList.get(position).getImg())
                .into(holder.mImageView);

        holder.mcardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(c,detailVolunteer.class);
                intent.putExtra("Image" , modelList.get(holder.getAdapterPosition()).getImg());
                intent.putExtra("Description", modelList.get(holder.getAdapterPosition()).getDescription());
                c.startActivity(intent);
            }
        });

        //setAnimation(holder.itemView, position);
    }


    //public void setAnimation(View viewToAnimate, int position){
      //  if(position>lastPosition){

        //    ScaleAnimation animation = new ScaleAnimation(0.0f,1.0f,0.0f,1.0f,
          //          Animation.RELATIVE_TO_SELF,0.5f,
            //        Animation.RELATIVE_TO_SELF,0.5f);
            //animation.setDuration(1500);
            //viewToAnimate.startAnimation(animation);
            //lastPosition = position;

        //}
    //}
    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public void filteredList(ArrayList<Model> filterList) {

        modelList = filterList;
        notifyDataSetChanged();
    }
}
