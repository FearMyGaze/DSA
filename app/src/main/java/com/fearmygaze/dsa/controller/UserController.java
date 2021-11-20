package com.fearmygaze.dsa.controller;

import static android.content.Context.MODE_PRIVATE;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.fearmygaze.dsa.R;
import com.fearmygaze.dsa.model.IVolleyErrorMessage;
import com.fearmygaze.dsa.model.RequestSingleton;
import com.fearmygaze.dsa.model.User;
import com.fearmygaze.dsa.view.activity.Main;
import com.fearmygaze.dsa.view.fragment.SignIn;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class UserController {

    public static void UserRegister(Context context, String name , String email , String passwd, String device_id , IVolleyErrorMessage volleyErrorMessage){
        String[] url= context.getResources().getStringArray(R.array.url);
        String jsonError = context.getResources().getString(R.string.jsonErrorDuring);
        String errorOnRegister = context.getResources().getString(R.string.errorOnRegister);
        String volleyError = context.getResources().getString(R.string.volleyError);

        StringRequest request = new StringRequest(Request.Method.POST, url[0],
                response -> {
                    try {
                        JSONObject jsonResponse = new JSONObject(response);
                        if (jsonResponse.getString("success").equals("1")) {
                            User user = new User(name,email);

                            Intent intent = new Intent(context, Main.class);
                            intent.putExtra("User", user);
                            context.startActivity(intent);
                            ((Activity) context).finish();
                        } else {
                            volleyErrorMessage.onWaring(errorOnRegister);
                        }

                    } catch (JSONException e) {
                        volleyErrorMessage.onError(jsonError+ " " +e.getMessage());
                    }
                },
                error -> volleyErrorMessage.onError(volleyError+ error.getMessage())) {
            @NonNull
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> parameters = new HashMap<>();
                parameters.put("username", name);
                parameters.put("email", email);
                parameters.put("passwd", passwd);
                parameters.put("device_id", device_id);
                return parameters;
            }
        };
        RequestSingleton.getInstance(context).addToRequestQueue(request);
    }


    public static void UserLogin( Context context, String email , String passwd ,IVolleyErrorMessage volleyErrorMessage) {
        String[] url= context.getResources().getStringArray(R.array.url);
        String jsonError = context.getResources().getString(R.string.jsonErrorDuring);
        String errorOnRegister = context.getResources().getString(R.string.errorOnRegister);
        String volleyError = context.getResources().getString(R.string.volleyError);

        StringRequest request = new StringRequest(Request.Method.POST, url[1],
                response -> {
                    try {
                        JSONObject jsonResponse = new JSONObject(response);
                        if (jsonResponse.getString("success").equals("1")) {

                            String successEmail = jsonResponse.getString("email");
                            String successName = jsonResponse.getString("username");

                            SharedPreferences.Editor editor = ((Activity) context).getPreferences(MODE_PRIVATE).edit();
                            editor.putString("userEmail", successEmail);
                            editor.putString("userPasswd", successName);
                            editor.apply();

                            User me = new User(successName, successEmail);

                            Intent intent = new Intent(context, Main.class);
                            intent.putExtra("User", me);
                            context.startActivity(intent);
                            ((Activity) context).finish();
                        } else {
                            volleyErrorMessage.onWaring(errorOnRegister);
                        }

                    } catch (JSONException e) {
                        volleyErrorMessage.onError(jsonError+ " " +e.getMessage());
                    }
                },
                error -> volleyErrorMessage.onError(volleyError+ error.getMessage())) {
            @NonNull
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> parameters = new HashMap<>();
                parameters.put("email", email);
                parameters.put("passwd", passwd);
                return parameters;
            }
        };
        RequestSingleton.getInstance(context).addToRequestQueue(request);

    }

}
