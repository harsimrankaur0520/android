package com.example.signup;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class MyHolder extends RecyclerView.ViewHolder {

    ImageView mImageView;
    TextView mTitle, mDes;

    CardView mcardView;

    MyHolder( View itemView) {
        super(itemView);

        mImageView = itemView.findViewById(R.id.imagetv);
        mTitle = itemView.findViewById(R.id.titlTv);
        mDes = itemView.findViewById(R.id.descriptionTv);

        mcardView = itemView.findViewById(R.id.mycardView);


    }
}
