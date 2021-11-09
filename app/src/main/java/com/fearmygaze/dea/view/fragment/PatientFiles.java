package com.fearmygaze.dea.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.fearmygaze.dea.R;
import com.google.android.material.button.MaterialButton;

public class PatientFiles extends Fragment {
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_patient_files, container, false);

        MaterialButton patientAddFiles = view.findViewById(R.id.patientAddFiles);
        RecyclerView patientRecycler = view.findViewById(R.id.patientRecycler);


        return view;
    }
}