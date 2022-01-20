package com.fearmygaze.dsa.view.fragment;

import static android.app.Activity.RESULT_OK;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;

import com.fearmygaze.dsa.Interface.IVolleyMessage;
import com.fearmygaze.dsa.R;
import com.fearmygaze.dsa.controller.FileController;
import com.fearmygaze.dsa.custom.SnackBar;
import com.fearmygaze.dsa.util.TextHandler;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Objects;

public class Upload extends Fragment {
    View view;

    AppCompatImageView imageView;
    MaterialButton uploadToServer;

    String stringConvertedImage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_upload, container, false);

        TextInputLayout uploadTitleError = view.findViewById(R.id.uploadTitleError);
        TextInputEditText uploadTitle = view.findViewById(R.id.uploadTitle);

        TextInputLayout uploadDescError = view.findViewById(R.id.uploadDescError);
        TextInputEditText uploadDesc = view.findViewById(R.id.uploadDesc);

        imageView = view.findViewById(R.id.uploadImageView);

        MaterialButton uploadChooseImage = view.findViewById(R.id.uploadSelectImage);
        uploadToServer = view.findViewById(R.id.uploadConfirm);

        SharedPreferences getSharedPrefs = PreferenceManager.getDefaultSharedPreferences(view.getContext().getApplicationContext());

        /*
         * The moment the TextInputEditText is filled with a text after an error occurred th error
         *   vanishes from the text that was changed
         * */
        uploadTitle.addTextChangedListener(new TextHandler(uploadTitleError));
        uploadDesc.addTextChangedListener(new TextHandler(uploadDescError));

        uploadChooseImage.setOnClickListener(v -> {
            if(ContextCompat.checkSelfPermission(v.getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(requireActivity() ,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},100);
            }else{
                selectImage();
            }
        });

        uploadToServer.setOnClickListener(v -> {
            TextHandler.IsTextInputEmpty(uploadTitle,uploadTitleError,v.getContext().getApplicationContext());
            TextHandler.IsTextInputEmpty(uploadDesc,uploadDescError,v.getContext().getApplicationContext());

            int userID = getSharedPrefs.getInt("userID",-1);

            if(!uploadTitleError.isErrorEnabled() && !uploadDescError.isErrorEnabled()){
                if(TextHandler.isSmallerThanSetLength(Objects.requireNonNull(uploadTitle.getText()).toString(),40 ,uploadTitleError ,v.getContext().getApplicationContext())
                        && TextHandler.isSmallerThanSetLength(Objects.requireNonNull(uploadDesc.getText()).toString(),100 ,uploadDescError,v.getContext().getApplicationContext())
                        && userID > -1){

                    String toUploadTitle = Objects.requireNonNull(uploadTitle.getText()).toString();
                    String toUploadDesc = Objects.requireNonNull(uploadDesc.getText().toString());
                    String uploadFile = stringConvertedImage;

                    uploadToServer.setEnabled(false);

                    FileController.fileUpload(v.getContext().getApplicationContext(), userID, toUploadTitle, toUploadDesc, uploadFile, new IVolleyMessage() {
                        @Override
                        public void onWaring(String message) {
                            SnackBar userNotification = new SnackBar(requireActivity(), v, Snackbar.LENGTH_LONG, Snackbar.ANIMATION_MODE_FADE);
                            userNotification.setOnWarningMsg(message);
                            userNotification.onWarning();
                        }

                        @Override
                        public void onError(String message) {
                            SnackBar userNotification = new SnackBar(requireActivity(), v, Snackbar.LENGTH_LONG, Snackbar.ANIMATION_MODE_FADE);
                            userNotification.setOnErrorMsg(message);
                            userNotification.onError();
                        }

                        @Override
                        public void onSuccess(String message) {
                            SnackBar userNotification = new SnackBar(requireActivity(), v, Snackbar.LENGTH_LONG, Snackbar.ANIMATION_MODE_FADE);
                            userNotification.setOnSuccessMsg(message);
                            userNotification.onSuccess();

                            uploadTitle.setText("");
                            uploadDesc.setText("");
                            imageView.setImageBitmap(null);

                            uploadToServer.setEnabled(true);

                        }
                    });
                }
            }
        });

        return view;
    }

    private void selectImage() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE,false);
        startActivityForResult(intent,100);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        String message = getResources().getString(R.string.noPermission);
        if (requestCode == 100 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            selectImage();
        }else{
            Toast.makeText(view.getContext(), message, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(view.getContext().getContentResolver(),uri);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream);

                stringConvertedImage = null; //Clears the string from previous conversions
                byte[] bytes = stream.toByteArray();
                stringConvertedImage = Base64.encodeToString(bytes,Base64.DEFAULT);

                imageView.setImageBitmap(bitmap);
                uploadToServer.setEnabled(true);

            } catch (IOException e) {
                Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            }
        }
    }
}