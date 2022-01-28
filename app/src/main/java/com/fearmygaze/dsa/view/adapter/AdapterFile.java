package com.fearmygaze.dsa.view.adapter;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.pm.PackageManager;
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
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.fearmygaze.dsa.Interface.IVolleyMessage;
import com.fearmygaze.dsa.R;
import com.fearmygaze.dsa.controller.FileController;
import com.fearmygaze.dsa.custom.SnackBar;
import com.fearmygaze.dsa.model.Exam;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;

import java.io.File;
import java.io.FileOutputStream;
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
        int adapterID       = fileList.get(position).getId();
        String adapterTitle = fileList.get(position).getTitle();
        String adapterDesc  = fileList.get(position).getDescription();
        String adapterDate  = fileList.get(position).getDate();

        holder.adapterTitle.setText(adapterTitle);
        holder.adapterDesc.setText(adapterDesc);
        holder.adapterDate.setText(adapterDate);

        holder.adapterDelete.setOnClickListener(v1 -> FileController.fileDelete(v1.getContext(), userID, adapterID, new IVolleyMessage() {
            @Override
            public void onWaring(String message) {
                SnackBar userNotification = new SnackBar((Activity) v1.getContext(), v1, Snackbar.LENGTH_LONG, Snackbar.ANIMATION_MODE_FADE);
                userNotification.setOnWarningMsg(message);
                userNotification.onWarning();
            }

            @Override
            public void onError(String message) {
                SnackBar userNotification = new SnackBar((Activity) v1.getContext(), v1, Snackbar.LENGTH_LONG, Snackbar.ANIMATION_MODE_FADE);
                userNotification.setOnErrorMsg(message);
                userNotification.onError();
            }

            @Override
            public void onSuccess(String message) {
                SnackBar userNotification = new SnackBar((Activity) v1.getContext(), v1, Snackbar.LENGTH_LONG, Snackbar.ANIMATION_MODE_FADE);
                userNotification.setOnSuccessMsg(message);
                userNotification.onSuccess();
                fileList.remove(holder.getAdapterPosition());
                notifyItemRemoved(holder.getAdapterPosition());
            }
        }));

        holder.adapterDownload.setOnClickListener(v2 -> FileController.fileImageDownload(v2.getContext(), userID, adapterID, new IVolleyMessage() {
            @Override
            public void onWaring(String message) {
                SnackBar userNotification = new SnackBar((Activity) v2.getContext(), v2, Snackbar.LENGTH_LONG, Snackbar.ANIMATION_MODE_FADE);
                userNotification.setOnErrorMsg(message);
                userNotification.onError();
            }

            @Override
            public void onError(String message) {
                SnackBar userNotification = new SnackBar((Activity) v2.getContext(), v2, Snackbar.LENGTH_LONG, Snackbar.ANIMATION_MODE_FADE);
                userNotification.setOnErrorMsg(message);
                userNotification.onError();
            }

            @Override
            public void onSuccess(String message) {
                byte[] decode = Base64.decode(message, Base64.DEFAULT); //This is for decoding the string to image
                Bitmap image = BitmapFactory.decodeByteArray(decode, 0, decode.length);
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q){
                    if(ContextCompat.checkSelfPermission(v2.getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                        ActivityCompat.requestPermissions((Activity) v2.getContext() ,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},99);
                    }else{
                        saveImage(image,(Activity) v2.getContext(),v2);
                    }
                }else{//This is for API Q and greater
                    saveImage(image, (Activity) v2.getContext(), v2);
                }
            }
        }));

    }

    @Override
    public int getItemCount() {
        return fileList.size();
    }

    public void setFileList(List<Exam> fileList) {
        this.fileList = fileList;
    }

    protected static class MyViewHolder extends RecyclerView.ViewHolder{
        ConstraintLayout adapterRootLayout;
        TextView adapterTitle, adapterDesc, adapterDate;
        MaterialButton adapterDelete, adapterDownload;

        public MyViewHolder(@NonNull View view) {
            super(view);
            adapterRootLayout   = view.findViewById(R.id.adapterFileRootLayout);

            adapterTitle        = view.findViewById(R.id.adapterFileTitle);
            adapterDesc         = view.findViewById(R.id.adapterFileDesc);
            adapterDate         = view.findViewById(R.id.adapterFileDate);

            adapterDelete       = view.findViewById(R.id.adapterFileDelete);
            adapterDownload     = view.findViewById(R.id.adapterFileDownload);
        }
    }

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
            }else{ //I cant test if that works or not
                File myDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString());
                myDir.mkdirs();
                String fileName = System.currentTimeMillis()+".png";
                File file = new File(myDir, fileName);
                try {
                    FileOutputStream out = new FileOutputStream(file);
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
                    out.flush();
                    out.close();
                    Toast.makeText(activity, "IMAGE SAVED", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(activity, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        }catch (Exception e){
            Toast.makeText(activity, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
