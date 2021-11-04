package com.fearmygaze.dea.custom;


import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatImageView;

import com.fearmygaze.dea.R;
import com.google.android.material.snackbar.Snackbar;

public class CustomSnackBar{

    /*
     * TODO: Maybe i can make one with action and picture and one without action
     * */

    public static void onSnackBar(View view , String text , int length , int color){
        Snackbar snackbar = Snackbar
                .make(view, text, length)
                .setActionTextColor(color);
        snackbar.show();
    }

    public static void onModifiedSnackBar(View view, Activity activity, String text , int length , int imageID){
        Snackbar snackbar = Snackbar.make(view, "", length);

        View view1 = activity.getLayoutInflater().inflate(R.layout.custom_snackbar, activity.findViewById(R.id.snackBarRootLayout));
        TextView textView = view1.findViewById(R.id.customSnackBarTextView);
        AppCompatImageView appCompatImageView = view1.findViewById(R.id.customSnackBarImage);

        snackbar.getView().setBackgroundColor(Color.TRANSPARENT);
        Snackbar.SnackbarLayout snackbarLayout = (Snackbar.SnackbarLayout) snackbar.getView();
        snackbarLayout.setPadding(0,0,0,0);

        textView.setText(text);
        appCompatImageView.setImageResource(imageID);

        snackbarLayout.addView(view1,0);
        snackbar.show();
    }


}
