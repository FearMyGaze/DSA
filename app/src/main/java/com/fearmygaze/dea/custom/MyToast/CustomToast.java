package com.fearmygaze.dea.custom.MyToast;

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
    private final AppCompatImageView appCompatImageView;
    private String onErrorMsg;
    private String onWarningMsg;
    private String onSuccessMsg;


    public CustomToast (Activity activity, String onErrorMsg , String onSuccessMsg , String onWarningMsg){
        View view = activity.getLayoutInflater().inflate(R.layout.custom_toast, (ViewGroup) activity.findViewById(R.id.rootLayout));
        textView = view.findViewById(R.id.customToastTextView);
        appCompatImageView = view.findViewById(R.id.customToastImage);
        toast = new Toast(activity);
        toast.setView(view); //TODO: This will become deprecated with 31 API
        this.onErrorMsg = onErrorMsg;
        this.onSuccessMsg = onSuccessMsg;
        this.onWarningMsg = onWarningMsg;
    }

    public CustomToast(Activity activity){
        View view = activity.getLayoutInflater().inflate(R.layout.custom_toast, (ViewGroup) activity.findViewById(R.id.rootLayout));
        textView = view.findViewById(R.id.customToastTextView);
        appCompatImageView = view.findViewById(R.id.customToastImage);
        toast = new Toast(activity);
        toast.setGravity(Gravity.TOP|Gravity.CENTER,0,0);
        toast.setView(view);
    }

    @Override
    public void onError() {
        textView.setText(onErrorMsg);
        textView.setTextColor(Color.rgb(1,1,1));
        appCompatImageView.setImageResource(R.drawable.ic_outline_error_outline_24);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();
    }

    public void onWarning(){
        textView.setText(onWarningMsg);
        textView.setTextColor(Color.rgb(10,10,10));
        appCompatImageView.setImageResource(R.drawable.ic_outline_warning_amber_24);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();
    }

    public void onSuccess(){
        textView.setText(onSuccessMsg);
        textView.setTextColor(Color.rgb(10,10,10));
        appCompatImageView.setImageResource(R.drawable.ic_outline_check_24);
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
