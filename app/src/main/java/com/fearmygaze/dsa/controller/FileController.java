package com.fearmygaze.dsa.controller;

import android.content.Context;
import android.content.SharedPreferences;

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

public class FileController {

    /**
     * @param context We need it to get the String from resource file strings.xml
     * @param userID We need the userID so the db knows in what user the file will be assigned
     * @param title We need the title so the userID or the doctor will know what after some time what was for
     * @param desc We need the description so the userID or the doctor will know the description of the upload was for
     * @param file We need the file in String format (after is encoded) so we can upload a file
     * @param iVolleyMessage a quick interface to handle the Success/Warning/Error
     */
    public static void fileUpload (Context context, int userID, String title, String desc, String file, IVolleyMessage iVolleyMessage){
        String[] url = context.getResources().getStringArray(R.array.url);
        String jsonError = context.getResources().getString(R.string.jsonErrorDuring);
        String errorOnFileUpload = context.getResources().getString(R.string.errorOnFileUpload);
        String volleyError = context.getResources().getString(R.string.volleyError);
        String userid = Integer.toString(userID); //This is to convert the int to string

        StringRequest request = new StringRequest(Request.Method.POST, url[0],
                response -> {
                    try {
                        JSONObject jsonResponse = new JSONObject(response);
                        if (jsonResponse.getString("success").equals("1")) {
                            iVolleyMessage.onSuccess(context.getResources().getString(R.string.fileUploadedSuccessfully));
                        } else {
                            iVolleyMessage.onWaring(errorOnFileUpload);
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
                parameters.put("userID", userid);
                parameters.put("fileTitle", title);
                parameters.put("fileDesc", desc);
                parameters.put("fileData", file);
                System.out.println(userid + title + desc);
                return parameters;
            }
        };
        RequestSingleton.getInstance(context).addToRequestQueue(request);
    }

    public static void fileDownload (Context context ){
        String[] url = context.getResources().getStringArray(R.array.url);
        String jsonError = context.getResources().getString(R.string.jsonErrorDuring);
        String errorOnUpload = context.getResources().getString(R.string.errorOnRegister);
        String volleyError = context.getResources().getString(R.string.volleyError);

    }

    public static void fileDelete (Context context){
        String[] url = context.getResources().getStringArray(R.array.url);
        String jsonError = context.getResources().getString(R.string.jsonErrorDuring);
        String errorOnUpload = context.getResources().getString(R.string.errorOnRegister);
        String volleyError = context.getResources().getString(R.string.volleyError);

    }

}
