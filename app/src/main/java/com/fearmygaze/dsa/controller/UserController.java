package com.fearmygaze.dsa.controller;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.preference.PreferenceManager;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.fearmygaze.dsa.Interface.IVolleyMessage;
import com.fearmygaze.dsa.R;
import com.fearmygaze.dsa.model.RequestSingleton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class UserController {

    /**
     * @param context This references the String from resource file strings.xml
     * @param name This references the name of the user
     * @param email This references the email of the user
     * @param passwd This references the passwd of the user
     * @param device_id We need it to set the device id of the phone when the user created for safety
     * @param volleyMessage a quick interface to handle the Success/Warning/Error
     */
    public static void UserRegister(Context context, String name, String email, String passwd, String device_id, IVolleyMessage volleyMessage) {
        String[] url = context.getResources().getStringArray(R.array.url);
        String jsonError = context.getResources().getString(R.string.jsonErrorDuring);
        String errorOnRegister = context.getResources().getString(R.string.errorOnRegister);
        String volleyError = context.getResources().getString(R.string.volleyError);
        SharedPreferences getSharedPrefs = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());

        StringRequest request = new StringRequest(Request.Method.POST, url[0],
                response -> {
                    try {
                        JSONObject jsonResponse = new JSONObject(response);
                        if (jsonResponse.getString("success").equals("1")) {
                            int successUserID = jsonResponse.getInt("userID");
                            SharedPreferences.Editor editor = getSharedPrefs.edit();
                            editor.putString("userName",name);
                            editor.putString("userEmail",email.toLowerCase(Locale.ROOT));
                            editor.putString("userPasswd",passwd);
                            editor.putInt("userID",successUserID);
                            editor.apply();
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
                parameters.put("email", email.toLowerCase(Locale.ROOT));
                parameters.put("passwd", passwd);
                parameters.put("device_id", device_id);
                return parameters;
            }
        };
        RequestSingleton.getInstance(context).addToRequestQueue(request);
    }

    /**
     * @param context We need it to get the String from resource file strings.xml
     * @param email This references the email of the user to login
     * @param passwd This references the passwd of the user to login
     * @param volleyMessage a quick interface to handle the Success/Warning/Error
     */
    public static void UserLogin(Context context, String email, String passwd, IVolleyMessage volleyMessage) {
        String[] url = context.getResources().getStringArray(R.array.url);
        String jsonError = context.getResources().getString(R.string.jsonErrorDuring);
        String errorOnLogin = context.getResources().getString(R.string.errorOnLogin);
        String volleyError = context.getResources().getString(R.string.volleyError);
        SharedPreferences getSharedPrefs = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());

        StringRequest request = new StringRequest(Request.Method.POST, url[1],
                response -> {
                    try {
                        JSONObject jsonResponse = new JSONObject(response);
                        if (jsonResponse.getString("success").equals("1")) {

                            String successUsername = jsonResponse.getString("username");
                            int successUserID = jsonResponse.getInt("userID");

                            SharedPreferences.Editor editor = getSharedPrefs.edit();
                            editor.putString("userName",successUsername);
                            editor.putString("userEmail",email.toLowerCase(Locale.ROOT));
                            editor.putString("userPasswd",passwd);
                            editor.putInt("userID",successUserID);
                            editor.apply();

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
                parameters.put("email", email.toLowerCase(Locale.ROOT));
                parameters.put("passwd", passwd);
                return parameters;
            }
        };
        RequestSingleton.getInstance(context).addToRequestQueue(request);
    }

    /**
     * @param context We need it to get the String from resource file strings.xml
     * @param username This references the new userName that is gonna replace the oldUsername
     * @param oldUsername This references the oldUsername because we want the old reference that already exists in the db
     * @param email This references the email updated or not so the db knows what user we are going to change the credentials
     * @param oldEmail This references the oldEmail because we want the old reference that already exists in the db
     * @param volleyMessage a quick interface to handle the Success/Warning/Error
     */
    public static void UserUpdate(Context context, String username, String oldUsername, String email, String oldEmail, IVolleyMessage volleyMessage) {
        String[] url = context.getResources().getStringArray(R.array.url);
        String jsonError = context.getResources().getString(R.string.jsonErrorDuring);
        String errorOnUpdate = context.getResources().getString(R.string.errorOnUpdate);
        String volleyError = context.getResources().getString(R.string.volleyError);
        SharedPreferences getSharedPrefs = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());

        StringRequest request = new StringRequest(Request.Method.POST, url[2],
                response -> {
                    try {
                        JSONObject jsonResponse = new JSONObject(response);
                        if (jsonResponse.getString("success").equals("1")) {
                            SharedPreferences.Editor editor = getSharedPrefs.edit();
                            editor.putString("userName",username);
                            editor.putString("userEmail",email.toLowerCase(Locale.ROOT));
                            editor.apply();
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
                parameters.put("email", email.toLowerCase(Locale.ROOT));
                parameters.put("oldEmail",oldEmail);
                parameters.put("username", username);
                parameters.put("oldUsername", oldUsername);
                return parameters;
            }
        };
        RequestSingleton.getInstance(context).addToRequestQueue(request);
    }

    /**
     * @param context We need it to get the String from resource file strings.xml
     * @param email This references the email to specify what user we want to see if exists
     * @param userID This references the unique id the user haves
     * @param iVolleyMessage a quick interface to handle the Success/Warning/Error
     */
    public static void UserExist(Context context, String email, int userID, IVolleyMessage iVolleyMessage){
        String[] url = context.getResources().getStringArray(R.array.url);
        String jsonError = context.getResources().getString(R.string.jsonErrorDuring);
        String errorOnUserNotExisting = context.getResources().getString(R.string.errorOnUserNotExisting);
        String volleyError = context.getResources().getString(R.string.volleyError);
        String successOnDelete = context.getResources().getString(R.string.successOnDelete);
        String userid = Integer.toString(userID);

        StringRequest request = new StringRequest(Request.Method.POST, url[5],
                response -> {
                    try {
                        JSONObject jsonResponse = new JSONObject(response);
                        if (jsonResponse.getString("success").equals("1")) {
                            iVolleyMessage.onSuccess(successOnDelete);
                        } else {
                            iVolleyMessage.onWaring(errorOnUserNotExisting);
                        }
                    } catch (JSONException e) {
                        iVolleyMessage.onError(jsonError + " " + e.getMessage());
                    }
                },
                error -> iVolleyMessage.onError(volleyError + error.getMessage())) {
            @NonNull
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> parameters = new HashMap<>();
                parameters.put("email", email.toLowerCase(Locale.ROOT));
                parameters.put("userID",userid);
                return parameters;
            }
        };
        RequestSingleton.getInstance(context).addToRequestQueue(request);
    }

    /**
     * @param context We need it to get the String from resource file strings.xml
     * @param email This references the unique email of user we want to delete
     * @param volleyMessage a quick interface to handle the Success/Warning/Error
     */
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
                parameters.put("email", email.toLowerCase(Locale.ROOT));
                return parameters;
            }
        };
        RequestSingleton.getInstance(context).addToRequestQueue(request);
    }

}
