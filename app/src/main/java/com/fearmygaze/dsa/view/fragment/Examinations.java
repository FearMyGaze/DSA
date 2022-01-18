package com.fearmygaze.dsa.view.fragment;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fearmygaze.dsa.Interface.IImage;
import com.fearmygaze.dsa.R;
import com.fearmygaze.dsa.controller.FileController;
import com.fearmygaze.dsa.custom.UserNotification;
import com.fearmygaze.dsa.model.Exam;
import com.fearmygaze.dsa.view.adapter.AdapterFile;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class Examinations extends Fragment {

    View view;
    AdapterFile adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_files, container, false);

        MaterialButton filesRefresh = view.findViewById(R.id.filesRefresh);

        RecyclerView filesRecycler = view.findViewById(R.id.filesRecycler);

        SharedPreferences getSharedPrefs = PreferenceManager.getDefaultSharedPreferences(requireActivity().getApplicationContext());

        List<Exam> fileList = new ArrayList<>();

        int prefUserID = getSharedPrefs.getInt("userID",-1);

        adapter = new AdapterFile(fileList,prefUserID);;

        fetchFiles(prefUserID);

        filesRefresh.setOnClickListener(v -> {
            fetchFiles(prefUserID); //This is for refreshing the list
        });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        filesRecycler.setLayoutManager(layoutManager);
        filesRecycler.setAdapter(adapter);

        return view;
    }

    void fetchFiles(int prefUserID){
        if (prefUserID > -1){
            FileController.fileFetch(requireContext(), prefUserID, new IImage() {
                @SuppressLint("NotifyDataSetChanged")
                @Override
                public void getFileList(List<Exam> fileList) {
                    adapter.setFileList(fileList);
                    adapter.notifyDataSetChanged();
                }


                @Override
                public void onError(String message) {
                    UserNotification userNotification = new UserNotification(requireActivity(), view, Snackbar.LENGTH_LONG, Snackbar.ANIMATION_MODE_FADE);
                    userNotification.setOnErrorMsg(message);
                    userNotification.onError();
                }
            });
        }
    }
}