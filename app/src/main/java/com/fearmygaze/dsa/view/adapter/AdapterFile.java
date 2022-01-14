package com.fearmygaze.dsa.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fearmygaze.dsa.R;
import com.fearmygaze.dsa.model.File;

import java.util.List;

public class AdapterFile extends RecyclerView.Adapter<AdapterFile.MyViewHolder> {

    List<File> fileList;

    public AdapterFile(List<File> fileList) {
        this.fileList = fileList;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View FileItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_file,parent,false);
        return new MyViewHolder(FileItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String adapterTitle = fileList.get(position).getTitle();
        String adapterDate = fileList.get(position).getDescription();
        System.out.println(adapterDate + adapterTitle);


        holder.adapterTitle.setText(adapterTitle);
        holder.adapterDate.setText(adapterDate);
    }

    @Override
    public int getItemCount() {
        return fileList.size();
    }

    public void setFileList(List<File> fileList) {
        this.fileList = fileList;
    }

    protected static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView adapterTitle, adapterDate;

        public MyViewHolder(@NonNull View view) {
            super(view);
            adapterTitle = view.findViewById(R.id.adapterTitle);
            adapterDate = view.findViewById(R.id.adapterDate);
        }
    }
}
