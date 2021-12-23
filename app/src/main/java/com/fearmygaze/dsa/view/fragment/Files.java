package com.fearmygaze.dsa.view.fragment;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.fearmygaze.dsa.R;
import com.fearmygaze.dsa.model.User;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.hbisoft.pickit.PickiT;
import com.hbisoft.pickit.PickiTCallbacks;

import java.util.ArrayList;

public class Files extends Fragment {
    View view;
    private final User me;

    public Files(User user) {
        this.me = user;
    }

    PickiT pickiT;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_files, container, false);


        RecyclerView filesRecycler = view.findViewById(R.id.filesRecycler);
        FloatingActionButton floatingActionButton =view.findViewById(R.id.filesAdd);


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickiT = new PickiT(view.getContext(), new PickiTCallbacks() {
                    @Override
                    public void PickiTonUriReturned() {

                    }

                    @Override
                    public void PickiTonStartListener() {

                    }

                    @Override
                    public void PickiTonProgressUpdate(int progress) {

                    }

                    @Override
                    public void PickiTonCompleteListener(String path, boolean wasDriveFile, boolean wasUnknownProvider, boolean wasSuccessful, String Reason) {

                    }

                    @Override
                    public void PickiTonMultipleCompleteListener(ArrayList<String> paths, boolean wasSuccessful, String Reason) {

                    }
                }, requireActivity());
            }
        });



        return view;
    }
}