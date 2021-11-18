package com.fearmygaze.dsa.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.fearmygaze.dsa.R;
import com.fearmygaze.dsa.model.User;
import com.google.android.material.button.MaterialButton;

public class Files extends Fragment {
    View view;
    private User me;

    public Files(User user){
        this.me = user;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_files, container, false);

        MaterialButton patientAddFiles = view.findViewById(R.id.patientAddFiles);
        RecyclerView patientRecycler = view.findViewById(R.id.patientRecycler);


        return view;
    }
}