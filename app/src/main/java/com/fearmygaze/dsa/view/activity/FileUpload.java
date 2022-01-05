package com.fearmygaze.dsa.view.activity;


import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.util.Base64;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.fearmygaze.dsa.R;
import com.fearmygaze.dsa.controller.FileController;
import com.fearmygaze.dsa.custom.UserNotification;
import com.fearmygaze.dsa.model.IVolleyMessage;
import com.fearmygaze.dsa.util.RegEx;
import com.fearmygaze.dsa.util.TextHandler;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Objects;

public class FileUpload extends AppCompatActivity {

    AppCompatImageView imageView;
    MaterialButton fileUploadConfirm;

    String stringConvertedImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.fearmygaze.dsa.R.layout.activity_file_upload);

        TextInputEditText fileUploadTitle = findViewById(R.id.fileUploadTitle);
        TextInputLayout fileUploadTitleError = findViewById(R.id.fileUploadTitleError);

        TextInputEditText fileUploadDesc = findViewById(R.id.fileUploadDesc);
        TextInputLayout fileUploadDescError = findViewById(R.id.fileUploadDescError);

        MaterialButton fileUploadSelectImage = findViewById(R.id.fileUploadSelectImage);
        fileUploadConfirm = findViewById(R.id.fileUploadConfirm);

        imageView = findViewById(R.id.fileUploadImageView);

        SharedPreferences getSharedPrefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());


        /*
         * The moment the TextInputEditText is filled with a text after an error occurred th error
         *   vanishes from the text that was changed
         * */
        fileUploadTitle.addTextChangedListener(new TextHandler(fileUploadTitleError));
        fileUploadDesc.addTextChangedListener(new TextHandler(fileUploadDescError));

        fileUploadSelectImage.setOnClickListener(v -> {
            if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this ,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},100);
            }else{
                selectImage();
            }
        });

        fileUploadConfirm.setOnClickListener(v -> {
            TextHandler.IsTextInputEmpty(fileUploadTitle,fileUploadTitleError,getApplicationContext());
            TextHandler.IsTextInputEmpty(fileUploadDesc,fileUploadDescError,getApplicationContext());

            String userEmail = getSharedPrefs.getString("userEmail","empty");

            if(!fileUploadTitleError.isErrorEnabled() && !fileUploadDescError.isErrorEnabled()){
                if(RegEx.isTextValid(Objects.requireNonNull(fileUploadTitle.getText()).toString(), fileUploadTitleError ,getApplicationContext())
                        && RegEx.isTextValid(Objects.requireNonNull(fileUploadDesc.getText()).toString(),fileUploadDescError,getApplicationContext())
                        && !userEmail.equals("empty")){

                    String uploadTitle = Objects.requireNonNull(fileUploadTitle.getText()).toString();
                    String uploadDesc = Objects.requireNonNull(fileUploadDesc.getText()).toString();
                    String uploadFile = stringConvertedImage; //TODO: Add breakpoint to see the value of the image
                    FileController.fileUpload(getApplicationContext(), userEmail, uploadTitle, uploadDesc, uploadFile, new IVolleyMessage() {
                        @Override
                        public void onWaring(String message) {
                            UserNotification userNotification = new UserNotification(FileUpload.this, v, Snackbar.LENGTH_LONG, Snackbar.ANIMATION_MODE_FADE);
                            userNotification.setOnWarningMsg(message);
                            userNotification.onWarning();
                        }

                        @Override
                        public void onError(String message) {
                            UserNotification userNotification = new UserNotification(FileUpload.this, v, Snackbar.LENGTH_LONG, Snackbar.ANIMATION_MODE_FADE);
                            userNotification.setOnErrorMsg(message);
                            userNotification.onError();
                        }

                        @Override
                        public void onSuccess(String message) {
                            UserNotification userNotification = new UserNotification(FileUpload.this, v, Snackbar.LENGTH_LONG, Snackbar.ANIMATION_MODE_FADE);
                            userNotification.setOnSuccessMsg(message);
                            userNotification.onSuccess();
                        }
                    });
                    
                    System.out.println(uploadFile);
                }
            }
        });

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
        if (requestCode == 100 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            selectImage();
        }else{
            /*
            * TODO: Change it to snackbar or a alertDialog and make the message translatable from strings.xml
            * */
            System.out.println("No permissions");

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if ( requestCode == 100 && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream);

                stringConvertedImage = null; //Clears the string from previous conversions
                byte[] bytes = stream.toByteArray();
                stringConvertedImage = Base64.encodeToString(bytes,Base64.DEFAULT);

                imageView.setImageBitmap(bitmap);
                fileUploadConfirm.setEnabled(true);

            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}