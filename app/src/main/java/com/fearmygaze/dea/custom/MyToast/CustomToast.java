package com.fearmygaze.dea.custom.MyToast;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatImageView;

import com.fearmygaze.dea.R;

public class CustomToast implements ICustomToast{
    private final Toast toast;
    private final TextView textView;
    private String onErrorMsg;
    private String onWarningMsg;
    private String onSuccessMsg;


    public CustomToast (Activity activity, String onErrorMsg , String onSuccessMsg , String onWarningMsg){
        @SuppressLint("InflateParams") View view = activity.getLayoutInflater().inflate(R.layout.custom_toast, (ViewGroup) activity.findViewById(R.id.rootLayout));
        textView = view.findViewById(R.id.CustomToast);
        toast = new Toast(activity);
        toast.setView(view);
        this.onErrorMsg = onErrorMsg;
        this.onSuccessMsg = onSuccessMsg;
        this.onWarningMsg = onWarningMsg;
    }

    public CustomToast(Activity activity){
        @SuppressLint("InflateParams") View view = activity.getLayoutInflater().inflate(R.layout.custom_toast, (ViewGroup) activity.findViewById(R.id.rootLayout));
        textView = view.findViewById(R.id.CustomToast);
        toast = new Toast(activity);
        toast.setGravity(Gravity.TOP,0,0);
        toast.setView(view);
    }

    @Override
    public void onError() {
        /*
        * Add stuff for onError Toast
        * */
        textView.setText(onErrorMsg);
        textView.setTextColor(Color.rgb(1,1,1));
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();
    }

    public void onWarning(){
        /*
         * Add stuff for onWarning Toast
         * */
        textView.setText(onWarningMsg);
        textView.setTextColor(Color.rgb(10,10,10));
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();
    }

    public void onSuccess(){
        /*
         * Add stuff for onSuccess Toast
         * */
        textView.setText(onSuccessMsg);
        textView.setTextColor(Color.rgb(10,10,10));
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();
    }


    public void setOnErrorMsg(String onErrorMsg) {
        this.onErrorMsg = onErrorMsg;
    }

    public void setOnSuccessMsg(String onSuccessMsg) {
        this.onSuccessMsg = onSuccessMsg;
    }

    public void setOnWarningMsg(String onWarningMsg) {
        this.onWarningMsg = onWarningMsg;
    }

    public Toast getToast() {
        return toast;
    }
}
