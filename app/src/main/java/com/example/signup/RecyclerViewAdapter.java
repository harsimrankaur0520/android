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

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

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
    public MyViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_volunteerlist,parent,false);
        final MyViewHolder vHolder=new MyViewHolder(v);



            myDialog = new Dialog(mContext);
            myDialog.setContentView(R.layout.dialog_volunteer);
            myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));




        vHolder.mbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    TextView dialog_name_tv=(TextView)myDialog.findViewById(R.id.dialog_name);
                    TextView dialog_phone_tv=(TextView)myDialog.findViewById(R.id.dialog_phone);
                    TextView dialog_specialization_tv=(TextView)myDialog.findViewById(R.id.dialog_specialization);
                    ImageView dialog_volunteer_img=(ImageView)myDialog.findViewById(R.id.dialog_img);

                    dialog_name_tv.setText(mData.get(vHolder.getAdapterPosition()).getName());
                    dialog_phone_tv.setText(mData.get(vHolder.getAdapterPosition()).getPhone());
                    //dialog_volunteer_img.setImageResource(mData.get(vHolder.getAdapterPosition()).getPhoto());

                    Toast.makeText(parent.getContext(), "Test Click" + String.valueOf(vHolder.getAdapterPosition()), Toast.LENGTH_SHORT).show();
                    myDialog.show();
                }catch (Exception e){
                    System.out.println(e);
                }
            }
        });

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull  final MyViewHolder holder,final int position) {

        holder.tv_name.setText(mData.get(position).getName());
        holder.tv_phone.setText(mData.get(position).getPhone());
       // holder.img.setImageResource(mData.get(position).getPhoto());

//        Glide.with(getActivity()).load(mData.get(position).getPhoto()).into(holder.img);

        holder.mcardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,detailVolunteerList.class);
                intent.putExtra("Image" , mData.get(holder.getAdapterPosition()).getPhone());
                intent.putExtra("Description", mData.get(holder.getAdapterPosition()).getPhone());
                mContext.startActivity(intent);
            }
        });

    }

    private Context getActivity() {
        return mContext;
    }


    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView tv_name;
        private TextView tv_phone;
        private ImageView img;
        private CardView mcardView;
        private LinearLayout item_volunteer;
        private Button mbutton;

        public MyViewHolder(View itemView){
            super(itemView);

//            item_volunteer=(LinearLayout)itemView.findViewById(R.id.volunteer_item);
            tv_name=(TextView) itemView.findViewById(R.id.name_volunteer);
            tv_phone=(TextView) itemView.findViewById(R.id.phone_volunteer);
            img=(ImageView) itemView.findViewById(R.id.img_volunteer);
            mbutton=(Button)itemView.findViewById(R.id.btn_info_volunteer);
            mcardView = (CardView) itemView.findViewById(R.id.mycardViewVol);
        }
    }
}
