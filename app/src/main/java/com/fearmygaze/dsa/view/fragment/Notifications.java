package com.fearmygaze.dsa.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.fragment.app.Fragment;

import com.fearmygaze.dsa.R;
import com.fearmygaze.dsa.model.User;

public class Notifications extends Fragment {
    View view;

    private User me;

    public Notifications(User user){
        this.me = user;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_notifications, container, false);






        return view;
    }
}