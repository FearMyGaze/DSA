package com.fearmygaze.dsa.controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.fearmygaze.dsa.R;
import com.fearmygaze.dsa.model.IVolleyErrorMessage;
import com.fearmygaze.dsa.model.RequestSingleton;
import com.fearmygaze.dsa.model.User;
import com.fearmygaze.dsa.view.activity.Main;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class UserController {

    public static void UserRegister(Context context, String name , String email , String passwd, String device_id , IVolleyErrorMessage volleyErrorMessage){
        String[] url= context.getResources().getStringArray(R.array.url);
        StringRequest request = new StringRequest(Request.Method.POST, url[0],
                response -> {
                    try {
                        JSONObject jsonResponse = new JSONObject(response);
                        if (jsonResponse.getString("success").equals("1")) {
                            User user = new User(name,email);

                            Intent intent = new Intent(context, Main.class);
                            intent.putExtra("User", user);
                            ((Activity)context).startActivity(intent);
                            ((Activity) context).finish();
                        } else {
                            volleyErrorMessage.onWaring("The user already exists");
                        }

                    } catch (JSONException e) {
                        volleyErrorMessage.onError("Error during ......."+e.getMessage());
                        System.out.println(e.getMessage());
                    }
                },
                /*
                * TODO: CHANGE THE ERRORS
                * */
                error -> volleyErrorMessage.onError("Error during connection"+ error.getMessage())) {
            @NonNull
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> parameters = new HashMap<>();
                parameters.put("username", name);
                parameters.put("email", email);
                parameters.put("password", passwd);
                parameters.put("device_id", device_id);
                return parameters;
            }
        };
        RequestSingleton.getInstance(context).addToRequestQueue(request);


    }

}
