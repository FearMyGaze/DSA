package com.fearmygaze.dsa.custom;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatImageView;

import com.fearmygaze.dsa.R;
import com.google.android.material.snackbar.Snackbar;

public class UserNotification implements IUserNotification {

    private final Snackbar snackbar;
    private final AppCompatImageView snackbarImageView;
    private final TextView snackbarTextView;
    private String onSuccessMsg;
    private String onWarningMsg;
    private String onErrorMsg;

    public UserNotification(Activity activity, View view, int length, int animation) {
        snackbar = Snackbar.make(view, "", length);
        @SuppressLint("InflateParams") View customSnackView = activity.getLayoutInflater().inflate(R.layout.custom_snackbar, null);
        snackbarTextView = customSnackView.findViewById(R.id.customSnackBarTextView);
        snackbarImageView = customSnackView.findViewById(R.id.customSnackBarImage);
        this.snackbar.getView().setBackgroundColor(Color.TRANSPARENT);
        this.snackbar.setAnimationMode(animation);
        Snackbar.SnackbarLayout snackbarLayout = (Snackbar.SnackbarLayout) snackbar.getView();
        snackbarLayout.setPadding(0, 0, 0, 0);
        snackbarLayout.addView(customSnackView, 0);
    }

    public UserNotification(Activity activity, View view, int length , int animation, Boolean b){
        snackbar = Snackbar.make(view, "", length);
        @SuppressLint("InflateParams") View customSnackView = activity.getLayoutInflater().inflate(R.layout.custom_snackbar, null);
        snackbarTextView = customSnackView.findViewById(R.id.customSnackBarTextView);
        snackbarImageView = customSnackView.findViewById(R.id.customSnackBarImage);
        this.snackbar.getView().setBackgroundColor(Color.TRANSPARENT);
        this.snackbar.setAnimationMode(animation);
        Snackbar.SnackbarLayout snackbarLayout = (Snackbar.SnackbarLayout) snackbar.getView();
        snackbarLayout.setPadding(0, 0, 0, 0);
        snackbarLayout.addView(customSnackView, 0);
    }

    @Override
    public void onSuccess() {
        this.snackbarImageView.setImageResource(R.drawable.ic_outline_check_24);
        this.snackbarTextView.setText(onSuccessMsg);
        this.snackbarTextView.setTextColor(Color.rgb(255, 255, 255));
        this.snackbar.show();
    }

    @Override
    public void onWarning() {
        this.snackbarImageView.setImageResource(R.drawable.ic_outline_warning_amber_24);
        this.snackbarTextView.setText(onWarningMsg);
        this.snackbarTextView.setTextColor(Color.rgb(255, 255, 255));
        this.snackbar.show();
    }

    @Override
    public void onError() {
        this.snackbarImageView.setImageResource(R.drawable.ic_outline_error_outline_24);
        this.snackbarTextView.setText(onErrorMsg);
        this.snackbarTextView.setTextColor(Color.rgb(255, 255, 255));
        this.snackbar.show();
    }

    public void setOnSuccessMsg(String onSuccessMsg) {
        this.onSuccessMsg = onSuccessMsg;
    }

    public void setOnWarningMsg(String onWarningMsg) {
        this.onWarningMsg = onWarningMsg;
    }

    public void setOnErrorMsg(String onErrorMsg) {
        this.onErrorMsg = onErrorMsg;
    }

    public Snackbar getSnackbar() {
        return snackbar;
    }
}
