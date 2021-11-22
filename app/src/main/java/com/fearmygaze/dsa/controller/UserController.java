package com.fearmygaze.dsa.controller;

import android.content.Context;

import androidx.annotation.NonNull;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.fearmygaze.dsa.R;
import com.fearmygaze.dsa.model.IVolleyMessage;
import com.fearmygaze.dsa.model.RequestSingleton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class UserController {

    public static void UserRegister(Context context, String name, String email, String passwd, String device_id, IVolleyMessage volleyMessage) {
        String[] url = context.getResources().getStringArray(R.array.url);
        String jsonError = context.getResources().getString(R.string.jsonErrorDuring);
        String errorOnRegister = context.getResources().getString(R.string.errorOnRegister);
        String volleyError = context.getResources().getString(R.string.volleyError);

        StringRequest request = new StringRequest(Request.Method.POST, url[0],
                response -> {
                    try {
                        JSONObject jsonResponse = new JSONObject(response);
                        if (jsonResponse.getString("success").equals("1")) {
                            volleyMessage.onSuccess(" ");
                        } else {
                            volleyMessage.onWaring(errorOnRegister);
                        }
                    } catch (JSONException e) {
                        volleyMessage.onError(jsonError + " " + e.getMessage());
                    }
                },
                error -> volleyMessage.onError(volleyError + error.getMessage())) {
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

    public static void UserLogin(Context context, String email, String passwd, IVolleyMessage volleyMessage) {
        String[] url = context.getResources().getStringArray(R.array.url);
        String jsonError = context.getResources().getString(R.string.jsonErrorDuring);
        String errorOnLogin = context.getResources().getString(R.string.errorOnLogin);
        String volleyError = context.getResources().getString(R.string.volleyError);

        StringRequest request = new StringRequest(Request.Method.POST, url[1],
                response -> {
                    try {
                        JSONObject jsonResponse = new JSONObject(response);
                        if (jsonResponse.getString("success").equals("1")) {

                            String successUsername = jsonResponse.getString("username");

                            volleyMessage.onSuccess(successUsername);

                        } else {
                            volleyMessage.onWaring(errorOnLogin);
                        }
                    } catch (JSONException e) {
                        volleyMessage.onError(jsonError + " " + e.getMessage());
                    }
                },
                error -> volleyMessage.onError(volleyError + error.getMessage())) {
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

    public static void UserUpdate(Context context, String username, String oldName, String email, IVolleyMessage volleyMessage) {
        String[] url = context.getResources().getStringArray(R.array.url);
        String jsonError = context.getResources().getString(R.string.jsonErrorDuring);
        String errorOnUpdate = context.getResources().getString(R.string.errorOnUpdate);
        String volleyError = context.getResources().getString(R.string.volleyError);

        StringRequest request = new StringRequest(Request.Method.POST, url[2],
                response -> {
                    try {
                        JSONObject jsonResponse = new JSONObject(response);
                        if (jsonResponse.getString("success").equals("1")) {
                            volleyMessage.onSuccess("");
                        } else {
                            volleyMessage.onWaring(errorOnUpdate);
                        }
                    } catch (JSONException e) {
                        volleyMessage.onError(jsonError + " " + e.getMessage());
                    }
                },
                error -> volleyMessage.onError(volleyError + error.getMessage())) {
            @NonNull
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> parameters = new HashMap<>();
                parameters.put("email", email);
                parameters.put("username", username);
                parameters.put("oldName", oldName);
                return parameters;
            }
        };
        RequestSingleton.getInstance(context).addToRequestQueue(request);
    }

    public static void UserDelete(Context context, String email, IVolleyMessage volleyMessage) {
        String[] url = context.getResources().getStringArray(R.array.url);
        String jsonError = context.getResources().getString(R.string.jsonErrorDuring);
        String errorOnDelete = context.getResources().getString(R.string.errorOnDelete);
        String volleyError = context.getResources().getString(R.string.volleyError);
        String successOnDelete = context.getResources().getString(R.string.successOnDelete);

        StringRequest request = new StringRequest(Request.Method.POST, url[3],
                response -> {
                    try {
                        JSONObject jsonResponse = new JSONObject(response);
                        if (jsonResponse.getString("success").equals("1")) {
                            volleyMessage.onSuccess(successOnDelete);
                        } else {
                            volleyMessage.onWaring(errorOnDelete);
                        }
                    } catch (JSONException e) {
                        volleyMessage.onError(jsonError + " " + e.getMessage());
                    }
                },
                error -> volleyMessage.onError(volleyError + error.getMessage())) {
            @NonNull
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> parameters = new HashMap<>();
                parameters.put("email", email);
                return parameters;
            }
        };
        RequestSingleton.getInstance(context).addToRequestQueue(request);
    }

}