package com.fearmygaze.dsa.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.fearmygaze.dsa.R;
import com.fearmygaze.dsa.model.User;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.button.MaterialButton;

public class Files extends Fragment {
    View view;
    private final User me;

    public Files(User user) {
        this.me = user;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_files, container, false);

        MaterialButton filesAdd = view.findViewById(R.id.filesAdd);
        RecyclerView filesRecycler = view.findViewById(R.id.filesRecycler);

        filesAdd.setOnClickListener(v -> {
            BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(requireActivity(), R.style.BottomSheetDialogTheme);
            View bottomSheetView = LayoutInflater.from(getContext()).inflate(R.layout.bottom_sheet_dialog, view.findViewById(R.id.bottomSheetContainer));

            bottomSheetView.findViewById(R.id.bottomSheetUploadPicture).setOnClickListener(v1 -> {

            });

            bottomSheetView.findViewById(R.id.bottomSheetUploadPDF).setOnClickListener(v2 -> {

            });

            bottomSheetDialog.setContentView(bottomSheetView);
            bottomSheetDialog.show();
        });
        return view;
    }

}