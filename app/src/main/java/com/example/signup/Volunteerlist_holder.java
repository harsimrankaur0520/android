package com.example.signup;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class Volunteerlist_holder extends RecyclerView.ViewHolder {

     TextView tv_name;
 TextView tv_phone;
     ImageView img;
     CardView mcardView;
    LinearLayout item_volunteer;
    Button mbutton;

    public Volunteerlist_holder( View itemView) {
        super(itemView);
        tv_name=itemView.findViewById(R.id.name_volunteer);
        tv_phone=(TextView) itemView.findViewById(R.id.phone_volunteer);
        img=(ImageView) itemView.findViewById(R.id.img_volunteer);
        mbutton=(Button)itemView.findViewById(R.id.btn_info_volunteer);
        mcardView = (CardView) itemView.findViewById(R.id.mycardViewVol);
    }
}
