package com.fearmygaze.dsa.view.fragment;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.fearmygaze.dsa.R;
import com.fearmygaze.dsa.model.User;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.button.MaterialButton;

import java.io.File;

public class Files extends Fragment {
    View view;
    int requestCode = 1;
    private User me;

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
                openFileChooser();
                Toast.makeText(requireActivity(), "TOAST", Toast.LENGTH_SHORT).show();
            });

            bottomSheetView.findViewById(R.id.bottomSheetUploadPDF).setOnClickListener(v2 -> {

            });

            bottomSheetView.findViewById(R.id.bottomSheetCreateTxtFile).setOnClickListener(v3 -> {
                /*
                 * TODO: Create a txt file and the upload it
                 * */
            });

            bottomSheetDialog.setContentView(bottomSheetView);
            bottomSheetDialog.show();
        });
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == this.requestCode && resultCode == Activity.RESULT_OK) {
            if (data == null) {
                return;
            }
            Uri uri = data.getData();
            File file = new File(uri.getPath());
            final String path = file.getAbsolutePath();
            Toast.makeText(requireActivity(), path, Toast.LENGTH_SHORT).show();
        }
    }

    public void openFileChooser() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        startActivityForResult(intent, requestCode);
    }
}