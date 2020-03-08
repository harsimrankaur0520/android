package com.example.signup;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<Volunteerlist_holder> {

    Context mContext;
    List<Model_VolunteerList> mData;
    CardView mcardView;
    Dialog myDialog;

    public RecyclerViewAdapter(Context mContext, List<Model_VolunteerList> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override

    public Volunteerlist_holder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_volunteerlist,parent,false);
        //final Volunteerlist_holder vHolder=new Volunteerlist_holder(v);
        return new Volunteerlist_holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final Volunteerlist_holder holder, int position) {
        holder.tv_name.setText(mData.get(position).getFullName());
        holder.tv_phone.setText(mData.get(position).getPhoneNo());
        holder.mbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

//        holder.mcardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(mContext,detailVolunteerList.class);
//                intent.putExtra("Image" , mData.get(holder.getAdapterPosition()).getPhoneNo());
//                intent.putExtra("Phone number", mData.get(holder.getAdapterPosition()).getPhoneNo());
//                mContext.startActivity(intent);
        //    }
      //  });

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }





}






























//            myDialog = new Dialog(mContext);
//            myDialog.setContentView(R.layout.dialog_volunteer);
//            myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//
//


//        vHolder.mbutton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                try {
//                    TextView dialog_name_tv=(TextView)myDialog.findViewById(R.id.dialog_name);
//                    TextView dialog_phone_tv=(TextView)myDialog.findViewById(R.id.dialog_phone);
//                    TextView dialog_specialization_tv=(TextView)myDialog.findViewById(R.id.dialog_specialization);
//                    ImageView dialog_volunteer_img=(ImageView)myDialog.findViewById(R.id.dialog_img);
//
//                    dialog_name_tv.setText(mData.get(vHolder.getAdapterPosition()).getName());
//                    dialog_phone_tv.setText(mData.get(vHolder.getAdapterPosition()).getPhone());
//                    //dialog_volunteer_img.setImageResource(mData.get(vHolder.getAdapterPosition()).getPhoto());
//
//                    Toast.makeText(parent.getContext(), "Test Click" + String.valueOf(vHolder.getAdapterPosition()), Toast.LENGTH_SHORT).show();
//                    myDialog.show();
//                }catch (Exception e){
//                    System.out.println(e);
//                }
//            }
//        });





//    @Override
//    public void onBindViewHolder(@NonNull  final MyViewHolder holder,final int position) {
//
//
//       // holder.img.setImageResource(mData.get(position).getPhoto());
//
////        Glide.with(getActivity()).load(mData.get(position).getPhoto()).into(holder.img);
//
//
//
//    private Context getActivity() {
//        return mContext;
//    }
//



