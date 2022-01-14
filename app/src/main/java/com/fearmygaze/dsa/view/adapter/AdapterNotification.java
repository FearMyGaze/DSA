package com.fearmygaze.dsa.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.fearmygaze.dsa.R;
import com.google.android.material.button.MaterialButton;

public class AdapterNotification extends RecyclerView.Adapter<AdapterNotification.MyViewHolder> {
    @NonNull
    @Override
    public AdapterNotification.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View notificationView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_notfication,parent,false);
        return new MyViewHolder(notificationView);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterNotification.MyViewHolder holder, int position) {
        //TODO: FIll the space
        String doctorName = "This is a name";

        holder.adapterName.setText(doctorName);
        holder.adapterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    protected  static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView adapterName , adapterGrantAccess;
        MaterialButton adapterButton;
        ConstraintLayout adapterRootLayout;

        public MyViewHolder(@NonNull View view) {
            super(view);
            adapterRootLayout = view.findViewById(R.id.adapterNotificationRootLayout);
            adapterGrantAccess = view.findViewById(R.id.adapterNotificationGrantAccess);
            adapterName = view.findViewById(R.id.adapterTitle);
            adapterButton = view.findViewById(R.id.adapterNotificationButton);
        }
    }
}
