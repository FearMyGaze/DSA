package com.fearmygaze.dsa.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.fearmygaze.dsa.R;
import com.fearmygaze.dsa.model.Notification;
import com.google.android.material.button.MaterialButton;

import java.util.List;

public class AdapterNotification extends RecyclerView.Adapter<AdapterNotification.MyViewHolder> {

    List<Notification> notificationList;

    int userID;

    public AdapterNotification(List<Notification> notificationList, int userID ) {
        this.notificationList = notificationList;
        this.userID = userID;
    }

    @NonNull
    @Override
    public AdapterNotification.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View notificationView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_notfication,parent,false);
        return new MyViewHolder(notificationView);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterNotification.MyViewHolder holder, int position) {
        String docName      = notificationList.get(position).getDocName();
        String fileTitle    = notificationList.get(position).getFileTitle();
        String date         = notificationList.get(position).getDate();

        holder.adapterDocName.setText(docName);
        holder.adapterFileTitle.setText(fileTitle);
        holder.adapterDate.setText(date);

        holder.adapterGrantAccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return notificationList.size();
    }

    public void setNotificationList(List<Notification> notificationList) {
        this.notificationList = notificationList;
    }

    protected  static class MyViewHolder extends RecyclerView.ViewHolder{
        ConstraintLayout adapterRootLayout;
        TextView adapterDocName , adapterFileTitle, adapterDate;
        MaterialButton adapterGrantAccess;

        public MyViewHolder(@NonNull View view) {
            super(view);
            adapterRootLayout   = view.findViewById(R.id.adapterNotificationRootLayout);

            adapterDocName      = view.findViewById(R.id.adapterNotificationDoctorsName);
            adapterFileTitle    = view.findViewById(R.id.adapterNotificationFileTitle);
            adapterDate         = view.findViewById(R.id.adapterNotificationDate);

            adapterGrantAccess  = view.findViewById(R.id.adapterNotificationGrantAccess);

        }
    }
}
