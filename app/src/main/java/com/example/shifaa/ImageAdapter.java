package com.example.shifaa;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {

    private Context contect;
    private List<User2> Uploads;

    public ImageAdapter(Context mcontect , List<User2> mUploads){
           contect = mcontect ;
           Uploads = mUploads ;
    }
    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(contect).inflate(R.layout.list_item2,parent,false);
        return new ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
           User2 upLoadCurrent = Uploads.get(position);
           holder.nameOfDrag.setText(upLoadCurrent.getTupeOfDrag1().toString());
           holder.SalryOfDrag.setText(upLoadCurrent.getSaleOfDrag1().toString());

        Picasso.get()
                .load(upLoadCurrent.getImgUri1())
                .placeholder(R.mipmap.ic_launcher)
                .fit()
                .centerCrop()
                .into(holder.imgDrag);
    }

    @Override
    public int getItemCount() {
        return Uploads.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder{

        public TextView nameOfDrag ;
        public TextView SalryOfDrag ;
        public ImageView imgDrag ;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);

            nameOfDrag = (TextView) itemView.findViewById(R.id.nameDrag_m);
            SalryOfDrag = (TextView) itemView.findViewById(R.id.salary_m);
            imgDrag = (ImageView) itemView.findViewById(R.id.imagpha_m);

        }
    }
}
