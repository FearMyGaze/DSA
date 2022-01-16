package com.fearmygaze.dsa.view.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.fearmygaze.dsa.R;
import com.fearmygaze.dsa.controller.FileController;
import com.fearmygaze.dsa.custom.UserNotification;
import com.fearmygaze.dsa.model.File;
import com.fearmygaze.dsa.model.IVolleyMessage;
import com.fearmygaze.dsa.view.fragment.Files;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class AdapterFile extends RecyclerView.Adapter<AdapterFile.MyViewHolder> {

    List<File> fileList;

    int userID;

    public AdapterFile(List<File> fileList, int userID ) {
        this.fileList = fileList;
        this.userID = userID;
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
        String adapterDate = fileList.get(position).getDate();
        int adapterID = fileList.get(position).getId();

        holder.adapterTitle.setText(adapterTitle);
        holder.adapterDate.setText(adapterDate);

        holder.adapterRootLayout.setOnClickListener(v -> {
            FileController.fileImageDownload(v.getContext(), userID, adapterID, new IVolleyMessage() {
                @Override
                public void onWaring(String message) {
                    UserNotification userNotification = new UserNotification((Activity) v.getContext(), v, Snackbar.LENGTH_LONG, Snackbar.ANIMATION_MODE_FADE);
                    userNotification.setOnErrorMsg(message);
                    userNotification.onError();
                }

                @Override
                public void onError(String message) {
                    UserNotification userNotification = new UserNotification((Activity) v.getContext(), v, Snackbar.LENGTH_LONG, Snackbar.ANIMATION_MODE_FADE);
                    userNotification.setOnErrorMsg(message);
                    userNotification.onError();
                }

                @Override
                public void onSuccess(String message) {

                }
            });
        });

        holder.adapterRootLayout.setOnLongClickListener(v -> {
            Toast.makeText(v.getContext(), "Long Press", Toast.LENGTH_SHORT).show();
            return true;
        });
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
        ConstraintLayout adapterRootLayout;

        public MyViewHolder(@NonNull View view) {
            super(view);
            adapterRootLayout = view.findViewById(R.id.adapterFileRootLayout);
            adapterTitle = view.findViewById(R.id.adapterFileTitle);
            adapterDate = view.findViewById(R.id.adapterFileDate);
        }
    }
}
