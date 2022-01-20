package com.fearmygaze.dsa.view.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.fearmygaze.dsa.Interface.IVolleyMessage;
import com.fearmygaze.dsa.R;
import com.fearmygaze.dsa.controller.FileController;
import com.fearmygaze.dsa.custom.SnackBar;
import com.fearmygaze.dsa.model.Exam;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import java.io.OutputStream;
import java.util.List;
import java.util.Objects;

public class AdapterFile extends RecyclerView.Adapter<AdapterFile.MyViewHolder> {

    List<Exam> fileList;

    int userID;

    public AdapterFile(List<Exam> fileList, int userID ) {
        this.fileList = fileList;
        this.userID = userID;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View FileItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_file,parent,false);
        return new MyViewHolder(FileItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String adapterTitle = fileList.get(position).getTitle();
        String adapterDate = fileList.get(position).getDate();
        String adapterDesc = fileList.get(position).getDescription();
        int adapterID = fileList.get(position).getId();

        holder.adapterTitle.setText(adapterTitle);
        holder.adapterDate.setText(adapterDate);

        holder.adapterRootLayout.setOnClickListener(v -> {
            FileController.fileImageDownload(v.getContext(), userID, adapterID, new IVolleyMessage() {
                @Override
                public void onWaring(String message) {
                    SnackBar userNotification = new SnackBar((Activity) v.getContext(), v, Snackbar.LENGTH_LONG, Snackbar.ANIMATION_MODE_FADE);
                    userNotification.setOnErrorMsg(message);
                    userNotification.onError();
                }

                @Override
                public void onError(String message) {
                    SnackBar userNotification = new SnackBar((Activity) v.getContext(), v, Snackbar.LENGTH_LONG, Snackbar.ANIMATION_MODE_FADE);
                    userNotification.setOnErrorMsg(message);
                    userNotification.onError();
                }

                @Override
                public void onSuccess(String message) {
                    Dialog dialog = new Dialog(v.getContext());
                    dialog.setContentView(R.layout.custom_file_dialog);
                    dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

                    TextInputEditText dialogTitle = dialog.findViewById(R.id.dialogTitle);
                    TextInputEditText dialogDesc = dialog.findViewById(R.id.dialogDesc);

                    AppCompatImageView dialogImage = dialog.findViewById(R.id.dialogImageView);

                    MaterialButton dialogDownload = dialog.findViewById(R.id.dialogDownload);
                    MaterialButton dialogDelete = dialog.findViewById(R.id.dialogDelete);

                    dialogTitle.setText(adapterTitle);
                    dialogDesc.setText(adapterDesc);

                    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
                        dialogDownload.setEnabled(false);
                    }

                    //This is for decoding the string to image
                    byte[] decode = Base64.decode(message, Base64.DEFAULT);
                    Bitmap image = BitmapFactory.decodeByteArray(decode, 0, decode.length);
                    dialogImage.setImageBitmap(image);

                    dialogDownload.setOnClickListener(v2 -> {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                            saveImage(image, (Activity) v.getContext(), v);
                            dialog.dismiss();
                        }
                    });

                    dialogDelete.setOnClickListener(v3 -> {
                        FileController.fileDelete(v3.getContext(), userID, adapterID, new IVolleyMessage() {
                            @Override
                            public void onWaring(String message) {
                                SnackBar userNotification = new SnackBar((Activity) v.getContext(), v, Snackbar.LENGTH_LONG, Snackbar.ANIMATION_MODE_FADE);
                                userNotification.setOnErrorMsg(message);
                                userNotification.onError();
                                dialog.dismiss();
                            }

                            @Override
                            public void onError(String message) {
                                SnackBar userNotification = new SnackBar((Activity) v.getContext(), v, Snackbar.LENGTH_LONG, Snackbar.ANIMATION_MODE_FADE);
                                userNotification.setOnWarningMsg(message);
                                userNotification.onWarning();
                                dialog.dismiss();
                            }

                            @Override
                            public void onSuccess(String message) {
                                SnackBar userNotification = new SnackBar((Activity) v.getContext(), v, Snackbar.LENGTH_LONG, Snackbar.ANIMATION_MODE_FADE);
                                userNotification.setOnSuccessMsg(message);
                                userNotification.onSuccess();
                                dialog.dismiss();
                            }
                        });
                    });


                    dialog.show();
                }
            });
        });
    }

    @Override
    public int getItemCount() {
        return fileList.size();
    }

    public void setFileList(List<Exam> fileList) {
        this.fileList = fileList;
    }

    protected static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView adapterTitle, adapterDate;
        ConstraintLayout adapterRootLayout;

        public MyViewHolder(@NonNull View view) {
            super(view);
            adapterRootLayout = view.findViewById(R.id.adapterFileRootLayout);
            adapterTitle = view.findViewById(R.id.adapterFileTitle);
            adapterDate = view.findViewById(R.id.adapterFileDate);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    private void saveImage(Bitmap bitmap, Activity activity, View view){
        OutputStream outputStream;
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                ContentResolver resolver = activity.getContentResolver();
                ContentValues values = new ContentValues();
                values.put(MediaStore.MediaColumns.DISPLAY_NAME, System.currentTimeMillis() + ".png");
                values.put(MediaStore.MediaColumns.MIME_TYPE, "image/png");
                values.put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES);
                Uri imageUri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
                outputStream = resolver.openOutputStream(Objects.requireNonNull(imageUri));
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
                Objects.requireNonNull(outputStream);

                SnackBar userNotification = new SnackBar(activity, view, Snackbar.LENGTH_LONG, Snackbar.ANIMATION_MODE_FADE);
                userNotification.setOnSuccessMsg(view.getContext().getResources().getString(R.string.successImageSave));
                userNotification.onSuccess();
            }
        }catch (Exception e){
            Toast.makeText(activity, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }


}
