package com.fearmygaze.dea.custom.MySnackBar;


import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

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
        //View snackView = snackbar.getView();
        //TextView textView = snackView.findViewById(R.id.snackbar_text);
        //textView.setTextColor(Color.YELLOW);
        snackbar.show();
    }


}
