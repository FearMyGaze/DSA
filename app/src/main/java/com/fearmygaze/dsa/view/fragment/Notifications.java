package com.fearmygaze.dsa.view.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fearmygaze.dsa.R;
import com.fearmygaze.dsa.view.adapter.AdapterNotification;
import com.google.android.material.button.MaterialButton;

public class Notifications extends Fragment {
    View view;
    AdapterNotification adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_notifications, container, false);

        MaterialButton notificationsRefresh = view.findViewById(R.id.notificationsRefresh);

        RecyclerView notificationRecycler = view.findViewById(R.id.notificationsRecycler);

        SharedPreferences getSharedPrefs = PreferenceManager.getDefaultSharedPreferences(requireActivity().getApplicationContext());

        int prefUserID = getSharedPrefs.getInt("userID",-1);



        return view;
    }
}