package com.fearmygaze.dsa.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.fearmygaze.dsa.R;

public class File extends RecyclerView.Adapter<File.MyViewHolder> {


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View FileItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_file,parent,false);
        return new MyViewHolder(FileItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String adapterTitle = "asd";
        String adapterDesc = "asd";


        holder.adapterTitle.setText(adapterTitle);
        holder.adapterDesc.setText(adapterDesc);

        holder.adapterRootLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: This will be to remove files POSSIBLY or Show an AlertDialog with every information
            }
        });
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    protected  static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView adapterTitle, adapterDesc;
        ConstraintLayout adapterRootLayout;

        public MyViewHolder(@NonNull View view) {
            super(view);
            adapterRootLayout = view.findViewById(R.id.adapterRootLayout);
            adapterTitle = view.findViewById(R.id.adapterTitle);
            adapterDesc = view.findViewById(R.id.adapterDate);
        }
    }
}
