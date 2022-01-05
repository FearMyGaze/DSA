package com.fearmygaze.dsa.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.fearmygaze.dsa.R;
import com.fearmygaze.dsa.model.User;
import com.fearmygaze.dsa.view.activity.FileUpload;
import com.fearmygaze.dsa.view.activity.Main;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Files extends Fragment {

    View view;
    private final User me;

    public Files(User user) {
        this.me = user;
    } //TODO: Maybe remove it because it is not in need

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_files, container, false);

        RecyclerView filesRecycler = view.findViewById(R.id.filesRecycler); //TODO: This will be filled by the server
        FloatingActionButton floatingActionButton =view.findViewById(R.id.filesAdd);


        floatingActionButton.setOnClickListener(v -> {

            Intent intent = new Intent(requireActivity(), FileUpload.class);
            //intent.putExtra("User", me); TODO: Remove if unneeded
            requireActivity().startActivity(intent);
            requireActivity().finish();
        });

        return view;
    }
}