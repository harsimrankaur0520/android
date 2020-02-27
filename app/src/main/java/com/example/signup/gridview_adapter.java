package com.example.signup;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;

public class gridview_adapter extends BaseAdapter {

    private Context mcontext;
    public  int[] discountArray = {R.drawable.travel, R.drawable.food,
            R.drawable.fashion, R.drawable.hotel,
            R.drawable.medi,R.drawable.grocery

    };

    public gridview_adapter(Context mcontext) {
        this.mcontext = mcontext;
    }

    @Override
    public int getCount() {
        return discountArray.length;
    }

    @Override
    public Object getItem(int i) {
        return discountArray[i];
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ImageView imageView = new ImageView(mcontext);
        imageView.setImageResource(discountArray[i]);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setLayoutParams(new GridView.LayoutParams(340, 350));



        return imageView;
    }
}
